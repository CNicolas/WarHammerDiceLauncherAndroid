package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.ISkillEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;
import com.aku.warhammerdicelauncher.model.dto.SkillDto;
import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class SkillDao extends AbstractDao<SkillDto> {
    public SkillDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = ISkillEntryConstants.TABLE_NAME;
        columnNameId = ISkillEntryConstants.COLUMN_NAME_ID;
    }

    //region Find
    public List<SkillDto> findAllByPlayer(PlayerDto playerDto) {
        String[] selectionArgs = {String.valueOf(playerDto.getId())};
        SQLiteDatabase db = whdHelper.getReadableDatabase();
        Cursor cursor = db.query(tableName, null, ISkillEntryConstants.COLUMN_NAME_PLAYER_ID + " = ?", selectionArgs, null, null, null);

        List<SkillDto> res = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SkillDto dto = createDtoFromCursor(cursor);
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
    public long insert(SkillDto skillDto) {
        throw new NotImplementedException("insert(SkillDto)");
    }

    public long insert(SkillDto skillDto, PlayerDto playerDto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromDto(skillDto, playerDto);

        long res = db.insert(tableName, null, values);
        return res;
    }
    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromDto(SkillDto skillDto) {
        throw new NotImplementedException("contentValuesFromDto(SkillDto)");
    }

    protected ContentValues contentValuesFromDto(SkillDto skillDto, PlayerDto playerDto) {
        ContentValues values = new ContentValues();

        values.put(ISkillEntryConstants.COLUMN_NAME_NAME, skillDto.getName());
        values.put(ISkillEntryConstants.COLUMN_NAME_CHARACTERISTIC, skillDto.getCharacteristic().toString());
        values.put(ISkillEntryConstants.COLUMN_NAME_LEVEL, skillDto.getLevel());
        values.put(ISkillEntryConstants.COLUMN_NAME_PLAYER_ID, playerDto.getId());

        return values;
    }

    protected SkillDto createDtoFromCursor(Cursor cursor) {
        SkillDto dto = new SkillDto();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnNameId)));
        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME_NAME)));
        dto.setCharacteristic(Characteristic.fromString(cursor.getString(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME_CHARACTERISTIC))));
        dto.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME_LEVEL)));
        dto.setPlayer_id(cursor.getInt(cursor.getColumnIndexOrThrow(ISkillEntryConstants.COLUMN_NAME_PLAYER_ID)));

        return dto;
    }
    //endregion
}
