package com.aku.warhammerdicelauncher.ihm.tools;

import android.view.View;
import android.widget.CheckBox;

import com.aku.warhammerdicelauncher.ihm.adapters.SkillsListAdapter;
import com.aku.warhammerdicelauncher.model.player.Skill;
import com.aku.warhammerdicelauncher.tools.PlayerContext;

/**
 * Created by cnicolas on 25/10/2016.
 */

public class SkillLevelClickListener implements View.OnClickListener {
    private final Skill mSkill;
    private final CheckBox mSkillLevel1;
    private final CheckBox mSkillLevel2;
    private final CheckBox mSkillLevel3;


    public SkillLevelClickListener(Skill skill, SkillsListAdapter.SkillHolder holder) {
        mSkill = skill;
        mSkillLevel1 = holder.skillLevel1;
        mSkillLevel2 = holder.skillLevel2;
        mSkillLevel3 = holder.skillLevel3;
    }

    @Override
    public void onClick(View v) {
        int newLevel = 0;

        String tagString = v.getTag().toString();
        int level = Integer.parseInt(tagString);

        switch (level) {
            case 1:
                if (mSkillLevel1.isChecked()) {
                    mSkillLevel1.setChecked(true);
                    newLevel = 1;
                } else if (mSkillLevel2.isChecked()) {
                    mSkillLevel1.setChecked(true);
                }
                mSkillLevel2.setChecked(false);
                mSkillLevel3.setChecked(false);
                break;
            case 2:
                if (mSkillLevel2.isChecked()) {
                    mSkillLevel1.setChecked(true);
                    mSkillLevel2.setChecked(true);
                    newLevel = 2;
                } else if (mSkillLevel3.isChecked()) {
                    mSkillLevel2.setChecked(true);
                }
                mSkillLevel3.setChecked(false);
                break;
            case 3:
                if (mSkillLevel3.isChecked()) {
                    mSkillLevel1.setChecked(true);
                    mSkillLevel2.setChecked(true);
                    mSkillLevel3.setChecked(true);
                    newLevel = 3;
                }
                break;
        }

        mSkill.setLevel(newLevel);
        PlayerContext.getPlayerInstance().setSkillLevel(mSkill, newLevel);
    }
}