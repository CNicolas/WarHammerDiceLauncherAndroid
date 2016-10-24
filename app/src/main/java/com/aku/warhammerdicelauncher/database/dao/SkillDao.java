package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.ISkillEntryConstants;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.Skill;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class SkillDao extends AbstractDao<Skill> {
    public SkillDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = ISkillEntryConstants.TABLE_NAME;
        columnId = ISkillEntryConstants.COLUMN_ID;
    }

    //region Find
    public List<Skill> findAllByPlayer(Player player) {
        String[] selectionArgs = {String.valueOf(player.getId())};
        SQLiteDatabase db = whdHelper.getReadableDatabase();
        Cursor cursor = db.query(tableName, null, ISkillEntryConstants.COLUMN_PLAYER_ID + " = ?", selectionArgs, null, null, null);

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
    @Override
    public long insert(Skill skill) {
        throw new NotImplementedException("insert(Skill)");
    }

    public long insert(Skill skill, Player player) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromDto(skill, player);

        long res = db.insert(tableName, null, values);
        return res;
    }

    @Override
    public List<Long> insertAll(List<Skill> skillList) {
        throw new NotImplementedException("insertAll(List<Skill>)");
    }

    public List<Long> insertAll(List<Skill> skillList, Player player) {
        List<Long> res = new ArrayList<>();

        for (Skill skill : skillList) {
            res.add(insert(skill, player));
        }

        return res;
    }

    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromModel(Skill skill) {
        throw new NotImplementedException("contentValuesFromModel(Skill)");
    }

    protected ContentValues contentValuesFromDto(Skill skill, Player player) {
        ContentValues values = new ContentValues();

        values.put(ISkillEntryConstants.COLUMN_NAME, skill.getName());
        values.put(ISkillEntryConstants.COLUMN_CHARACTERISTIC, skill.getCharacteristic().toString());
        values.put(ISkillEntryConstants.COLUMN_LEVEL, skill.getLevel());
        values.put(ISkillEntryConstants.COLUMN_PLAYER_ID, player.getId());

        return values;
    }

    protected Skill createModelFromCursor(Cursor cursor) {
        Skill dto = new Skill();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_LEVEL)));
        dto.setPlayer_id(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_PLAYER_ID)));

        return dto;
    }
    //endregion
}
