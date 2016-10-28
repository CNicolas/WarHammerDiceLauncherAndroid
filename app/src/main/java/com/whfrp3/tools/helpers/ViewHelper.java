package com.whfrp3.tools.helpers;

import android.view.View;
import android.widget.TextView;

import com.whfrp3.R;

/**
 * This helper provide some convenient functions for view management.
 */
public final class ViewHelper {

    /**
     * Private Constructor.
     */
    private ViewHelper() {
        // Do nothing
    }

    /**
     * Search the TextView with the provided id in the parent view and set its value.
     *
     * @param parentView Parent view.
     * @param id         TextView id.
     * @param value      New TextView value.
     */
    public static void setTextViewValue(View parentView, int id, Object value) {
        TextView view = (TextView) parentView.findViewById(id);
        view.setText(String.valueOf(value));
    }

    /**
     * Search the TextView with the provided id in the parent view and set its value id.
     *
     * @param parentView Parent view.
     * @param id         TextView id.
     * @param valueId    New TextView value id.
     */
    public static void setTextViewValueId(View parentView, int id, int valueId) {
        TextView view = (TextView) parentView.findViewById(id);
        view.setText(valueId);
    }

    /**
     * Search de View with the provided id in the parent viw and set its visibility.
     *
     * @param parentView Parent view.
     * @param id         View id.
     * @param visibility New visibility.
     */
    public static void setViewVisibility(View parentView, int id, int visibility) {
        // Visibility values filter
        if (visibility != View.VISIBLE && visibility != View.GONE && visibility != View.INVISIBLE) {
            return;
        }

        View view = parentView.findViewById(id);
        view.setVisibility(visibility);
    }
}
