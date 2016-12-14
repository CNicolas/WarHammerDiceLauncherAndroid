package com.whfrp3.database.dao;

import java.util.List;

/**
 * The general DAO interface.
 *
 * @param <T> The associated model class.
 */
interface IDao<T> {
    /**
     * Finds all data in the database.
     *
     * @return All data in the database.
     */
    List<T> findAll();

    /**
     * Inserts the model in the database.
     *
     * @param model The model to insert.
     */
    void insert(T model);

    /**
     * Updates the model in the database.
     *
     * @param model The model to update.
     */
    void update(T model);

    /**
     * Deletes all data from the database table.
     */
    void deleteAll();
}
