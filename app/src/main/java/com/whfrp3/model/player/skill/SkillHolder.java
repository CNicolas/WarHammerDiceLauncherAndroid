package com.whfrp3.model.player.skill;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * An item of the skillsListView.
 */
public class SkillHolder {
    /**
     * The position in the ListView.
     */
    private int position;
    /**
     * The Skill name.
     */
    private TextView mName;
    /**
     * The first checkbox representing the level 1.
     */
    private CheckBox mCheckbox1;
    /**
     * The second checkbox representing the level 2.
     */
    private CheckBox mCheckbox2;
    /**
     * The third checkbox representing the level 3.
     */
    private CheckBox mCheckbox3;

    //region Get & Set
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
    //endregion
}
