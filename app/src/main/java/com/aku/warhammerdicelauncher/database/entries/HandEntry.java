package com.aku.warhammerdicelauncher.database.entries;

import android.provider.BaseColumns;

/**
 * Created by cnicolas on 05/10/2016.
 */

public abstract class HandEntry implements BaseColumns {
    public static final String TABLE_NAME = "hand";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_CHARACTERISTIC = "characteristic";
    public static final String COLUMN_NAME_RECKLESS = "reckless";
    public static final String COLUMN_NAME_CONSERVATIVE = "conservative";
    public static final String COLUMN_NAME_EXPERTISE = "expertise";
    public static final String COLUMN_NAME_FORTUNE = "fortune";
    public static final String COLUMN_NAME_MISFORTUNE = "misfortune";
    public static final String COLUMN_NAME_CHALLENGE = "challenge";

}
