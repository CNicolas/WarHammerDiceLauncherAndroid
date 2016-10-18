package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.tools.PlayerEditTextWatcher;
import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;
import com.aku.warhammerdicelauncher.tools.enums.PlayerInformation;

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
        playerAgeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.AGE));

        final EditText playerDescriptionView = (EditText) rootView.findViewById(R.id.player_description);
        playerDescriptionView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.DESCRIPTION));


        final EditText playerExperienceView = (EditText) rootView.findViewById(R.id.player_experience);
        playerExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.EXPERIENCE));

        final EditText playerMaxExperienceView = (EditText) rootView.findViewById(R.id.player_max_experience);
        playerMaxExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.MAX_EXPERIENCE));

        final EditText playerNameView = (EditText) rootView.findViewById(R.id.player_name);
        playerNameView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.NAME));

        final EditText playerRaceView = (EditText) rootView.findViewById(R.id.player_race);
        playerRaceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RACE));

        final EditText playerRankView = (EditText) rootView.findViewById(R.id.player_rank);
        playerRankView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RANK));

        final EditText playerSizeView = (EditText) rootView.findViewById(R.id.player_size);
        playerSizeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.SIZE));
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
            Player player = PlayerContext.getPlayerInstance();
            new AlertDialog.Builder(getActivity())
                    .setTitle("Nom = " + player.getName())
                    .setMessage(String.format("Force = %d + %d blancs", player.getCharacteristics().getStrength(), player.getCharacteristics().getStrength_fortune()))
                    .show();
        }
    }

}
