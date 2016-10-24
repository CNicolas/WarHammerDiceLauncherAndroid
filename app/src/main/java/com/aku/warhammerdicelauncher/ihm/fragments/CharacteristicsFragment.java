package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.tools.CharacteristicEditTextWatcher;
import com.aku.warhammerdicelauncher.ihm.tools.PlayerEditTextWatcher;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;
import com.aku.warhammerdicelauncher.tools.enums.PlayerInformation;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class CharacteristicsFragment extends Fragment {
    //region Characteristic Fields
    private EditText mPlayerStrengthView;
    private EditText mPlayerToughnessView;
    private EditText mPlayerAgilityView;
    private EditText mPlayerIntelligenceView;
    private EditText mPlayerWillpowerView;
    private EditText mPlayerFellowshipView;

    private EditText mPlayerStrengthFortuneView;
    private EditText mPlayerToughnessFortuneView;
    private EditText mPlayerAgilityFortuneView;
    private EditText mPlayerIntelligenceFortuneView;
    private EditText mPlayerWillpowerFortuneView;
    private EditText mPlayerFellowshipFortuneView;
    //endregion

    //region Player Information
    private EditText mPlayerAgeView;
    private EditText mPlayerCareerView;
    private EditText mPlayerDescriptionView;
    private EditText mPlayerExperienceView;
    private EditText mPlayerMaxExperienceView;
    private EditText mPlayerWoundsView;
    private EditText mPlayerMaxWoundsView;
    private EditText mPlayerCorruptionView;
    private EditText mPlayerMaxCorruptionView;
    private EditText mPlayerNameView;
    private EditText mPlayerRaceView;
    private EditText mPlayerRankView;
    private EditText mPlayerSizeView;
    //endregion

    private View mRootView;
    private boolean mIsInEdition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_characteristics, container, false);
        setHasOptionsMenu(true);

        initPlayerCharacteristicsFields();
        initPlayerCharacteristicsWatchers();

        initPlayerInformationFields();
        initPlayerInformationWatchers();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mIsInEdition = bundle.getBoolean(IPlayerConstants.IS_IN_EDITION_KEY);
        }

        changeEdition();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillPlayerCharacteristicsFields();
        fillPlayerInformationFields();
    }

    public void changeEdition() {
        boolean isInEdition = PlayerContext.isInEdition();

        if (mIsInEdition != isInEdition) {
            mIsInEdition = isInEdition;
            updateCharacteristicsEdition(isInEdition);
            updateCharacteristicsFortuneEdition(isInEdition);
            updatePlayerInformationEdition(isInEdition);
        }
    }

    private void updateCharacteristicsEdition(boolean isInEdition) {
        mPlayerStrengthView.setFocusable(isInEdition);
        mPlayerStrengthView.setFocusableInTouchMode(isInEdition);
        mPlayerStrengthView.setClickable(isInEdition);

        mPlayerToughnessView.setFocusable(isInEdition);
        mPlayerToughnessView.setFocusableInTouchMode(isInEdition);
        mPlayerToughnessView.setClickable(isInEdition);

        mPlayerAgilityView.setFocusable(isInEdition);
        mPlayerAgilityView.setFocusableInTouchMode(isInEdition);
        mPlayerAgilityView.setClickable(isInEdition);

        mPlayerIntelligenceView.setFocusable(isInEdition);
        mPlayerIntelligenceView.setFocusableInTouchMode(isInEdition);
        mPlayerIntelligenceView.setClickable(isInEdition);

        mPlayerWillpowerView.setFocusable(isInEdition);
        mPlayerWillpowerView.setFocusableInTouchMode(isInEdition);
        mPlayerWillpowerView.setClickable(isInEdition);

        mPlayerFellowshipView.setFocusable(isInEdition);
        mPlayerFellowshipView.setFocusableInTouchMode(isInEdition);
        mPlayerFellowshipView.setClickable(isInEdition);
    }

    private void updateCharacteristicsFortuneEdition(boolean isInEdition) {
        mPlayerStrengthFortuneView.setFocusable(isInEdition);
        mPlayerStrengthFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerStrengthFortuneView.setClickable(isInEdition);

        mPlayerToughnessFortuneView.setFocusable(isInEdition);
        mPlayerToughnessFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerToughnessFortuneView.setClickable(isInEdition);

        mPlayerAgilityFortuneView.setFocusable(isInEdition);
        mPlayerAgilityFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerAgilityFortuneView.setClickable(isInEdition);

        mPlayerIntelligenceFortuneView.setFocusable(isInEdition);
        mPlayerIntelligenceFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerIntelligenceFortuneView.setClickable(isInEdition);

        mPlayerWillpowerFortuneView.setFocusable(isInEdition);
        mPlayerWillpowerFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerWillpowerFortuneView.setClickable(isInEdition);

        mPlayerFellowshipFortuneView.setFocusable(isInEdition);
        mPlayerFellowshipFortuneView.setFocusableInTouchMode(isInEdition);
        mPlayerFellowshipFortuneView.setClickable(isInEdition);
    }

    private void updatePlayerInformationEdition(boolean isInEdition) {
        mPlayerAgeView.setFocusable(isInEdition);
        mPlayerAgeView.setFocusableInTouchMode(isInEdition);
        mPlayerAgeView.setClickable(isInEdition);

        mPlayerCareerView.setFocusable(isInEdition);
        mPlayerCareerView.setFocusableInTouchMode(isInEdition);
        mPlayerCareerView.setClickable(isInEdition);

        mPlayerDescriptionView.setFocusable(isInEdition);
        mPlayerDescriptionView.setFocusableInTouchMode(isInEdition);
        mPlayerDescriptionView.setClickable(isInEdition);

        mPlayerExperienceView.setFocusable(isInEdition);
        mPlayerExperienceView.setFocusableInTouchMode(isInEdition);
        mPlayerExperienceView.setClickable(isInEdition);

        mPlayerMaxExperienceView.setFocusable(isInEdition);
        mPlayerMaxExperienceView.setFocusableInTouchMode(isInEdition);
        mPlayerMaxExperienceView.setClickable(isInEdition);

        mPlayerNameView.setFocusable(isInEdition);
        mPlayerNameView.setFocusableInTouchMode(isInEdition);
        mPlayerNameView.setClickable(isInEdition);

        mPlayerRaceView.setFocusable(isInEdition);
        mPlayerRaceView.setFocusableInTouchMode(isInEdition);
        mPlayerRaceView.setClickable(isInEdition);

        mPlayerRankView.setFocusable(isInEdition);
        mPlayerRankView.setFocusableInTouchMode(isInEdition);
        mPlayerRankView.setClickable(isInEdition);

        mPlayerSizeView.setFocusable(isInEdition);
        mPlayerSizeView.setFocusableInTouchMode(isInEdition);
        mPlayerSizeView.setClickable(isInEdition);
    }

    //region Characteristics Setup
    private void initPlayerCharacteristicsFields() {
        mPlayerStrengthView = (EditText) mRootView.findViewById(R.id.strength_characteristic);
        mPlayerToughnessView = (EditText) mRootView.findViewById(R.id.toughness_characteristic);
        mPlayerAgilityView = (EditText) mRootView.findViewById(R.id.agility_characteristic);
        mPlayerIntelligenceView = (EditText) mRootView.findViewById(R.id.intelligence_characteristic);
        mPlayerWillpowerView = (EditText) mRootView.findViewById(R.id.willpower_characteristic);
        mPlayerFellowshipView = (EditText) mRootView.findViewById(R.id.fellowship_characteristic);

        mPlayerStrengthFortuneView = (EditText) mRootView.findViewById(R.id.strength_fortune);
        mPlayerToughnessFortuneView = (EditText) mRootView.findViewById(R.id.toughness_fortune);
        mPlayerAgilityFortuneView = (EditText) mRootView.findViewById(R.id.agility_fortune);
        mPlayerIntelligenceFortuneView = (EditText) mRootView.findViewById(R.id.intelligence_fortune);
        mPlayerWillpowerFortuneView = (EditText) mRootView.findViewById(R.id.willpower_fortune);
        mPlayerFellowshipFortuneView = (EditText) mRootView.findViewById(R.id.fellowship_fortune);
    }

    private void fillPlayerCharacteristicsFields() {
        Player player = PlayerContext.getPlayerInstance();

        int strength = player.getCharacteristics().getStrength();
        mPlayerStrengthView.setText(strength == 0 ? "" : String.valueOf(strength));
        int toughness = player.getCharacteristics().getToughness();
        mPlayerToughnessView.setText(toughness == 0 ? "" : String.valueOf(toughness));
        int agility = player.getCharacteristics().getAgility();
        mPlayerAgilityView.setText(agility == 0 ? "" : String.valueOf(agility));
        int intelligence = player.getCharacteristics().getIntelligence();
        mPlayerIntelligenceView.setText(intelligence == 0 ? "" : String.valueOf(intelligence));
        int willpower = player.getCharacteristics().getWillpower();
        mPlayerWillpowerView.setText(willpower == 0 ? "" : String.valueOf(willpower));
        int fellowship = player.getCharacteristics().getFellowship();
        mPlayerFellowshipView.setText(fellowship == 0 ? "" : String.valueOf(fellowship));

        int strengthFortune = player.getCharacteristics().getStrength_fortune();
        mPlayerStrengthFortuneView.setText(strengthFortune == 0 ? "" : String.valueOf(strengthFortune));
        int toughnessFortune = player.getCharacteristics().getToughness_fortune();
        mPlayerToughnessFortuneView.setText(toughnessFortune == 0 ? "" : String.valueOf(toughnessFortune));
        int agilityFortune = player.getCharacteristics().getAgility_fortune();
        mPlayerAgilityFortuneView.setText(agilityFortune == 0 ? "" : String.valueOf(agilityFortune));
        int intelligenceFortune = player.getCharacteristics().getIntelligence_fortune();
        mPlayerIntelligenceFortuneView.setText(intelligenceFortune == 0 ? "" : String.valueOf(intelligenceFortune));
        int willpowerFortune = player.getCharacteristics().getWillpower_fortune();
        mPlayerWillpowerFortuneView.setText(willpowerFortune == 0 ? "" : String.valueOf(willpowerFortune));
        int fellowshipFortune = player.getCharacteristics().getFellowship_fortune();
        mPlayerFellowshipFortuneView.setText(fellowshipFortune == 0 ? "" : String.valueOf(fellowshipFortune));
    }

    private void initPlayerCharacteristicsWatchers() {
        mPlayerStrengthView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.STRENGTH));
        mPlayerToughnessView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.TOUGHNESS));
        mPlayerAgilityView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.AGILITY));
        mPlayerIntelligenceView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.INTELLIGENCE));
        mPlayerWillpowerView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.WILLPOWER));
        mPlayerFellowshipView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.FELLOWSHIP));

        mPlayerStrengthFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.STRENGTH_FORTUNE));
        mPlayerToughnessFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.TOUGHNESS_FORTUNE));
        mPlayerAgilityFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.AGILITY_FORTUNE));
        mPlayerIntelligenceFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.INTELLIGENCE_FORTUNE));
        mPlayerWillpowerFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.WILLPOWER_FORTUNE));
        mPlayerFellowshipFortuneView.addTextChangedListener(new CharacteristicEditTextWatcher(Characteristic.FELLOWSHIP_FORTUNE));
    }
    //endregion

    //region Text fields setup
    private void initPlayerInformationFields() {
        mPlayerAgeView = (EditText) mRootView.findViewById(R.id.player_age);
        mPlayerCareerView = (EditText) mRootView.findViewById(R.id.player_career);
        mPlayerDescriptionView = (EditText) mRootView.findViewById(R.id.player_description);
        mPlayerExperienceView = (EditText) mRootView.findViewById(R.id.player_experience);
        mPlayerMaxExperienceView = (EditText) mRootView.findViewById(R.id.player_max_experience);
        mPlayerWoundsView = (EditText) mRootView.findViewById(R.id.player_wounds);
        mPlayerMaxWoundsView = (EditText) mRootView.findViewById(R.id.player_max_wounds);
        mPlayerCorruptionView = (EditText) mRootView.findViewById(R.id.player_corruption);
        mPlayerMaxCorruptionView = (EditText) mRootView.findViewById(R.id.player_max_corruption);
        mPlayerNameView = (EditText) mRootView.findViewById(R.id.player_name);
        mPlayerRaceView = (EditText) mRootView.findViewById(R.id.player_race);
        mPlayerRankView = (EditText) mRootView.findViewById(R.id.player_rank);
        mPlayerSizeView = (EditText) mRootView.findViewById(R.id.player_size);
    }

    private void fillPlayerInformationFields() {
        Player player = PlayerContext.getPlayerInstance();

        int age = player.getAge();
        mPlayerAgeView.setText(age == 0 ? "" : String.valueOf(age));

        mPlayerCareerView.setText(player.getCareer());

        mPlayerDescriptionView.setText(player.getDescription());

        int experience = player.getExperience();
        mPlayerExperienceView.setText(experience == 0 ? "" : String.valueOf(experience));
        int maxExperience = player.getMax_experience();
        mPlayerMaxExperienceView.setText(maxExperience == 0 ? "" : String.valueOf(maxExperience));

        int wounds = player.getWounds();
        mPlayerWoundsView.setText(wounds == 0 ? "" : String.valueOf(wounds));
        int maxWounds = player.getMax_wounds();
        mPlayerMaxWoundsView.setText(maxWounds == 0 ? "" : String.valueOf(maxWounds));

        int corruption = player.getCorruption();
        mPlayerCorruptionView.setText(corruption == 0 ? "" : String.valueOf(corruption));
        int maxCorruption = player.getMax_corruption();
        mPlayerMaxCorruptionView.setText(maxCorruption == 0 ? "" : String.valueOf(maxCorruption));

        mPlayerNameView.setText(player.getName());

        mPlayerRaceView.setText(player.getRace());

        int rank = player.getRank();
        mPlayerRankView.setText(rank == 0 ? "" : String.valueOf(rank));

        double size = player.getSize();
        mPlayerSizeView.setText(size == 0 ? "" : String.valueOf(size));
    }

    private void initPlayerInformationWatchers() {
        mPlayerAgeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.AGE));
        mPlayerCareerView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.CAREER));
        mPlayerDescriptionView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.DESCRIPTION));
        mPlayerExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.EXPERIENCE));
        mPlayerMaxExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.MAX_EXPERIENCE));
        mPlayerWoundsView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.WOUNDS));
        mPlayerMaxWoundsView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.MAX_WOUNDS));
        mPlayerCorruptionView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.CORRUPTION));
        mPlayerMaxCorruptionView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.MAX_CORRUPTION));
        mPlayerNameView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.NAME));
        mPlayerRaceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RACE));
        mPlayerRankView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RANK));
        mPlayerSizeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.SIZE));
    }
    //endregion
}
