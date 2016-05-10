package com.aku.warhammerdicelauncher.model.database;

import android.provider.BaseColumns;

/**
 * Created by cnicolas on 10/05/2016.
 */
public final class DicesHandContract {
    public DicesHandContract() {
    }

    public static abstract class HandEntry implements BaseColumns {
        public static final String TABLE_NAME = "hand";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CHARACTERISTIC = "characteristic";
        public static final String COLUMN_NAME_RECKLESS = "reckless";
        public static final String COLUMN_NAME_CONSERVATIVE = "conservative";
        public static final String COLUMN_NAME_EXPERTISE = "expertise";
        public static final String COLUMN_NAME_FORTUNE = "fortune";
        public static final String COLUMN_NAME_MISFORTUNE = "misfortune";
        public static final String COLUMN_NAME_CHALLENGE = "challenge";
    }
}
