package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDao implements IDao<HandDto> {
    private WarHammerDatabaseHelper whdHelper;

    public HandDao(WarHammerDatabaseHelper whdHelper) {
        this.whdHelper = whdHelper;
    }

    //region Find
    public List<HandDto> findAll() {
        List<HandDto> res = new ArrayList<>();
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(IHandEntryConstants.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HandDto dto = createHandDtoFromCursor(cursor);
                res.add(dto);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public List<String> findAllTitles() {
        List<String> res = new ArrayList<>();
        String[] projection = {IHandEntryConstants.COLUMN_NAME_TITLE};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(IHandEntryConstants.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_TITLE));
                res.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public HandDto findByTitle(String title) throws Resources.NotFoundException {
        String[] selectionArgs = {title};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(IHandEntryConstants.TABLE_NAME, null, IHandEntryConstants.COLUMN_NAME_TITLE + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            HandDto dto = createHandDtoFromCursor(cursor);
            cursor.close();
            return dto;
        } else {
            cursor.close();
            throw new Resources.NotFoundException();
        }
    }
    //endregion

    //region Insert
    public long insert(HandDto handDto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromHandDto(handDto);

        long res = db.insert(IHandEntryConstants.TABLE_NAME, null, values);
        return res;
    }
    //endregion

    //region Update
    public long update(HandDto handDto, String title) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        ContentValues values = contentValuesFromHandDto(handDto);
        String[] filters = {title};

        long res = db.update(IHandEntryConstants.TABLE_NAME, values, String.format("%s = ?", IHandEntryConstants.COLUMN_NAME_TITLE), filters);
        return res;
    }
    //endregion

    //region Delete
    public long delete(HandDto handDto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        String[] filters = {handDto.getTitle()};

        long res = db.delete(IHandEntryConstants.TABLE_NAME, String.format("%s = ?", IHandEntryConstants.COLUMN_NAME_TITLE), filters);
        return res;
    }
    //endregion

    //region Private Methods
    private ContentValues contentValuesFromHandDto(HandDto handDto) {
        ContentValues values = new ContentValues();

        values.put(IHandEntryConstants.COLUMN_NAME_TITLE, handDto.getTitle());
        values.put(IHandEntryConstants.COLUMN_NAME_CHARACTERISTIC, handDto.getCharacteristic());
        values.put(IHandEntryConstants.COLUMN_NAME_RECKLESS, handDto.getReckless());
        values.put(IHandEntryConstants.COLUMN_NAME_CONSERVATIVE, handDto.getConservative());
        values.put(IHandEntryConstants.COLUMN_NAME_EXPERTISE, handDto.getExpertise());
        values.put(IHandEntryConstants.COLUMN_NAME_FORTUNE, handDto.getFortune());
        values.put(IHandEntryConstants.COLUMN_NAME_MISFORTUNE, handDto.getMisfortune());
        values.put(IHandEntryConstants.COLUMN_NAME_CHALLENGE, handDto.getChallenge());

        return values;
    }

    private HandDto createHandDtoFromCursor(Cursor cursor) {
        HandDto dto = new HandDto();

        dto.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_TITLE)));
        dto.setCharacteristic(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_CHARACTERISTIC)));
        dto.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_RECKLESS)));
        dto.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_CONSERVATIVE)));
        dto.setExpertise(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_EXPERTISE)));
        dto.setFortune(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_FORTUNE)));
        dto.setMisfortune(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_MISFORTUNE)));
        dto.setChallenge(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_CHALLENGE)));

        return dto;
    }
    //endregion
}
