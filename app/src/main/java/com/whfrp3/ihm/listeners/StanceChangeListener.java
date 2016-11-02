package com.whfrp3.ihm.listeners;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.whfrp3.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class StanceChangeListener implements DiscreteSeekBar.OnProgressChangeListener {
    private final int mConservativeColor;
    private final int mRecklessColor;
    private final int mNeutralColor;
    private final TextView mCurrentStanceTextView;

    public StanceChangeListener(Context context, TextView currentStanceTextView) {
        mConservativeColor = ContextCompat.getColor(context, R.color.conservative);
        mRecklessColor = ContextCompat.getColor(context, R.color.reckless);
        mNeutralColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);

        mCurrentStanceTextView = currentStanceTextView;
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

            String format = mCurrentStanceTextView.getResources().getString(R.string.stance_conservative_format);
            mCurrentStanceTextView.setText(String.format(format, value * -1));
            mCurrentStanceTextView.setTextColor(mConservativeColor);
        } else if (value > 0) {
            seekBar.setScrubberColor(mRecklessColor);
            seekBar.setThumbColor(ColorStateList.valueOf(mRecklessColor), mRecklessColor);

            String format = mCurrentStanceTextView.getResources().getString(R.string.stance_reckless_format);
            mCurrentStanceTextView.setText(String.format(format, value));
            mCurrentStanceTextView.setTextColor(mRecklessColor);
        } else {
            seekBar.setScrubberColor(mNeutralColor);
            seekBar.setThumbColor(ColorStateList.valueOf(mNeutralColor), mNeutralColor);

            String text = mCurrentStanceTextView.getResources().getString(R.string.neutral);
            mCurrentStanceTextView.setText(text);
            mCurrentStanceTextView.setTextColor(mNeutralColor);
        }
    }
}
