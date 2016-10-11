package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.model.dto.SkillDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class SkillDao extends AbstractDao<SkillDto> {
    public SkillDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
    }

    //region Find
    @Override
    public List<SkillDto> findAll() {
        List<HandDto> res = new ArrayList<>();
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(IHandEntryConstants.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HandDto dto = createDtoFromCursor(cursor);
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
    public long insert(HandDto handDto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromDto(handDto);

        long res = db.insert(IHandEntryConstants.TABLE_NAME, null, values);
        return res;
    }
    //endregion

    //region Update

    //endregion

    //region Delete
    @Override
    public long delete(SkillDto dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        String[] filters = {dto.getId()};

        long res = db.delete(IHandEntryConstants.TABLE_NAME, String.format("%s = ?", IHandEntryConstants.COLUMN_NAME_TITLE), filters);
        return res;
    }
    //endregion

    //region Private Methods
    private ContentValues contentValuesFromDto(HandDto handDto) {
        ContentValues values = new ContentValues();

        values.put(IHandEntryConstants.COLUMN_NAME_TITLE, handDto.getTitle());
        values.put(IHandEntryConstants.COLUMN_NAME_CHARACTERISTIC, handDto.getCharacteristic());

        return values;
    }

    private HandDto createDtoFromCursor(Cursor cursor) {
        HandDto dto = new HandDto();

        dto.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_TITLE)));
        dto.setCharacteristic(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_CHARACTERISTIC)));

        return dto;
    }
    //endregion
}
