package com.aku.warhammerdicelauncher.database.dao;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.IDto;

/**
 * Created by cnicolas on 06/10/2016.
 */

public abstract class AbstractDao<T extends IDto> implements IDao<T> {
    protected WarHammerDatabaseHelper whdHelper;

    public AbstractDao(WarHammerDatabaseHelper whdHelper) {
        this.whdHelper = whdHelper;
    }
}
