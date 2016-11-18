package com.whfrp3.ihm.components;

import android.content.Context;
import android.util.AttributeSet;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Extends DiscreteBar to bind min and max
 */
public class BindableDiscreteSeekBar extends DiscreteSeekBar {
    public BindableDiscreteSeekBar(Context context) {
        super(context);
    }

    public BindableDiscreteSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindableDiscreteSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDsb_max(int max) {
        setMax(max);
    }

    public void setDsb_min(int min) {
        setMin(min);
    }
}
