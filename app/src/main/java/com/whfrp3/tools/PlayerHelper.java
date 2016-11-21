package com.whfrp3.tools;

import android.util.Log;

import com.whfrp3.database.dao.CharacteristicsDao;
import com.whfrp3.database.dao.PlayerDao;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;
import com.whfrp3.tools.helpers.SkillsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Carries the current player, as a Singleton like.
 */
public abstract class PlayerHelper {

    private static List<OnPlayerUpdateListener> mListeners;

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

        notifyListeners();

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

    //region Listeners
    public static void registerPlayerUpdateListener(OnPlayerUpdateListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        mListeners.add(listener);
    }

    private static void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (OnPlayerUpdateListener listener : mListeners) {
                listener.onPlayerUpdate();
            }
        }
    }
    //endregion
}
