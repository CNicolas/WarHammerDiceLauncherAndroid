package com.whfrp3.ihm.listeners;

import android.util.Log;
import android.widget.CheckBox;

import com.whfrp3.model.player.skill.Skill;


public class SkillHandlers {
    public void onLevelSelected(Skill skill, int level, CheckBox skillLevel1, CheckBox skillLevel2, CheckBox skillLevel3) {

        int newLevel = 0;
        switch (level) {
            case 1:
                if (skillLevel1.isChecked()) { // Checkbox 1 checked
                    newLevel = 1;
                } else if (skillLevel2.isChecked()) { // Checkbox 1 unchecked but Checkbox 2 checked
                    skillLevel1.setChecked(true);
                    newLevel = 1;
                }
                skillLevel2.setChecked(false);
                skillLevel3.setChecked(false);
                break;
            case 2:
                newLevel = 1;
                if (skillLevel2.isChecked()) { // Checkbox 2 checked
                    skillLevel1.setChecked(true);
                    newLevel = 2;
                } else if (skillLevel3.isChecked()) { // Checkbox 2 unchecked but Checkbox 3 checked
                    skillLevel2.setChecked(true); // Check instead of uncheck
                    newLevel = 2;
                }
                skillLevel3.setChecked(false);
                break;
            case 3:
                newLevel = 2;
                if (skillLevel3.isChecked()) {// Checkbox 3 checked
                    skillLevel1.setChecked(true);
                    skillLevel2.setChecked(true);
                    newLevel = 3;
                }
                break;
        }

        skill.setLevel(newLevel);
        Log.w("SKILL", skill.toString());
    }
}
