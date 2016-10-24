package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 06/10/2016.
 */

public abstract class AbstractDao<T extends IModel> implements IDao<T> {
    protected WarHammerDatabaseHelper whdHelper;
    protected String tableName;
    protected String columnId;

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
                T model = createModelFromCursor(cursor);
                res.add(model);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    public T findById(int id) throws SQLiteException {
        return findByIntegerInColumn(id, columnId);
    }

    public T findByIntegerInColumn(int field, String column) throws SQLiteException {
        return findByStringInColumn(String.valueOf(field), column);
    }

    public T findByStringInColumn(String field, String column) throws SQLiteException {
        String[] selectionArgs = {field};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, null, column + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            T model = createModelFromCursor(cursor);
            cursor.close();
            return model;
        } else {
            cursor.close();
            throw new SQLiteException();
        }
    }

    public List<String> findAllValuesOfColumn(String column) {
        List<String> res = new ArrayList<>();
        String[] projection = {column};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(column));
                res.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    //endregion

    //region Insert
    public long insert(T model) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromModel(model);

        long res = db.insert(tableName, null, values);
        return res;
    }

    public List<Long> insertAll(List<T> models) {
        List<Long> res = new ArrayList<>();

        for (T dto : models) {
            res.add(insert(dto));
        }

        return res;
    }
    //endregion

    //region Update
    public long update(T model) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {String.valueOf(model.getId())};

        long res = db.update(tableName, values, String.format("%s = ?", columnId), filters);
        return res;
    }
    //endregion

    //region Delete
    public long delete(T dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        String[] filters = {String.valueOf(dto.getId())};

        long res = db.delete(tableName, String.format("%s = ?", columnId), filters);
        return res;
    }
    //endregion

    public int getNextId() {
        List<String> idsList = findAllValuesOfColumn(columnId);
        if (idsList.size() > 0) {
            return Integer.parseInt(idsList.get(idsList.size() - 1)) + 1;
        } else {
            return 1;
        }
    }

    protected abstract ContentValues contentValuesFromModel(T model);

    protected abstract T createModelFromCursor(Cursor cursor);
}
