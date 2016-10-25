package com.aku.warhammerdicelauncher.ihm.listeners;

import android.view.View;
import android.widget.CheckBox;

import com.aku.warhammerdicelauncher.model.player.skill.Skill;
import com.aku.warhammerdicelauncher.model.player.skill.SkillHolder;
import com.aku.warhammerdicelauncher.tools.PlayerContext;

/**
 * Created by cnicolas on 25/10/2016.
 */

public class SkillLevelClickListener implements View.OnClickListener {
    private final Skill mSkill;
    private final CheckBox mCheckbox1;
    private final CheckBox mCheckbox2;
    private final CheckBox mCheckbox3;


    public SkillLevelClickListener(Skill skill, SkillHolder holder) {
        mSkill = skill;
        mCheckbox1 = holder.getCheckbox1();
        mCheckbox2 = holder.getCheckbox2();
        mCheckbox3 = holder.getCheckbox3();
    }

    @Override
    public void onClick(View v) {
        int newLevel = 0;

        String tagString = v.getTag().toString();
        int level = Integer.parseInt(tagString);

        switch (level) {
            case 1:
                if (mCheckbox1.isChecked()) {
                    mCheckbox1.setChecked(true);
                    newLevel = 1;
                } else if (mCheckbox2.isChecked()) {
                    mCheckbox1.setChecked(true);
                }
                mCheckbox2.setChecked(false);
                mCheckbox3.setChecked(false);
                break;
            case 2:
                if (mCheckbox2.isChecked()) {
                    mCheckbox1.setChecked(true);
                    mCheckbox2.setChecked(true);
                    newLevel = 2;
                } else if (mCheckbox3.isChecked()) {
                    mCheckbox2.setChecked(true);
                }
                mCheckbox3.setChecked(false);
                break;
            case 3:
                if (mCheckbox3.isChecked()) {
                    mCheckbox1.setChecked(true);
                    mCheckbox2.setChecked(true);
                    mCheckbox3.setChecked(true);
                    newLevel = 3;
                }
                break;
        }

        mSkill.setLevel(newLevel);
        PlayerContext.getPlayerInstance().setSkillLevel(mSkill, newLevel);
        PlayerContext.updatePlayer();
    }
}