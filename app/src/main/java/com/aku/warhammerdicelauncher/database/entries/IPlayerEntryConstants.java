package com.aku.warhammerdicelauncher.database.entries;

import android.provider.BaseColumns;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface IPlayerEntryConstants extends BaseColumns {
    String TABLE_NAME = "player";

    String COLUMN_NAME_ID = "id";

    String COLUMN_NAME_NAME = "name";
    String COLUMN_NAME_RACE = "race";
    String COLUMN_NAME_AGE = "age";
    String COLUMN_NAME_SIZE = "size";
    String COLUMN_NAME_DESCRIPTION = "age";

    String COLUMN_NAME_RANK = "rank";
    String COLUMN_NAME_EXPERIENCE = "experience";
    String COLUMN_NAME_MAX_EXPERIENCE = "max_experience";
    String COLUMN_NAME_WOUNDS = "wounds";
    String COLUMN_NAME_MAX_WOUNDS = "max_wounds";
    String COLUMN_NAME_RECKLESS = "reckless";
    String COLUMN_NAME_MAX_RECKLESS = "max_reckless";
    String COLUMN_NAME_CONSERVATIVE = "conservative";
    String COLUMN_NAME_MAX_CONSERVATIVE = "max_conservative";

    String COLUMN_NAME_MONEY_BRASS = "money_brass";
    String COLUMN_NAME_MONEY_SILVER = "money_silver";
    String COLUMN_NAME_MONEY_GOLD = "money_gold";

    String COLUMN_NAME_CHARACTERISTICS_ID = "characteristics_id";

}

