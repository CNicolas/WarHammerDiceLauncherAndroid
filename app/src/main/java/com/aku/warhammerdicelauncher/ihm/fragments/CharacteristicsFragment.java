package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.utils.PlayerContext;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;
import com.aku.warhammerdicelauncher.utils.enums.PlayerInformation;

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
        initEditTexts(rootView);

        return rootView;
    }

    //region Characteristics wheels setup
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
        Characteristics characteristics = PlayerContext.getPlayerInstance().getCharacteristics();
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

    //region Text fields setup
    private void initEditTexts(View rootView) {
        final EditText playerAgeView = (EditText) rootView.findViewById(R.id.player_age);
        playerAgeView.addTextChangedListener(new EditTextWatcher(PlayerInformation.AGE));

        final EditText playerDescriptionView = (EditText) rootView.findViewById(R.id.player_description);
        playerDescriptionView.addTextChangedListener(new EditTextWatcher(PlayerInformation.DESCRIPTION));


        final EditText playerExperienceView = (EditText) rootView.findViewById(R.id.player_experience);
        playerExperienceView.addTextChangedListener(new EditTextWatcher(PlayerInformation.EXPERIENCE));

        final EditText playerMaxExperienceView = (EditText) rootView.findViewById(R.id.player_max_experience);
        playerMaxExperienceView.addTextChangedListener(new EditTextWatcher(PlayerInformation.MAX_EXPERIENCE));

        final EditText playerNameView = (EditText) rootView.findViewById(R.id.player_name);
        playerNameView.addTextChangedListener(new EditTextWatcher(PlayerInformation.NAME));

        final EditText playerRaceView = (EditText) rootView.findViewById(R.id.player_race);
        playerRaceView.addTextChangedListener(new EditTextWatcher(PlayerInformation.RACE));

        final EditText playerRankView = (EditText) rootView.findViewById(R.id.player_rank);
        playerRankView.addTextChangedListener(new EditTextWatcher(PlayerInformation.RANK));

        final EditText playerSizeView = (EditText) rootView.findViewById(R.id.player_size);
        playerSizeView.addTextChangedListener(new EditTextWatcher(PlayerInformation.SIZE));
    }
    //endregion

    private void setPlayerInformation(PlayerInformation playerInformation, String newValue) {
        Player player = PlayerContext.getPlayerInstance();
        try {
            switch (playerInformation) {
                case AGE:
                    player.setAge(Integer.parseInt(newValue));
                    return;
                case DESCRIPTION:
                    player.setDescription(newValue);
                    return;
                case EXPERIENCE:
                    player.setExperience(Integer.parseInt(newValue));
                    return;
                case MAX_EXPERIENCE:
                    player.setMax_experience(Integer.parseInt(newValue));
                    return;
                case NAME:
                    player.setName(newValue);
                    return;
                case RACE:
                    player.setRace(newValue);
                    return;
                case RANK:
                    player.setRank(Integer.parseInt(newValue));
                    return;
                case SIZE:
                    player.setSize(Double.parseDouble(newValue));
                    return;
                default:
                    return;
            }
        } catch (NumberFormatException nfe) {
            return;
        }
    }

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
            Player player = PlayerContext.getPlayerInstance();
            new AlertDialog.Builder(getActivity())
                    .setTitle("Nom = " + player.getName())
                    .setMessage(String.format("Force = %d + %d blancs", player.getCharacteristics().getStrength(), player.getCharacteristics().getStrength_fortune()))
                    .show();
        }
    }

    private class EditTextWatcher implements TextWatcher {
        private final PlayerInformation playerInformation;

        public EditTextWatcher(PlayerInformation playerInformation) {
            this.playerInformation = playerInformation;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            setPlayerInformation(playerInformation, s.toString());
        }
    }

    //endregion


//    private Player saveAndGet() {
//        WarHammerDatabaseHelper warHammerDatabaseHelper = new WarHammerDatabaseHelper(getActivity());
//
//        CharacteristicsDao characteristicsDao = new CharacteristicsDao(warHammerDatabaseHelper);
//        characteristicsDao.insert(createSampleCharacteristicsDto());
//
//        PlayerDao playerDao = new PlayerDao(warHammerDatabaseHelper);
//        Player player = createSamplePlayerDto();
//        playerDao.insert(player);
//
//        SkillDao skillDao = new SkillDao(warHammerDatabaseHelper);
//        skillDao.insertAll(createSamplesSkillDto(), player);
//
//        return playerDao.findById(1);
//    }
//
//    private Characteristics createSampleCharacteristicsDto() {
//        Characteristics dto = new Characteristics();
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
//    private List<Skill> createSamplesSkillDto() {
//        List<Skill> skills = new ArrayList<>();
//
//        Skill dto1 = new Skill();
//        dto1.setCharacteristic(Characteristic.AGILITY);
//        dto1.setLevel(1);
//        dto1.setName("Capacité de Tir");
//        skills.add(dto1);
//
//        Skill dto2 = new Skill();
//        dto2.setCharacteristic(Characteristic.INTELLIGENCE);
//        dto2.setLevel(0);
//        dto2.setName("Observation");
//        skills.add(dto2);
//
//        Skill dto3 = new Skill();
//        dto3.setCharacteristic(Characteristic.WILLPOWER);
//        dto3.setLevel(0);
//        dto3.setName("Discipline");
//        skills.add(dto3);
//
//        return skills;
//    }
//
//    private Player createSamplePlayerDto() {
//        Player dto = new Player();
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
