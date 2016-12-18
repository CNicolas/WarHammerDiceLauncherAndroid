package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IPlayerCharacteristicEntryConstants;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.player.PlayerCharacteristic;

import java.util.List;

/**
 * DAO of player characteristic.
 */
public class PlayerCharacteristicDao extends AbstractDao<PlayerCharacteristic> implements IPlayerCharacteristicEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerCharacteristicDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<PlayerCharacteristic> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Update

    @Override
    public void update(PlayerCharacteristic model) {
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {model.getCharacteristic().toString(), String.valueOf(model.getPlayerId())};

        mDatabase.update(mTableName, values, String.format("%s = ? AND %s = ?", COLUMN_CHARACTERISTIC, COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Delete

    /**
     * Removes all player characteristics with the given player id from the database.
     *
     * @param playerId Player id.
     */
    public void deleteAllByPlayerId(long playerId) {
        String[] filters = {String.valueOf(playerId)};

        mDatabase.delete(mTableName, String.format("%s = ?", COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(PlayerCharacteristic playerCharacteristic) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_CHARACTERISTIC, playerCharacteristic.getCharacteristic().toString());
        values.put(COLUMN_PLAYER_ID, playerCharacteristic.getPlayerId());
        values.put(COLUMN_VALUE, playerCharacteristic.getValue());
        values.put(COLUMN_FORTUNE_VALUE, playerCharacteristic.getFortuneValue());

        return values;
    }

    @Override
    protected PlayerCharacteristic createModelFromCursor(Cursor cursor) {
        Characteristic characteristic = Characteristic.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CHARACTERISTIC)));
        long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_ID));
        int value = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VALUE));
        int fortuneValue = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FORTUNE_VALUE));

        return new PlayerCharacteristic(characteristic, playerId, value, fortuneValue);
    }

    //endregion
}
