package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.IDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 06/10/2016.
 */

public abstract class AbstractDao<T extends IDto> implements IDao<T> {
    protected WarHammerDatabaseHelper whdHelper;
    protected String tableName;
    protected String columnNameId;

    public AbstractDao(WarHammerDatabaseHelper whdHelper) {
        this.whdHelper = whdHelper;
    }

    //region Find
    public List<T> findAll() {
        List<T> res = new ArrayList<>();
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                T dto = createDtoFromCursor(cursor);
                res.add(dto);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public T findById(int id) throws Resources.NotFoundException {
        String[] selectionArgs = {String.valueOf(id)};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, null, columnNameId + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            T dto = createDtoFromCursor(cursor);
            cursor.close();
            return dto;
        } else {
            cursor.close();
            throw new Resources.NotFoundException();
        }
    }
    //endregion

    //region Insert
    public long insert(T dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromDto(dto);

        long res = db.insert(tableName, null, values);
        return res;
    }

    public List<Long> insertAll(List<T> dtos) {
        List<Long> res = new ArrayList<>();

        for (T dto : dtos) {
            res.add(insert(dto));
        }

        return res;
    }
    //endregion

    //region Delete
    public long delete(T dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        String[] filters = {String.valueOf(dto.getId())};

        long res = db.delete(tableName, String.format("%s = ?", columnNameId), filters);
        return res;
    }
    //endregion

    protected abstract ContentValues contentValuesFromDto(T dto);

    protected abstract T createDtoFromCursor(Cursor cursor);
}
