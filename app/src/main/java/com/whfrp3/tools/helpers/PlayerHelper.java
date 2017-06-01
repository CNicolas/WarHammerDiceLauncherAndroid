package com.whfrp3.tools.helpers;

import android.util.Log;

import com.whfrp3.BR;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.item.Item;
import com.whfrp3.model.item.Weapon;
import com.whfrp3.tools.WHFRP3Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Carries the current player, as a Singleton like.
 */
public abstract class PlayerHelper {

    public static void savePlayer(Player player) {
        if (player == null || !player.isUpdatable()) {
            return;
        }

        if (player.getId() == 0) {
            WHFRP3Application.getDatabase().getPlayerDao().insert(player);
        } else {
            WHFRP3Application.getDatabase().getPlayerDao().update(player);
        }

        Log.i("Player Context UPDATE", player.toString());
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

    public static List<String> getWeaponsName(List<Weapon> items) {
        List<String> res = new ArrayList<>();

        for (Item item : items) {
            res.add(item.getName());
        }

        return res;
    }

    public static void notifyBinding() {
        Player player = WHFRP3Application.getPlayer();

        player.notifyPropertyChanged(BR.player);
        player.notifyPropertyChanged(BR.currentEncumbrance);
        player.notifyPropertyChanged(BR.encumbranceColor);

        notifySkillBinding();
        notifyTalentBinding();
        notifyEquipmentBinding();
    }

    public static void notifySkillBinding() {
        Player player = WHFRP3Application.getPlayer();

        //player.notifyPropertyChanged(BR.playerSkill);
        player.notifyPropertyChanged(BR.skills);
        player.notifyPropertyChanged(BR.specializations);
        player.notifyPropertyChanged(BR.specialization);
        player.notifyPropertyChanged(BR.specialized);
        player.notifyPropertyChanged(BR.skill);
    }

    public static void notifyTalentBinding() {
        Player player = WHFRP3Application.getPlayer();

        //player.notifyPropertyChanged(BR.playerTalent);
        player.notifyPropertyChanged(BR.talents);
        player.notifyPropertyChanged(BR.exhausted);
        player.notifyPropertyChanged(BR.equipped);
    }

    public static void notifyEquipmentBinding() {
        Player player = WHFRP3Application.getPlayer();

        player.notifyPropertyChanged(BR.equippedWeapons);
        player.notifyPropertyChanged(BR.fullDefenseAmount);
        player.notifyPropertyChanged(BR.fullSoakAmount);
    }
}
