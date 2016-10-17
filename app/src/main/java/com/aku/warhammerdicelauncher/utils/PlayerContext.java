package com.aku.warhammerdicelauncher.utils;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.ihm.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.player.Player;

/**
 * Created by cnicolas on 13/10/2016.
 */

public class PlayerContext {
    private static Player player;

    public static Player getPlayerInstance(MainActivity context, int id) {
        if (player == null) {
            PlayerDao playerDao = new PlayerDao(new WarHammerDatabaseHelper(context));
            player = playerDao.findById(id);
        }
        return player;
    }

    public static Player getPlayerInstance() {
        if (player == null) {
            player = getEmptyPlayerInstance();
        }
        return player;
    }

    public static Player getEmptyPlayerInstance() {
        player = new Player();
        return player;
    }
}
