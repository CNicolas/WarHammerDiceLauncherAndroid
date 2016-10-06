package com.aku.warhammerdicelauncher.database.dao;

import com.aku.warhammerdicelauncher.model.dto.IDto;

import java.util.List;

/**
 * Created by cnicolas on 06/10/2016.
 */

public interface IDao<T extends IDto> {
    List<T> findAll();

    long insert(T dto);

    long delete(T dto);
}
