package com.whfrp3.database.dao;

import android.database.sqlite.SQLiteException;

import com.whfrp3.model.AbstractModel;

import java.util.List;

/**
 * The general DAO interface.
 *
 * @param <T> The associated model class (must extend AbstractModel class).
 */
interface IDao<T extends AbstractModel> {
    /**
     * Finds all the values of the model in mDatabase.
     *
     * @return All values of the model in mDatabase.
     */
    List<T> findAll();

    /**
     * Finds a model by its id.
     *
     * @param id Id of the model to seek.
     * @return The model found.
     * @throws SQLiteException if no model has been found.
     */
    T findById(long id);

    /**
     * Insert the model in the mDatabase.
     *
     * @param model The model to insert.
     */
    void insert(T model);

    /**
     * Update the model in the mDatabase.
     *
     * @param model The model to update.
     */
    void update(T model);

    /**
     * Delete the model in the mDatabase.
     *
     * @param itemId The id of the model to delete.
     */
    void delete(long itemId);

    /**
     * Delete all the models from table in the mDatabase.
     */
    void deleteAll();
}
