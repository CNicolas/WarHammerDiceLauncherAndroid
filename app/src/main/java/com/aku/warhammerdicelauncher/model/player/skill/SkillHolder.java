package com.aku.warhammerdicelauncher.model.player.skill;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by cnicolas on 25/10/2016.
 */

public class SkillHolder {
    private int position;
    private TextView mName;
    private CheckBox mCheckbox1;
    private CheckBox mCheckbox2;
    private CheckBox mCheckbox3;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TextView getSkillName() {
        return mName;
    }

    public void setSkillName(TextView skillName) {
        this.mName = skillName;
    }

    public CheckBox getCheckbox1() {
        return mCheckbox1;
    }

    public void setCheckbox1(CheckBox checkbox1) {
        this.mCheckbox1 = checkbox1;
    }

    public CheckBox getCheckbox2() {
        return mCheckbox2;
    }

    public void setCheckbox2(CheckBox checkbox2) {
        this.mCheckbox2 = checkbox2;
    }

    public CheckBox getCheckbox3() {
        return mCheckbox3;
    }

    public void setCheckbox3(CheckBox checkbox3) {
        this.mCheckbox3 = checkbox3;
    }
}
