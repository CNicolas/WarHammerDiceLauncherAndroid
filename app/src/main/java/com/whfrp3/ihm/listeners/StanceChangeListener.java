package com.whfrp3.ihm.listeners;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.WHFRP3Application;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class StanceChangeListener implements DiscreteSeekBar.OnProgressChangeListener {
    private final int mConservativeColor;
    private final int mRecklessColor;
    private final int mNeutralColor;
    private final TextView mCurrentStanceTextView;

    public StanceChangeListener(TextView currentStanceTextView) {
        Context context = WHFRP3Application.getAppContext();
        mConservativeColor = ContextCompat.getColor(context, R.color.conservative);
        mRecklessColor = ContextCompat.getColor(context, R.color.reckless);
        mNeutralColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);

        mCurrentStanceTextView = currentStanceTextView;
    }

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        changeColor(seekBar, value);
        updateCurrentStance(value);
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

    private void updateCurrentStance(int value) {
        Player player = WHFRP3Application.getPlayer();
        if (value < 0) {
            String format = mCurrentStanceTextView.getResources().getString(R.string.stance_conservative_format);
            mCurrentStanceTextView.setText(String.format(format, (-1 * value)));
            mCurrentStanceTextView.setTextColor(mConservativeColor);

            player.setConservative(-1 * value);
            player.setReckless(0);
        } else if (value > 0) {
            String format = mCurrentStanceTextView.getResources().getString(R.string.stance_reckless_format);
            mCurrentStanceTextView.setText(String.format(format, value));
            mCurrentStanceTextView.setTextColor(mRecklessColor);

            player.setConservative(0);
            player.setReckless(value);
        } else {
            String text = mCurrentStanceTextView.getResources().getString(R.string.neutral);
            mCurrentStanceTextView.setText(text);
            mCurrentStanceTextView.setTextColor(mNeutralColor);

            player.setConservative(0);
            player.setReckless(0);
        }
    }
}
