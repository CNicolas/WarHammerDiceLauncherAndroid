package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.whfrp3.database.entries.IPlayerEntryConstants;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.helpers.GsonHelper;

import java.util.List;

/**
 * DAO of players.
 */
public class PlayerDao extends AbstractDaoWithId<Player> implements IPlayerEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    /**
     * Find all players names.
     *
     * @return Players names.
     */
    public List<String> findAllNames() {
        return findAllValuesOfColumn(COLUMN_NAME);
    }

    /**
     * Find a player by its name.
     *
     * @param name Player name.
     * @return Player found.
     */
    public Player findByName(String name) {
        return findByColumn(COLUMN_NAME, name);
    }

    //endregion

    //region Private methods

    @Override
    protected ContentValues contentValuesFromModel(Player player) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_JSON, GsonHelper.getInstance().toJson(player));

        return values;
    }

    @Override
    protected Player createModelFromCursor(Cursor cursor) {
        String json = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JSON));
        Log.w("PLAYER", json);
        return GsonHelper.getInstance().fromJson(json, Player.class);
    }

    //endregion
}
