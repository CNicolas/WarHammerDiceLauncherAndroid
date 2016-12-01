package com.whfrp3.tools.helpers;

import android.util.Log;

import com.whfrp3.BR;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.Skill;
import com.whfrp3.tools.WHFRP3Application;

import java.util.List;

/**
 * Carries the current player, as a Singleton like.
 */
public abstract class PlayerHelper {

    public static void savePlayer(Player player) {
        if (player == null || !player.isUpdatable()) {
            return;
        }

        if (player.getSkills().size() == 0) {
            List<Skill> basicSkills = SkillsHelper.createBasicSkills();
            player.setSkills(basicSkills);
        }

        if (player.getId() == 0) {
            WHFRP3Application.getDatabase().getPlayerDao().insert(player);
        } else {
            WHFRP3Application.getDatabase().getPlayerDao().update(player);
        }

        Log.e("Player Context UPDATE", player.toString());
    }

    public static void changeStress(Player player, int change) {
        int newStressValue = player.getStress() + change;
        if (newStressValue >= 0 && newStressValue <= player.getMaxStressBeforeComa()) {
            player.setStress(player.getStress() + change);
        }
    }

    public static void changeExertion(Player player, int change) {
        int newExertionValue = player.getExertion() + change;
        if (newExertionValue >= 0 && newExertionValue <= player.getMaxExertionBeforeComa()) {
            player.setExertion(player.getExertion() + change);
        }
    }

    public static void notifyBinding() {
        Player player = WHFRP3Application.getPlayer();

        player.notifyPropertyChanged(BR.currentEncumbrance);
        player.notifyPropertyChanged(BR.encumbranceColor);
        notifyEquipmentBinding();
    }

    public static void notifyEquipmentBinding() {
        Player player = WHFRP3Application.getPlayer();

        player.notifyPropertyChanged(BR.equippedWeapons);
        player.notifyPropertyChanged(BR.fullDefenseAmount);
        player.notifyPropertyChanged(BR.fullSoakAmount);
    }
}
