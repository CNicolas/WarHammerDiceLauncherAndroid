package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDao extends AbstractDao<HandDto> {
    public HandDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = IHandEntryConstants.TABLE_NAME;
        columnNameId = IHandEntryConstants.COLUMN_NAME_ID;
    }

    //region Find
    public List<String> findAllTitles() {
        return findAllByField(IHandEntryConstants.COLUMN_NAME_TITLE);
    }

    public HandDto findByTitle(String title) throws Resources.NotFoundException {
        String[] selectionArgs = {title};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, null, IHandEntryConstants.COLUMN_NAME_TITLE + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            HandDto dto = createDtoFromCursor(cursor);
            cursor.close();
            return dto;
        } else {
            cursor.close();
            throw new Resources.NotFoundException();
        }
    }
    //endregion

    //region Update
    public long update(HandDto handDto, String title) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        ContentValues values = contentValuesFromDto(handDto);
        String[] filters = {title};

        long res = db.update(tableName, values, String.format("%s = ?", IHandEntryConstants.COLUMN_NAME_TITLE), filters);
        return res;
    }
    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromDto(HandDto handDto) {
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

    protected HandDto createDtoFromCursor(Cursor cursor) {
        HandDto dto = new HandDto();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_NAME_ID)));

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
