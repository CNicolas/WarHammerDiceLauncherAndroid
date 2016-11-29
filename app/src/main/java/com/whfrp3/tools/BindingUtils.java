package com.whfrp3.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableArrayList;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.ihm.adapters.WeaponsListAdapter;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.model.player.skill.Skill;

import java.util.List;

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

    @BindingAdapter("skills")
    public static void bindSkills(ListView view, ObservableArrayList<Skill> list) {
        SkillsListAdapter adapter = new SkillsListAdapter(list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("weapons")
    public static void bindWeapons(ListView view, List<Weapon> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WeaponsListAdapter adapter = new WeaponsListAdapter(inflater, list);
        view.setAdapter(adapter);
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
