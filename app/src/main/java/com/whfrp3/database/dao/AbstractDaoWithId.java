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
public abstract class AbstractDaoWithId<T extends AbstractModel> extends AbstractDao<T> implements IDaoWithId<T> {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database  Database connection.
     * @param tableName Table name.
     */
    public AbstractDaoWithId(SQLiteDatabase database, String tableName) {
        super(database, tableName);

        this.mDatabase = database;
        this.mTableName = tableName;
    }

    //endregion

    //region Find

    @Override
    public T findById(long id) {
        return findByColumn(IEntryConstants.COLUMN_ID, String.valueOf(id));
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

    //endregion
}