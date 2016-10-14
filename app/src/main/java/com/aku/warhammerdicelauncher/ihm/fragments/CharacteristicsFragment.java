package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;
import com.aku.warhammerdicelauncher.utils.PlayerRepository;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelScrollListener;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

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

        initWheels(rootView);

        return rootView;
    }

    //region Wheels Setup
    private void initWheels(View rootView) {
        setupCharacteristicWheel(rootView, R.id.strength_wheel, Characteristic.STRENGTH);
        setupCharacteristicFortuneWheel(rootView, R.id.strength_fortune_wheel, Characteristic.STRENGTH_FORTUNE);
        setupCharacteristicWheel(rootView, R.id.toughness_wheel, Characteristic.TOUGHNESS);
        setupCharacteristicFortuneWheel(rootView, R.id.toughness_fortune_wheel, Characteristic.TOUGHNESS_FORTUNE);
        setupCharacteristicWheel(rootView, R.id.agility_wheel, Characteristic.AGILITY);
        setupCharacteristicFortuneWheel(rootView, R.id.agility_fortune_wheel, Characteristic.AGILITY_FORTUNE);
        setupCharacteristicWheel(rootView, R.id.intelligence_wheel, Characteristic.INTELLIGENCE);
        setupCharacteristicFortuneWheel(rootView, R.id.intelligence_fortune_wheel, Characteristic.INTELLIGENCE_FORTUNE);
        setupCharacteristicWheel(rootView, R.id.willpower_wheel, Characteristic.WILLPOWER);
        setupCharacteristicFortuneWheel(rootView, R.id.willpower_fortune_wheel, Characteristic.WILLPOWER_FORTUNE);
        setupCharacteristicWheel(rootView, R.id.fellowship_wheel, Characteristic.FELLOWSHIP);
        setupCharacteristicFortuneWheel(rootView, R.id.fellowship_fortune_wheel, Characteristic.FELLOWSHIP_FORTUNE);
    }

    private void setupCharacteristicWheel(View rootView, int wheelId, Characteristic characteristic) {
        setupWheel(rootView, wheelId, R.layout.characteristic_wheel_blue_item, characteristic);
    }

    private void setupCharacteristicFortuneWheel(View rootView, int wheelId, Characteristic characteristic) {
        setupWheel(rootView, wheelId, R.layout.characteristic_wheel_white_item, characteristic);
    }

    private void setupWheel(View rootView, int id, int idResourceItem, Characteristic characteristic) {
        final AbstractWheel wheel = (AbstractWheel) rootView.findViewById(id);
        NumericWheelAdapter minAdapter = new NumericWheelAdapter(getActivity(), 0, 10, "%2d");
        minAdapter.setItemResource(idResourceItem);
        minAdapter.setItemTextResource(R.id.text);
        wheel.setViewAdapter(minAdapter);
        wheel.addScrollingListener(new CharacteristicWheelScrollListener(characteristic));
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
            case STRENGTH_FORTUNE:
                characteristics.setStrength_fortune(newValue);
                return;
            case TOUGHNESS_FORTUNE:
                characteristics.setToughness_fortune(newValue);
                return;
            case AGILITY_FORTUNE:
                characteristics.setAgility_fortune(newValue);
                return;
            case INTELLIGENCE_FORTUNE:
                characteristics.setIntelligence_fortune(newValue);
                return;
            case WILLPOWER_FORTUNE:
                characteristics.setWillpower_fortune(newValue);
                return;
            case FELLOWSHIP_FORTUNE:
                characteristics.setFellowship_fortune(newValue);
                return;
            default:
                return;
        }

    }
    //endregion

    private class CharacteristicWheelScrollListener implements OnWheelScrollListener {

        private final Characteristic characteristic;

        public CharacteristicWheelScrollListener(Characteristic characteristic) {
            this.characteristic = characteristic;
        }

        @Override
        public void onScrollingStarted(AbstractWheel abstractWheel) {

        }

        @Override
        public void onScrollingFinished(AbstractWheel abstractWheel) {
            setPlayerCharacteristic(characteristic, abstractWheel.getCurrentItem());
            PlayerDto player = PlayerRepository.getPlayerInstance();
            new AlertDialog.Builder(getActivity())
                    .setTitle("Nom = " + player.getName())
                    .setMessage(String.format("Force = %d + %d blancs", player.getCharacteristics().getStrength(), player.getCharacteristics().getStrength_fortune()))
                    .show();
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
