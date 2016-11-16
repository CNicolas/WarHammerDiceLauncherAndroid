package com.whfrp3.tools.helpers;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ListView;

import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.model.player.skill.Skill;

/**
 * Created by cnicolas on 14/11/2016.
 */

public class SkillsListBinder {
    @BindingAdapter("app:items")
    public static void bindList(ListView view, ObservableArrayList<Skill> list) {
        SkillsListAdapter adapter = new SkillsListAdapter(list);
        view.setAdapter(adapter);
    }
}
