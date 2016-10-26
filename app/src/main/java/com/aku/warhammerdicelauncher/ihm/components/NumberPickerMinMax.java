package com.aku.warhammerdicelauncher.ihm.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * Extension of the standard NumberPicker, providing the xml attributes minValue and maxValue.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NumberPickerMinMax extends NumberPicker {

    //region Constructors matching super
    public NumberPickerMinMax(Context context) {
        super(context);
    }

    public NumberPickerMinMax(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public NumberPickerMinMax(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }
    //endregion

    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "minValue", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "maxValue", 0));
    }
}
