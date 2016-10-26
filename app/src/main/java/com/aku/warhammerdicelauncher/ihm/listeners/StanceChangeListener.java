package com.aku.warhammerdicelauncher.ihm.listeners;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;

import com.aku.warhammerdicelauncher.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by cnicolas on 26/10/2016.
 */
public class StanceChangeListener implements DiscreteSeekBar.OnProgressChangeListener {
    private final int mConservativeColor;
    private final int mRecklessColor;
    private final int mNeutralColor;

    public StanceChangeListener(Context context) {
        mConservativeColor = ContextCompat.getColor(context, R.color.conservative);
        mRecklessColor = ContextCompat.getColor(context, R.color.reckless);
        mNeutralColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
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
