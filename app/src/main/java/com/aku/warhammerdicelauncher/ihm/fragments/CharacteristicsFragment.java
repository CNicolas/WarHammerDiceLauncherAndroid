package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;
import com.aku.warhammerdicelauncher.utils.PlayerRepository;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class CharacteristicsFragment extends Fragment {

    private DiscreteSeekBar strengthBar;
    private DiscreteSeekBar toughnessBar;
    private DiscreteSeekBar agilityBar;
    private DiscreteSeekBar intelligenceBar;
    private DiscreteSeekBar willpowerBar;
    private DiscreteSeekBar fellowshipBar;

    public CharacteristicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characteristics, container, false);

        initializeSeekBars(rootView);

        return rootView;
    }

    private void initializeSeekBars(View rootView) {
        strengthBar = (DiscreteSeekBar) rootView.findViewById(R.id.strength_bar);
        toughnessBar = (DiscreteSeekBar) rootView.findViewById(R.id.toughness_bar);
        agilityBar = (DiscreteSeekBar) rootView.findViewById(R.id.agility_bar);
        intelligenceBar = (DiscreteSeekBar) rootView.findViewById(R.id.intelligence_bar);
        willpowerBar = (DiscreteSeekBar) rootView.findViewById(R.id.willpower_bar);
        fellowshipBar = (DiscreteSeekBar) rootView.findViewById(R.id.fellowship_bar);

        strengthBar.setOnProgressChangeListener(new CharacteristicBarChangeListener(Characteristic.STRENGTH));
    }

    private void setPlayerCharacteristic(Characteristic characteristic, int newValue) {
        CharacteristicsDto characteristics = PlayerRepository.getPlayerInstance().getCharacteristics();
        switch (characteristic) {
            case STRENGTH:
                characteristics.setStrength(newValue);
                return;
            case TOUGHNESS:
                characteristics.setToughness(newValue);
                return;
            case AGILITY:
                characteristics.setAgility(newValue);
                return;
            case INTELLIGENCE:
                characteristics.setIntelligence(newValue);
                return;
            case WILLPOWER:
                characteristics.setWillpower(newValue);
                return;
            case FELLOWSHIP:
                characteristics.setFellowship(newValue);
                return;
            default:
                return;
        }

    }

    private class CharacteristicBarChangeListener implements DiscreteSeekBar.OnProgressChangeListener {

        private final Characteristic characteristic;

        public CharacteristicBarChangeListener(Characteristic characteristic) {
            this.characteristic = characteristic;
        }

        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            setPlayerCharacteristic(characteristic, value);
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            new AlertDialog.Builder(getActivity()).setTitle("Lol").setMessage("votre force" + PlayerRepository.getPlayerInstance().getCharacteristics().getStrength()).show();
        }
    }

//    private PlayerDto saveAndGet() {
//        WarHammerDatabaseHelper warHammerDatabaseHelper = new WarHammerDatabaseHelper(getActivity());
//
//        CharacteristicsDao characteristicsDao = new CharacteristicsDao(warHammerDatabaseHelper);
//        characteristicsDao.insert(createSampleCharacteristicsDto());
//
//        PlayerDao playerDao = new PlayerDao(warHammerDatabaseHelper);
//        PlayerDto player = createSamplePlayerDto();
//        playerDao.insert(player);
//
//        SkillDao skillDao = new SkillDao(warHammerDatabaseHelper);
//        skillDao.insertAll(createSamplesSkillDto(), player);
//
//        return playerDao.findById(1);
//    }
//
//    private CharacteristicsDto createSampleCharacteristicsDto() {
//        CharacteristicsDto dto = new CharacteristicsDto();
//
//        dto.setId(1);
//
//        dto.setStrength(1);
//        dto.setToughness(2);
//        dto.setAgility(3);
//        dto.setIntelligence(4);
//        dto.setWillpower(5);
//        dto.setFellowship(6);
//
//        dto.setStrength_fortune(6);
//        dto.setToughness_fortune(5);
//        dto.setAgility_fortune(4);
//        dto.setIntelligence_fortune(3);
//        dto.setWillpower_fortune(2);
//        dto.setFellowship_fortune(1);
//
//        return dto;
//    }
//
//    private List<SkillDto> createSamplesSkillDto() {
//        List<SkillDto> skills = new ArrayList<>();
//
//        SkillDto dto1 = new SkillDto();
//        dto1.setCharacteristic(Characteristic.AGILITY);
//        dto1.setLevel(1);
//        dto1.setName("Capacité de Tir");
//        skills.add(dto1);
//
//        SkillDto dto2 = new SkillDto();
//        dto2.setCharacteristic(Characteristic.INTELLIGENCE);
//        dto2.setLevel(0);
//        dto2.setName("Observation");
//        skills.add(dto2);
//
//        SkillDto dto3 = new SkillDto();
//        dto3.setCharacteristic(Characteristic.WILLPOWER);
//        dto3.setLevel(0);
//        dto3.setName("Discipline");
//        skills.add(dto3);
//
//        return skills;
//    }
//
//    private PlayerDto createSamplePlayerDto() {
//        PlayerDto dto = new PlayerDto();
//
//        dto.setId(1);
//
//        dto.setName("Aku");
//        dto.setRace("Elfe");
//        dto.setAge(59);
//        dto.setSize(200);
//        dto.setDescription("OK LOL");
//
//        dto.setRank(1);
//        dto.setExperience(0);
//        dto.setMax_experience(9);
//        dto.setWounds(0);
//        dto.setMax_wounds(12);
//        dto.setReckless(0);
//        dto.setMax_reckless(2);
//        dto.setConservative(0);
//        dto.setMax_conservative(2);
//
//        dto.setMoney_brass(48);
//        dto.setMoney_silver(13);
//        dto.setMoney_gold(3);
//
//        dto.setCharacteristics(createSampleCharacteristicsDto());
//        dto.setSkills(createSamplesSkillDto());
//
//        return dto;
//    }
}
