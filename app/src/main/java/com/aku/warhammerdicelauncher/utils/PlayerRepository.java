package com.aku.warhammerdicelauncher.utils;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.PlayerDao;
import com.aku.warhammerdicelauncher.ihm.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;

/**
 * Created by cnicolas on 13/10/2016.
 */

public class PlayerRepository {
    private static PlayerDto player;

    public static PlayerDto getPlayerInstance(MainActivity context, int id) {
        if (player == null) {
            PlayerDao playerDao = new PlayerDao(new WarHammerDatabaseHelper(context));
            player = playerDao.findById(id);
        }
        return player;
    }

    public static PlayerDto getPlayerInstance() {
        if (player == null) {
            player = getEmptyPlayerInstance();
        }
        return player;
    }

    public static PlayerDto getEmptyPlayerInstance() {
        player = new PlayerDto();
        return player;
    }
}
