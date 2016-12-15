package com.whfrp3.ihm.listeners;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.CheckBox;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.LaunchActivity;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;

import java.util.List;

public class PlayerSkillHandlers implements IPlayerActivityConstants {
    public void onLevelSelected(PlayerSkill playerSkill, int level, CheckBox skillLevel1, CheckBox skillLevel2, CheckBox skillLevel3) {

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

        playerSkill.setLevel(newLevel);
        Log.w("SKILL", playerSkill.toString());
    }

    public void launchSkill(final PlayerSkill skill) {
        final Weapon[] weapon = {null};

        if (skill.isFightSkill()) {
            List<Weapon> weapons;
            if (skill.isWeaponSkill()) {
                weapons = WHFRP3Application.getPlayer().getMeleeUsableWeapons();
            } else {
                weapons = WHFRP3Application.getPlayer().getDistanceUsableWeapons();
            }

            if (weapons.size() > 1) {
                List<String> items = PlayerHelper.getWeaponsName(weapons);
                final AlertDialog.Builder rangeDialogBuilder = new AlertDialog.Builder(WHFRP3Application.getActivity());
                rangeDialogBuilder.setTitle(skill.getName());

                final List<Weapon> finalWeapons = weapons;
                rangeDialogBuilder.setSingleChoiceItems(items.toArray(new String[]{}), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        weapon[0] = finalWeapons.get(which);
                    }
                });
                rangeDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startLaunchActivity(skill, weapon[0]);
                        dialog.dismiss();
                    }
                });

                rangeDialogBuilder.show();
            } else if (weapons.size() == 1) {
                startLaunchActivity(skill, weapon[0]);
            }
        } else {
            startLaunchActivity(skill, null);
        }
    }

    /**
     * Start a new LaunchActivity with(out) a bundle and add it to the TaskStack.
     *
     * @param playerSkill the skill to launch
     */
    public void startLaunchActivity(PlayerSkill playerSkill) {
        Activity currentActivity = WHFRP3Application.getActivity();

        Bundle bundle = new Bundle();
        bundle.putSerializable(SKILL_BUNDLE_TAG, playerSkill);
        bundle.putInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, ADVENTURE_FRAGMENT_POSITION);
        if (weapon != null) {
            bundle.putSerializable(WEAPON_BUNDLE_TAG, weapon);
        }

        Intent launchIntent = new Intent(currentActivity, LaunchActivity.class);
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(currentActivity);
        stackBuilder.addParentStack(LaunchActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        currentActivity.startActivityForResult(launchIntent, LAUNCH_REQUEST);
    }
}
