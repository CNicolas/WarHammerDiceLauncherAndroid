package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.WarHammerDatabaseHelper;
import com.whfrp3.database.entries.ISkillEntryConstants;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.enums.Characteristic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class SkillDao {
    private final WarHammerDatabaseHelper mWhdHelper;

    public SkillDao(WarHammerDatabaseHelper whdHelper) {
        mWhdHelper = whdHelper;
    }

    //region Find
    public List<Skill> findAll() {
        List<Skill> res = new ArrayList<>();
        SQLiteDatabase db = mWhdHelper.getReadableDatabase();

        Cursor cursor = db.query(ISkillEntryConstants.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Skill model = createModelFromCursor(cursor);
                res.add(model);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public List<Skill> findAllByPlayer(Player player) {
        return findAllByPlayerId(player.getId());
    }

    public List<Skill> findAllByPlayerId(int playerId) {
        String[] selectionArgs = {String.valueOf(playerId)};
        SQLiteDatabase db = mWhdHelper.getReadableDatabase();
        Cursor cursor = db.query(ISkillEntryConstants.TABLE_NAME, null, ISkillEntryConstants.COLUMN_PLAYER_ID + " = ?", selectionArgs, null, null, null);

        List<Skill> res = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Skill dto = createModelFromCursor(cursor);
                res.add(dto);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    //endregion

    //region Insert
    public long insert(Skill model, Player player) {
        SQLiteDatabase db = mWhdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromModel(model, player);

        long res = db.insert(ISkillEntryConstants.TABLE_NAME, null, values);
        return res;
    }

    public List<Long> insertAll(List<Skill> models, Player player) {
        List<Long> res = new ArrayList<>();

        for (Skill skill : models) {
            res.add(insert(skill, player));
        }

        return res;
    }
    //endregion

    //region Update
    public long update(Skill model, Player player) {
        SQLiteDatabase db = mWhdHelper.getWritableDatabase();
        ContentValues values = contentValuesFromModel(model, player);
        String[] filters = {String.valueOf(model.getId())};

        long res = db.update(ISkillEntryConstants.TABLE_NAME, values, String.format("%s = ?", ISkillEntryConstants.COLUMN_ID), filters);
        return res;
    }
    //endregion

    //region Protected Methods
    protected ContentValues contentValuesFromModel(Skill skill, Player player) {
        ContentValues values = new ContentValues();

        values.put(ISkillEntryConstants.COLUMN_NAME, skill.getName());
        values.put(ISkillEntryConstants.COLUMN_CHARACTERISTIC, skill.getCharacteristic().toString());
        values.put(ISkillEntryConstants.COLUMN_LEVEL, skill.getLevel());
        values.put(ISkillEntryConstants.COLUMN_PLAYER_ID, player.getId());

        return values;
    }

    protected Skill createModelFromCursor(Cursor cursor) {
        Skill dto = new Skill();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_ID)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_LEVEL)));
        dto.setPlayer_id(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_PLAYER_ID)));

        return dto;
    }
    //endregion
}
