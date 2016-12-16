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
import com.whfrp3.model.Specialization;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.ArrayList;
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

    public void openSpecializationPopup(final PlayerSkill playerSkill) {
        final List<Specialization> specializations = SpecializationHelper.getInstance().getSpecializationsBySkillId(playerSkill.getSkill().getId());
        List<String> specializationsName = SpecializationHelper.getInstance().getSpecializationsName(specializations);
        final boolean[] checkedSpecializations = new boolean[specializations.size()];

        AlertDialog.Builder specializationsDialogBuilder = new AlertDialog.Builder(WHFRP3Application.getActivity());
        specializationsDialogBuilder.setTitle(playerSkill.getSkill().getName());
        specializationsDialogBuilder.setMultiChoiceItems(specializationsName.toArray(new String[]{}), checkedSpecializations, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checkedSpecializations[i] = b;
            }
        });
        specializationsDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> newSpecs = new ArrayList<>();
                for (int i = 0; i < specializations.size(); i++) {
                    if (checkedSpecializations[i]) {
                        newSpecs.add(specializations.get(i).getName());
                    }
                }
                dialog.dismiss();
                ToastNotification.info(newSpecs.toString());
            }
        });

        specializationsDialogBuilder.show();
    }

    //region Launch Skill
    public void launchSkill(final PlayerSkill playerSkill) {
        final Weapon[] weapon = {null};

        if (playerSkill.getSkill().isFightSkill()) {
            List<Weapon> weapons;
            if (playerSkill.getSkill().isWeaponSkill()) {
                weapons = WHFRP3Application.getPlayer().getMeleeUsableWeapons();
            } else {
                weapons = WHFRP3Application.getPlayer().getDistanceUsableWeapons();
            }

            if (weapons.size() > 1) {
                List<String> weaponsName = PlayerHelper.getWeaponsName(weapons);
                final AlertDialog.Builder weaponDialogBuilder = new AlertDialog.Builder(WHFRP3Application.getActivity());
                weaponDialogBuilder.setTitle(playerSkill.getSkill().getName());

                final List<Weapon> finalWeapons = weapons;
                weaponDialogBuilder.setSingleChoiceItems(weaponsName.toArray(new String[]{}), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        weapon[0] = finalWeapons.get(which);
                    }
                });
                weaponDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startLaunchActivity(playerSkill, weapon[0]);
                        dialog.dismiss();
                    }
                });

                weaponDialogBuilder.show();
            } else if (weapons.size() == 1) {
                startLaunchActivity(playerSkill, weapon[0]);
            }
        } else {
            startLaunchActivity(playerSkill, null);
        }
    }

    /**
     * Start a new LaunchActivity with(out) a bundle and add it to the TaskStack.
     *
     * @param playerSkill the skill to launch
     */
    private void startLaunchActivity(PlayerSkill playerSkill, @Nullable Weapon weapon) {
        Activity currentActivity = WHFRP3Application.getActivity();

        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER_SKILL_BUNDLE_TAG, playerSkill);
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
    //endregion
}
