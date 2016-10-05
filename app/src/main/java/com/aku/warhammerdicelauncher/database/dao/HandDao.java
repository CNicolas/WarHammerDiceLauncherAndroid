package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.HandEntry;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDao {
    private WarHammerDatabaseHelper whdHelper;

    public HandDao(WarHammerDatabaseHelper whdHelper) {
        this.whdHelper = whdHelper;
    }

    public List<HandDto> findAll() {
        List<HandDto> res = new ArrayList<>();
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(HandEntry.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HandDto dto = new HandDto();

                dto.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_TITLE)));
                dto.setCharacteristic(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHARACTERISTIC)));
                dto.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_RECKLESS)));
                dto.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CONSERVATIVE)));
                dto.setExpertise(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_EXPERTISE)));
                dto.setFortune(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_FORTUNE)));
                dto.setMisfortune(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_MISFORTUNE)));
                dto.setChallenge(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHALLENGE)));

                res.add(dto);

                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public List<String> findAllTitles() {
        List<String> res = new ArrayList<>();
        String[] projection = {HandEntry.COLUMN_NAME_TITLE};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(HandEntry.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_TITLE));
                res.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public HandDto findByTitle(String title) throws Resources.NotFoundException {
        HandDto res = new HandDto();
        String[] selectionArgs = {title};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(HandEntry.TABLE_NAME, null, HandEntry.COLUMN_NAME_TITLE + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            res.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_TITLE)));
            res.setCharacteristic(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHARACTERISTIC)));
            res.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_RECKLESS)));
            res.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CONSERVATIVE)));
            res.setExpertise(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_EXPERTISE)));
            res.setFortune(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_FORTUNE)));
            res.setMisfortune(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_MISFORTUNE)));
            res.setChallenge(cursor.getInt(cursor.getColumnIndexOrThrow(HandEntry.COLUMN_NAME_CHALLENGE)));

            cursor.close();
            return res;
        } else {
            cursor.close();
            throw new Resources.NotFoundException();
        }
    }

    public long insert(HandDto handDto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromHandDto(handDto);

        return db.insert(HandEntry.TABLE_NAME, null, values);
    }

    public long update(HandDto handDto, String title) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromHandDto(handDto);

        String[] filters = {title};
        return db.update(HandEntry.TABLE_NAME, values, String.format("%s = ?", HandEntry.COLUMN_NAME_TITLE), filters);
    }

    private ContentValues contentValuesFromHandDto(HandDto handDto) {
        ContentValues values = new ContentValues();

        values.put(HandEntry.COLUMN_NAME_TITLE, handDto.getTitle());
        values.put(HandEntry.COLUMN_NAME_CHARACTERISTIC, handDto.getCharacteristic());
        values.put(HandEntry.COLUMN_NAME_RECKLESS, handDto.getReckless());
        values.put(HandEntry.COLUMN_NAME_CONSERVATIVE, handDto.getConservative());
        values.put(HandEntry.COLUMN_NAME_EXPERTISE, handDto.getExpertise());
        values.put(HandEntry.COLUMN_NAME_FORTUNE, handDto.getFortune());
        values.put(HandEntry.COLUMN_NAME_MISFORTUNE, handDto.getMisfortune());
        values.put(HandEntry.COLUMN_NAME_CHALLENGE, handDto.getChallenge());

        return values;
    }

}
