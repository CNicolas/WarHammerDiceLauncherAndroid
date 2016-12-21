package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Gather the common methods for DAOs.
 *
 * @param <T> The associated model class.
 */
abstract class AbstractDao<T> implements IDao<T> {

    //region Properties

    /**
     * Database connection.
     */
    SQLiteDatabase mDatabase;

    /**
     * Table name.
     */
    String mTableName;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param database  Database connection.
     * @param tableName Table name.
     */
    AbstractDao(SQLiteDatabase database, String tableName) {
        this.mDatabase = database;
        this.mTableName = tableName;
    }

    //endregion

    //region Find

    @Override
    public List<T> findAll() {
        List<T> res = new ArrayList<>();

        Cursor cursor = mDatabase.query(mTableName, null, null, null, null, null, null);
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

    /**
     * Search an entry in the table with the given column equals to the given value.
     *
     * @param column Column to use.
     * @param value  Value to search.
     * @return Entry found.
     */
    T findByColumn(String column, String value) {
        String[] selectionArgs = {value};

        Cursor cursor = mDatabase.query(mTableName, null, column + "=?", selectionArgs, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                return createModelFromCursor(cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    /**
     * Search entries in the table with the given column equals to the given value.
     *
     * @param column Column to use.
     * @param value  Value to search.
     * @return Entries found.
     */
    List<T> findAllByColumn(String column, String value) {
        List<T> res = new ArrayList<>();
        String[] selectionArgs = {value};

        Cursor cursor = mDatabase.query(mTableName, null, column + "=?", selectionArgs, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    T model = createModelFromCursor(cursor);
                    res.add(model);
                    cursor.moveToNext();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return res;
    }

    /**
     * Search in the table all the values of the given column.
     *
     * @param column Column to use.
     * @return All the values found.
     */
    List<String> findAllValuesOfColumn(String column) {
        List<String> res = new ArrayList<>();
        String[] projection = {column};

        Cursor cursor = mDatabase.query(mTableName, projection, null, null, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(column));
                    res.add(name);
                    cursor.moveToNext();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        return res;
    }

    //endregion

    //region Insert

    @Override
    public void insert(T model) {
        ContentValues values = contentValuesFromModel(model);

        mDatabase.insert(mTableName, null, values);
    }

    //endregion

    //region Delete

    @Override
    public void deleteAll() {
        mDatabase.delete(mTableName, null, null);
    }

    //endregion

    //region Data conversion methods

    /**
     * Converts a boolean to an integer.
     *
     * @param bool Boolean to convert.
     * @return Integer corresponding to the boolean.
     */
    int convertBooleanToInteger(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Converts an integer to a boolean.
     *
     * @param i Integer to convert.
     * @return Boolean corresponding to the integer.
     */
    boolean convertIntegerToBoolean(int i) {
        return i != 0;
    }

    //endregion

    /**
     * Extract the model fields to a ContentValues element (a kind of map), understandable by the Sql helper.
     *
     * @param model the model.
     * @return the ContentValues.
     */
    protected abstract ContentValues contentValuesFromModel(T model);

    /**
     * Create a model from the cursor.
     *
     * @param cursor the current element of the returning query.
     * @return the model.
     */
    protected abstract T createModelFromCursor(Cursor cursor);
}
