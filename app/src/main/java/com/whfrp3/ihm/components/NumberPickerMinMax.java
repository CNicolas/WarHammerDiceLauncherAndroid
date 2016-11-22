package com.whfrp3.ihm.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import com.whfrp3.R;

/**
 * Extension of the standard NumberPicker, providing the xml attributes minValue and maxValue.
 */
public class NumberPickerMinMax extends NumberPicker {

    //region Constructors matching super
    public NumberPickerMinMax(Context context) {
        super(context);
    }

    public NumberPickerMinMax(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(context, attrs);
    }

    public NumberPickerMinMax(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(context, attrs);
    }
    //endregion

    private void processAttributeSet(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NumberPickerMinMax, 0, 0);

        setMinValue(a.getInteger(R.styleable.NumberPickerMinMax_minValue, getContext().getResources().getInteger(R.integer.zero)));
        setMaxValue(a.getInteger(R.styleable.NumberPickerMinMax_maxValue, getContext().getResources().getInteger(R.integer.max_dice_number_picker)));
    }
}
