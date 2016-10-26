package com.whfrp3.database.dao;

import android.database.sqlite.SQLiteException;

import com.whfrp3.model.IModel;

import java.util.List;

/**
 * The Dao interface.
 *
 * @param <T> the associated IModel.
 */
interface IDao<T extends IModel> {
    /**
     * Finds all the values of the model in database.
     *
     * @return the complete list.
     */
    List<T> findAll();

    /**
     * Finds a model by its id.
     *
     * @param id the id of the model to seek.
     * @return the model.
     * @throws SQLiteException if no model has been found.
     */
    T findById(int id) throws SQLiteException;

    /**
     * Insert the model in the database.
     *
     * @param model the model to insert.
     * @return the result of the sql query.
     */
    long insert(T model);

    /**
     * Update the model in the database.
     *
     * @param model the model to update.
     * @return the result of the sql query.
     */
    long update(T model);

    /**
     * Delete the model in the database.
     *
     * @param model the model to delete.
     * @return the result of the sql query.
     */
    long delete(T model);

    /**
     * Delete all the models from table in the database.
     *
     * @return the result of the sql query.
     */
    long deleteAll();
}
