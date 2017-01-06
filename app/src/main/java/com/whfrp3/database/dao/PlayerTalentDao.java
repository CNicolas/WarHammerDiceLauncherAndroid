package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IPlayerTalentEntryConstants;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.tools.helpers.TalentHelper;

import java.util.List;

/**
 * DAO of player talents.
 */
public class PlayerTalentDao extends AbstractDao<PlayerTalent> implements IPlayerTalentEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerTalentDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<PlayerTalent> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Update

    @Override
    public void update(PlayerTalent model) {
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {String.valueOf(model.getTalent().getId()), String.valueOf(model.getPlayerId())};

        mDatabase.update(mTableName, values, String.format("%s = ? AND %s = ?", COLUMN_TALENT_ID, COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Delete

    /**
     * Removes all player talents with the given player id from the database.
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
    protected ContentValues contentValuesFromModel(PlayerTalent playerTalent) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TALENT_ID, playerTalent.getTalent().getId());
        values.put(COLUMN_PLAYER_ID, playerTalent.getPlayerId());
        values.put(COLUMN_IS_EQUIPPED, convertBooleanToInteger(playerTalent.isEquipped()));

        return values;
    }

    @Override
    protected PlayerTalent createModelFromCursor(Cursor cursor) {
        long talentId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TALENT_ID));
        long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_ID));
        boolean equipped = convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_EQUIPPED)));

        Talent talent = TalentHelper.getInstance().getTalentById(talentId);

        PlayerTalent res = new PlayerTalent(talent, playerId);
        res.setEquipped(equipped);

        return res;
    }

    //endregion
}
