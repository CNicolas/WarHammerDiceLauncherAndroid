package com.aku.warhammerdicelauncher.ihm.fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.helpers.OnPlayerUpdateListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment implements OnPlayerUpdateListener {
    private View mRootView;
    private DiscreteSeekBar mPlayerStance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_adventure, container, false);

        mPlayerStance = (DiscreteSeekBar) mRootView.findViewById(R.id.player_stance);
        mPlayerStance.setMin(-1 * PlayerContext.getPlayerInstance().getMax_conservative());
        mPlayerStance.setMax(PlayerContext.getPlayerInstance().getMax_reckless());
        mPlayerStance.setOnProgressChangeListener(new StanceChangeListener());

        return mRootView;
    }

    @Override
    public void onPlayerUpdate() {
        mPlayerStance.setMin(-1 * PlayerContext.getPlayerInstance().getMax_conservative());
        mPlayerStance.setMax(PlayerContext.getPlayerInstance().getMax_reckless());
    }


    private class StanceChangeListener implements DiscreteSeekBar.OnProgressChangeListener {
        private final int mConservativeColor;
        private final int mRecklessColor;
        private final int mNeutralColor;

        public StanceChangeListener() {
            mConservativeColor = ContextCompat.getColor(getActivity(), R.color.conservative);
            mRecklessColor = ContextCompat.getColor(getActivity(), R.color.reckless);
            mNeutralColor = ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark);
        }

        @Override
        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
            changeColor(seekBar, value);
        }

        @Override
        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
        }

        private void changeColor(DiscreteSeekBar seekBar, int value) {
            if (value < 0) {
                seekBar.setScrubberColor(mConservativeColor);
                seekBar.setThumbColor(ColorStateList.valueOf(mConservativeColor), mConservativeColor);
            } else if (value > 0) {
                seekBar.setScrubberColor(mRecklessColor);
                seekBar.setThumbColor(ColorStateList.valueOf(mRecklessColor), mRecklessColor);
            } else {
                seekBar.setScrubberColor(mNeutralColor);
                seekBar.setThumbColor(ColorStateList.valueOf(mNeutralColor), mNeutralColor);
            }
        }
    }
}
