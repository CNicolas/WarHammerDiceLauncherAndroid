package com.whfrp3.ihm.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.CheckBox;

import com.whfrp3.ihm.activities.LaunchActivity;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;


public class SkillHandlers implements IPlayerActivityConstants {
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

    /**
     * Start a new LaunchActivity with(out) a bundle and add it to the TaskStack.
     *
     * @param skill
     */
    public void startLaunchActivity(Skill skill) {
        Activity currentActivity = WHFRP3Application.getActivity();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SKILL_BUNDLE_TAG, skill);
        bundle.putInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, ADVENTURE_FRAGMENT_POSITION);

        Intent launchIntent = new Intent(currentActivity, LaunchActivity.class);
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(currentActivity);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        currentActivity.startActivityForResult(launchIntent, LAUNCH_REQUEST);
    }
}
