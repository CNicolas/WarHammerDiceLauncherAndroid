package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IPlayerSpecializationEntryConstants;
import com.whfrp3.model.player.PlayerSpecialization;
import com.whfrp3.model.skills.Specialization;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.List;

/**
 * DAO of player specializations.
 */
public class PlayerSpecializationDao extends AbstractDao<PlayerSpecialization> implements IPlayerSpecializationEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerSpecializationDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<PlayerSpecialization> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Update

    @Override
    public void update(PlayerSpecialization model) {
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {String.valueOf(model.getSpecialization().getId()), String.valueOf(model.getPlayerId())};

        mDatabase.update(mTableName, values, String.format("%s = ? AND %s = ?", COLUMN_SPECIALIZATION_ID, COLUMN_PLAYER_ID), filters);
    }

    //endregion

    //region Delete

    /**
     * Removes all player specializations with the given player id from the database.
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
    protected ContentValues contentValuesFromModel(PlayerSpecialization playerSpecialization) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_SPECIALIZATION_ID, playerSpecialization.getSpecialization().getId());
        values.put(COLUMN_PLAYER_ID, playerSpecialization.getPlayerId());

        return values;
    }

    @Override
    protected PlayerSpecialization createModelFromCursor(Cursor cursor) {
        long specializationId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_SPECIALIZATION_ID));
        long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_ID));

        Specialization specialization = SpecializationHelper.getInstance().getSpecialization(specializationId);

        return new PlayerSpecialization(specialization, playerId);
    }

    //endregion
}
