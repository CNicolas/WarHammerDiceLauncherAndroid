package com.whfrp3.model.player.skill;

import android.databinding.ObservableArrayList;

import java.util.List;

/**
 * Created by cnicolas on 14/11/2016.
 */

public class SkillsList {
    public ObservableArrayList<Skill> list = new ObservableArrayList<>();

    public SkillsList(List<Skill> skillList) {
        list.addAll(skillList);
    }
}
