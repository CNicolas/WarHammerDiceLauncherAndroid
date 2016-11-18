package com.whfrp3.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.util.Log;
import android.widget.TextView;

/**
 * Automatically transform anroid:text attribute to and from String.
 */
public abstract class AbstractBindingModel extends BaseObservable implements IModel {
    @BindingAdapter("android:text")
    public static void setText(TextView view, int value) {
        String res = value == 0 ? "" : String.valueOf(value);
        if (!res.equals(view.getText().toString())) {
            view.setText(res);
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view) {
        String content = null;
        try {
            content = view.getText().toString();
            return Integer.parseInt(content);
        } catch (NumberFormatException nfe) {
            Log.e("BindingModel", "Bad content '" + content + "'", nfe);
        }
        return 0;
    }
}
