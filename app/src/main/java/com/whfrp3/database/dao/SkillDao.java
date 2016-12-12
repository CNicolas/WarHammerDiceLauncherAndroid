package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.ISkillEntryConstants;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.player.Skill;

import java.util.List;

/**
 * DAO of skills.
 */
public class SkillDao extends AbstractDao<Skill> implements ISkillEntryConstants {

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

    public List<Skill> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(Skill skill) {
        ContentValues values = new ContentValues();

        values.put(ISkillEntryConstants.COLUMN_NAME, skill.getName());
        values.put(ISkillEntryConstants.COLUMN_CHARACTERISTIC, skill.getCharacteristic().toString());
        values.put(ISkillEntryConstants.COLUMN_LEVEL, skill.getLevel());
        values.put(ISkillEntryConstants.COLUMN_PLAYER_ID, skill.getPlayerId());

        return values;
    }

    @Override
    protected Skill createModelFromCursor(Cursor cursor) {
        Skill dto = new Skill();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_ID)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_LEVEL)));
        dto.setPlayerId(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_PLAYER_ID)));

        return dto;
    }

    //endregion
}
