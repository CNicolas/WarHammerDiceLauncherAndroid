package com.aku.warhammerdicelauncher.ihm;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * Created by cnicolas on 04/05/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)//For backward-compability
public class NumberScroller extends NumberPicker {
    public NumberScroller(Context context) {
        super(context);
    }

    public NumberScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributeSet(attrs);
    }

    public NumberScroller(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }

    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "minValue", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "maxValue", 0));
    }
}
