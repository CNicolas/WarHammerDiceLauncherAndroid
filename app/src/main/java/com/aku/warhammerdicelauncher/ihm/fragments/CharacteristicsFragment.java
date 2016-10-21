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
        mPlayerNameView.setFocusable(isInEdition);
        mPlayerNameView.setFocusableInTouchMode(isInEdition);
        mPlayerNameView.setClickable(isInEdition);

        mPlayerCareerView.setFocusable(isInEdition);
        mPlayerCareerView.setFocusableInTouchMode(isInEdition);
        mPlayerCareerView.setClickable(isInEdition);

        mPlayerRaceView.setFocusable(isInEdition);
        mPlayerRaceView.setFocusableInTouchMode(isInEdition);
        mPlayerRaceView.setClickable(isInEdition);

        mPlayerAgeView.setFocusable(isInEdition);
        mPlayerAgeView.setFocusableInTouchMode(isInEdition);
        mPlayerAgeView.setClickable(isInEdition);

        mPlayerSizeView.setFocusable(isInEdition);
        mPlayerSizeView.setFocusableInTouchMode(isInEdition);
        mPlayerSizeView.setClickable(isInEdition);

        mPlayerDescriptionView.setFocusable(isInEdition);
        mPlayerDescriptionView.setFocusableInTouchMode(isInEdition);
        mPlayerDescriptionView.setClickable(isInEdition);

        mPlayerRankView.setFocusable(isInEdition);
        mPlayerRankView.setFocusableInTouchMode(isInEdition);
        mPlayerRankView.setClickable(isInEdition);

        mPlayerExperienceView.setFocusable(isInEdition);
        mPlayerExperienceView.setFocusableInTouchMode(isInEdition);
        mPlayerExperienceView.setClickable(isInEdition);

        mPlayerMaxExperienceView.setFocusable(isInEdition);
        mPlayerMaxExperienceView.setFocusableInTouchMode(isInEdition);
        mPlayerMaxExperienceView.setClickable(isInEdition);
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
        mPlayerDescriptionView = (EditText) mRootView.findViewById(R.id.player_description);
        mPlayerExperienceView = (EditText) mRootView.findViewById(R.id.player_experience);
        mPlayerMaxExperienceView = (EditText) mRootView.findViewById(R.id.player_max_experience);
        mPlayerNameView = (EditText) mRootView.findViewById(R.id.player_name);
        mPlayerCareerView = (EditText) mRootView.findViewById(R.id.player_career);
        mPlayerRaceView = (EditText) mRootView.findViewById(R.id.player_race);
        mPlayerRankView = (EditText) mRootView.findViewById(R.id.player_rank);
        mPlayerSizeView = (EditText) mRootView.findViewById(R.id.player_size);
    }

    private void initPlayerInformationWatchers() {
        mPlayerAgeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.AGE));
        mPlayerDescriptionView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.DESCRIPTION));
        mPlayerExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.EXPERIENCE));
        mPlayerMaxExperienceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.MAX_EXPERIENCE));
        mPlayerNameView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.NAME));
        mPlayerCareerView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.CAREER));
        mPlayerRaceView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RACE));
        mPlayerRankView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.RANK));
        mPlayerSizeView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.SIZE));
    }
    //endregion
}
