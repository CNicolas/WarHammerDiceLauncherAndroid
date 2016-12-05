package com.whfrp3.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.ihm.adapters.WeaponsListAdapter;
import com.whfrp3.model.player.Skill;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.List;

/**
 * Binding utils.
 */
public class BindingUtils {

    //region Conversion
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
    //endregion

    //region Custom binding attributes
    @BindingAdapter("skills")
    public static void bindSkills(ListView view, List<Skill> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SkillsListAdapter adapter = new SkillsListAdapter(inflater, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("weapons")
    public static void bindWeapons(ListView view, List<Weapon> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WeaponsListAdapter adapter = new WeaponsListAdapter(inflater, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("android:typeface")
    public static void setTypeface(TextView v, String style) {
        switch (style) {
            case "bold":
                v.setTypeface(null, Typeface.BOLD);
                break;
            default:
                v.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }
    //endregion

    /**
     * Return the string corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return String corresponding to the resource id.
     */
    public static String string(int resourceId) {
        return WHFRP3Application.getResourceString(resourceId);
    }

    /**
     * Return the drawable corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return Drawable corresponding to the resource id.
     */
    public static Drawable drawable(int resourceId) {
        return WHFRP3Application.getResourceDrawable(resourceId);
    }

    /**
     * Return the drawable corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return Drawable corresponding to the resource id.
     */
    public static int color(int resourceId) {
        return WHFRP3Application.getResourceColor(resourceId);
    }
}
