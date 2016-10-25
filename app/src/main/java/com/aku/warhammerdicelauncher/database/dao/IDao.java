package com.aku.warhammerdicelauncher.database.dao;

import android.database.sqlite.SQLiteException;

import com.aku.warhammerdicelauncher.model.IModel;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public interface IDao<T extends IModel> {
    List<T> findAll();
    T findById(int id) throws SQLiteException;

    long insert(T model);

    long update(T model);

    long delete(T model);

    long deleteAll();
}
