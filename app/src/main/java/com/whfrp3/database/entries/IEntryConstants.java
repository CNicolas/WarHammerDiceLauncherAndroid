package com.whfrp3.database.entries;

import android.provider.BaseColumns;

/**
 * The database constants common to every table.
 */
interface IEntryConstants extends BaseColumns {
    String COLUMN_ID = "id";

    String TEXT_TYPE = " TEXT";
    String INTEGER_TYPE = " INTEGER";
    String FLOAT_TYPE = " REAL";

    String PRIMARY_KEY = " PRIMARY KEY";

    String AUTO_INCREMENT = " AUTOINCREMENT";
    String NOT_NULL = " NOT NULL";
    String UNIQUE = " UNIQUE";
    String COMMA_SEP = ",";
}
