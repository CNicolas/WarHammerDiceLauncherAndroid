package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IEntryConstants;
import com.whfrp3.model.AbstractModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Gather the common methods for DAOs.
 *
 * @param <T> The associated model class (must extend AbstractModel class).
 */
public abstract class AbstractDao<T extends AbstractModel> implements IDao<T> {

    //region Properties

    /**
     * Database connection.
     */
    protected SQLiteDatabase mDatabase;

    /**
     * Table name.
     */
    protected String mTableName;

    //endregion

    //region Constructor

    public AbstractDao(SQLiteDatabase database, String tableName) {
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

    @Override
    public T findById(long id) {
        return findByColumn(IEntryConstants.COLUMN_ID, String.valueOf(id));
    }

    /**
     * Search an entry in the table with the given column equals to the given value.
     *
     * @param column Column to use.
     * @param value  Value to search.
     * @return Entry found.
     */
    protected T findByColumn(String column, String value) {
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
    protected List<T> findAllByColumn(String column, String value) {
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
    protected List<String> findAllValuesOfColumn(String column) {
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

        long newId = mDatabase.insert(mTableName, null, values);

        model.setId(newId);
    }

    //endregion

    //region Update

    @Override
    public void update(T model) {
        ContentValues values = contentValuesFromModel(model);
        String[] filters = {String.valueOf(model.getId())};

        mDatabase.update(mTableName, values, String.format("%s = ?", IEntryConstants.COLUMN_ID), filters);
    }

    //endregion

    //region Delete

    @Override
    public void delete(long itemId) {
        String[] filters = {String.valueOf(itemId)};

        mDatabase.delete(mTableName, String.format("%s = ?", IEntryConstants.COLUMN_ID), filters);
    }

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
    protected int convertBooleanToInteger(boolean bool) {
        return bool ? 1 : 0;
    }

    /**
     * Converts an integer to a boolean.
     *
     * @param i Integer to convert.
     * @return Boolean corresponding to the integer.
     */
    protected boolean convertIntegerToBoolean(int i) {
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
