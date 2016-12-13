package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.ISkillEntryConstants;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.player.PlayerSkill;

import java.util.List;

/**
 * DAO of skills.
 */
public class SkillDao extends AbstractDao<PlayerSkill> implements ISkillEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public SkillDao(SQLiteDatabase database) {
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

        values.put(ISkillEntryConstants.COLUMN_NAME, playerSkill.getName());
        values.put(ISkillEntryConstants.COLUMN_CHARACTERISTIC, playerSkill.getCharacteristic().toString());
        values.put(ISkillEntryConstants.COLUMN_LEVEL, playerSkill.getLevel());
        values.put(ISkillEntryConstants.COLUMN_PLAYER_ID, playerSkill.getPlayerId());

        return values;
    }

    @Override
    protected PlayerSkill createModelFromCursor(Cursor cursor) {
        PlayerSkill dto = new PlayerSkill();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_ID)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_LEVEL)));
        dto.setPlayerId(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_PLAYER_ID)));

        return dto;
    }

    //endregion
}
