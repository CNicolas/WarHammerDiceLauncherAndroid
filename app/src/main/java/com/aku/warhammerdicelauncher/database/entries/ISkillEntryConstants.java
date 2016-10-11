package com.aku.warhammerdicelauncher.database.entries;

import android.provider.BaseColumns;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface ISkillEntryConstants extends BaseColumns {
    String TABLE_NAME = "skill";

    String COLUMN_NAME_ID = "id";

    String COLUMN_NAME_NAME = "name";
    String COLUMN_NAME_PLAYER_ID = "player_id";
    String COLUMN_NAME_CHARACTERISTIC = "characteristic";
    String COLUMN_NAME_LEVEL = "level";
}
