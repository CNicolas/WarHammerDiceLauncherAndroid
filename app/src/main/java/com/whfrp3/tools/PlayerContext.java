package com.whfrp3.tools;

import android.content.Context;
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
public abstract class PlayerContext {
    private static Player mPlayer;
    private static boolean isInEdition;

    private static List<OnPlayerUpdateListener> mListeners;

    private static PlayerDao mPlayerDao;
    private static CharacteristicsDao mCharacteristicsDao;
    private static Context mContext;

    //region Player
    public static Player getPlayerInstance() {
        return mPlayer;
    }

    public static Player initEmptyPlayer() {
        mPlayer = new Player();
        return mPlayer;
    }

    public static void updatePlayer() {
        if (mPlayer == null || !mPlayer.isUpdatable()) {
            return;
        }

        if (mPlayer.getSkills().size() == 0) {
            List<Skill> basicSkills = SkillsHelper.createBasicSkills(mContext);
            mPlayer.setSkills(basicSkills);
        }

        if (mPlayer.getId() == 0) {
            mPlayer.setId(mPlayerDao.getNextId());
            mPlayer.getCharacteristics().setId(mCharacteristicsDao.getNextId());

            mPlayerDao.insert(mPlayer);
        } else {
            mPlayerDao.update(mPlayer);
        }
        notifyListeners();

        Log.e("Player Context", mPlayer.toString());
    }

    public static void setPlayer(Player player) {
        mPlayer = player;
    }

    //endregion

    //region Edition
    public static boolean isInEdition() {
        return isInEdition;
    }

    public static void setIsInEdition(boolean isInEdition) {
        PlayerContext.isInEdition = isInEdition;
    }
    //endregion

    public static void registerPlayerUpdateListener(OnPlayerUpdateListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        mListeners.add(listener);
    }

    private static void notifyListeners() {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        for (OnPlayerUpdateListener listener : mListeners) {
            listener.onPlayerUpdate();
        }
    }

    //region Daos

    public static void setPlayerDao(PlayerDao playerDao) {
        PlayerContext.mPlayerDao = playerDao;
    }

    public static void setCharacteristicsDao(CharacteristicsDao characteristicsDao) {
        mCharacteristicsDao = characteristicsDao;
    }

    //endregion


    public static void setContext(Context context) {
        mContext = context;
    }
}
