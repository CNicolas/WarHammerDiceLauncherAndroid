package com.whfrp3.ihm.components;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;

import com.whfrp3.R;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.WHFRP3Application;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class EncumbranceBar extends BindableDiscreteSeekBar implements DiscreteSeekBar.OnProgressChangeListener {

    public EncumbranceBar(Context context) {
        super(context);
        if (!isInEditMode()) {
            setColorFromPlayerEncumbrance();
        }

    }

    public EncumbranceBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            setColorFromPlayerEncumbrance();
        }

    }

    public EncumbranceBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            setColorFromPlayerEncumbrance();
        }
    }


    private void setColorFromPlayerEncumbrance() {
        Context context = WHFRP3Application.getAppContext();
        Player player = WHFRP3Application.getPlayer();
        int value = player.getCurrentEncumbrance();

        int color;
        if (value < player.getEncumbranceOverload()) {
            color = ContextCompat.getColor(context, R.color.conservative);
        } else if (value < player.getEncumbranceMax()) {
            color = ContextCompat.getColor(context, R.color.orange);
        } else {
            color = ContextCompat.getColor(context, R.color.reckless);
        }

        setScrubberColor(color);
//        setRippleColor(color);
    }

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        setColorFromPlayerEncumbrance();
        Log.d("ENCUMBRANCE_BAR", "CHANGED");
    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

    }
}
