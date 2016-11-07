package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentCharacteristicsBinding;
import com.whfrp3.tools.PlayerContext;

/**
 * The CharacteristicsFragment.
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
    private EditText mPlayerMaxExperienceView;
    private EditText mPlayerMaxWoundsView;
    private EditText mPlayerMaxCorruptionView;
    private EditText mPlayerMaxConservativeView;
    private EditText mPlayerMaxRecklessView;
    private EditText mPlayerNameView;
    private EditText mPlayerRaceView;
    private EditText mPlayerRankView;
    private EditText mPlayerSizeView;
    //endregion

    private View mRootView;
    private Boolean mIsInEdition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        mRootView = inflater.inflate(R.layout.fragment_characteristics, container, false);
        setHasOptionsMenu(true);

        initPlayerCharacteristicsFields();
        initPlayerInformationFields();

        FragmentCharacteristicsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characteristics, container, false);
        binding.setPlayer(PlayerContext.getPlayerInstance());
        binding.setCarac(PlayerContext.getPlayerInstance().getCharacteristics());

        changeEdition();

        long difference = System.currentTimeMillis() - startTime;
        Log.d("CharacteristicsFragment", String.format("%d = %d", startTime, difference));
//        return mRootView;
        return binding.getRoot();
    }

    /**
     * Update the UI according to the edition boolean from context.
     */
    public void changeEdition() {
        boolean isInEdition = PlayerContext.isInEdition();

        updateCharacteristicsEdition(isInEdition);
        updateCharacteristicsFortuneEdition(isInEdition);
        updatePlayerInformationEdition(isInEdition);
    }

    /**
     * Change the editable state of the blue characteristics EditTexts.
     *
     * @param isInEdition the edition state.
     */
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

    /**
     * Change the editable state of the white characteristics fortune EditTexts.
     *
     * @param isInEdition the edition state.
     */
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

    /**
     * Change the editable state of the player information EditTexts.
     *
     * @param isInEdition the edition state.
     */
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

        mPlayerMaxConservativeView.setFocusable(isInEdition);
        mPlayerMaxConservativeView.setFocusableInTouchMode(isInEdition);
        mPlayerMaxConservativeView.setClickable(isInEdition);

        mPlayerMaxRecklessView.setFocusable(isInEdition);
        mPlayerMaxRecklessView.setFocusableInTouchMode(isInEdition);
        mPlayerMaxRecklessView.setClickable(isInEdition);
    }

    //region Characteristics Setup

    /**
     * Initialize the player characteristics fields.
     */
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
    //endregion

    //region Text fields setup

    /**
     * Initialize the player information fields.
     */
    private void initPlayerInformationFields() {
        mPlayerAgeView = (EditText) mRootView.findViewById(R.id.player_age);
        mPlayerCareerView = (EditText) mRootView.findViewById(R.id.player_career);
        mPlayerDescriptionView = (EditText) mRootView.findViewById(R.id.player_description);
        mPlayerMaxExperienceView = (EditText) mRootView.findViewById(R.id.player_max_experience);
        mPlayerMaxWoundsView = (EditText) mRootView.findViewById(R.id.player_max_wounds);
        mPlayerMaxCorruptionView = (EditText) mRootView.findViewById(R.id.player_max_corruption);
        mPlayerMaxConservativeView = (EditText) mRootView.findViewById(R.id.player_max_conservative);
        mPlayerMaxRecklessView = (EditText) mRootView.findViewById(R.id.player_max_reckless);
        mPlayerNameView = (EditText) mRootView.findViewById(R.id.player_name);
        mPlayerRaceView = (EditText) mRootView.findViewById(R.id.player_race);
        mPlayerRankView = (EditText) mRootView.findViewById(R.id.player_rank);
        mPlayerSizeView = (EditText) mRootView.findViewById(R.id.player_size);
    }

    //endregion
}
