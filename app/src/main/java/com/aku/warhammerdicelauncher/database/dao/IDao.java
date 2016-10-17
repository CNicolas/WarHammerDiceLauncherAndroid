package com.aku.warhammerdicelauncher.database.dao;

import android.content.res.Resources;

import com.aku.warhammerdicelauncher.model.IModel;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public interface IDao<T extends IModel> {
    List<T> findAll();

    T findById(int id) throws Resources.NotFoundException;

    long insert(T dto);

    long delete(T dto);
}
