package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.CharacteristicsDao;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;
import com.aku.warhammerdicelauncher.model.dto.SkillDto;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class CharacteristicsFragment extends Fragment {

    public CharacteristicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characteristics, container, false);

        PlayerDto player = saveAndGet();
        new AlertDialog.Builder(getActivity())
                .setTitle(player.getName())
                .setMessage("Salut ! voici ton agi : " + player.getCharacteristics().getAgility())
                .show();

        return rootView;
    }

    private PlayerDto saveAndGet() {
        WarHammerDatabaseHelper warHammerDatabaseHelper = new WarHammerDatabaseHelper(getActivity());

        CharacteristicsDao characteristicsDao = new CharacteristicsDao(warHammerDatabaseHelper);
        characteristicsDao.insert(createSampleCharacteristicsDto());

        PlayerDao playerDao = new PlayerDao(warHammerDatabaseHelper);
        PlayerDto player = createSamplePlayerDto();
        playerDao.insert(player);

//        SkillDao skillDao = new SkillDao(warHammerDatabaseHelper);
//        skillDao.insert(createSampleSkillDto());

        return playerDao.findById(1);
    }

    private CharacteristicsDto createSampleCharacteristicsDto() {
        CharacteristicsDto dto = new CharacteristicsDto();

        dto.setId(1);

        dto.setStrength(1);
        dto.setToughness(2);
        dto.setAgility(3);
        dto.setIntelligence(4);
        dto.setWillpower(5);
        dto.setFellowship(6);

        dto.setStrength_fortune(6);
        dto.setToughness_fortune(5);
        dto.setAgility_fortune(4);
        dto.setIntelligence_fortune(3);
        dto.setWillpower_fortune(2);
        dto.setFellowship_fortune(1);

        return dto;
    }

    private SkillDto createSampleSkillDto() {
        SkillDto dto = new SkillDto();

        dto.setCharacteristic(Characteristic.AGILITY);
        dto.setLevel(1);
        dto.setName("Capacité de Tir");

        return dto;
    }

    private PlayerDto createSamplePlayerDto() {
        PlayerDto dto = new PlayerDto();

        dto.setName("Aku");
        dto.setRace("Elfe");
        dto.setAge(59);
        dto.setSize(200);
        dto.setDescription("OK LOL");

        dto.setRank(1);
        dto.setExperience(0);
        dto.setMax_experience(9);
        dto.setWounds(0);
        dto.setMax_wounds(12);
        dto.setReckless(0);
        dto.setMax_reckless(2);
        dto.setConservative(0);
        dto.setMax_conservative(2);

        dto.setMoney_brass(48);
        dto.setMoney_silver(13);
        dto.setMoney_gold(3);

        dto.setCharacteristics(createSampleCharacteristicsDto());

        List<SkillDto> skills = new ArrayList<>();
        skills.add(createSampleSkillDto());
        dto.setSkills(skills);

        return dto;
    }
}
