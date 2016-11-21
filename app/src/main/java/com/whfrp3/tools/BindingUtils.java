package com.whfrp3.tools;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.util.Log;
import android.widget.TextView;

/**
 * Binding utils.
 */
public class BindingUtils {

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

    /**
     * Return the string corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return String corresponding to the resource id.
     */
    public static String string(int resourceId) {
        return WHFRP3Application.getResourceString(resourceId);
    }
}
