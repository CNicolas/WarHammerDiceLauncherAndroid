package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IPlayerSkillEntryConstants;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.player.PlayerSkill;

import java.util.List;

/**
 * DAO of skills.
 */
public class PlayerSkillDao extends AbstractDao<PlayerSkill> implements IPlayerSkillEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public PlayerSkillDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<PlayerSkill> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(PlayerSkill playerSkill) {
        ContentValues values = new ContentValues();

        values.put(IPlayerSkillEntryConstants.COLUMN_NAME, playerSkill.getName());
        values.put(IPlayerSkillEntryConstants.COLUMN_CHARACTERISTIC, playerSkill.getCharacteristic().toString());
        values.put(IPlayerSkillEntryConstants.COLUMN_LEVEL, playerSkill.getLevel());
        values.put(IPlayerSkillEntryConstants.COLUMN_PLAYER_ID, playerSkill.getPlayerId());

        return values;
    }

    @Override
    protected PlayerSkill createModelFromCursor(Cursor cursor) {
        PlayerSkill dto = new PlayerSkill();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerSkillEntryConstants.COLUMN_ID)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerSkillEntryConstants.COLUMN_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerSkillEntryConstants.COLUMN_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerSkillEntryConstants.COLUMN_LEVEL)));
        dto.setPlayerId(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerSkillEntryConstants.COLUMN_PLAYER_ID)));

        return dto;
    }

    //endregion
}
