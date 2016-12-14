package com.whfrp3.database.dao;

import android.database.sqlite.SQLiteException;

import com.whfrp3.model.AbstractModel;

/**
 * The DAO interface used for models with id.
 *
 * @param <T> The associated model class (must extend AbstractModel class).
 */
interface IDaoWithId<T extends AbstractModel> extends IDao<T> {

    /**
     * Finds a model by its id.
     *
     * @param id Id of the model to seek.
     * @return The model found.
     * @throws SQLiteException if no model has been found.
     */
    T findById(long id);

    /**
     * Deletes the model in the database.
     *
     * @param id The id of the model to delete.
     */
    void delete(long id);
}
