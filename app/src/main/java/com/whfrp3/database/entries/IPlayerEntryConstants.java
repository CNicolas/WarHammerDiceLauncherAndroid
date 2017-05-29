package com.whfrp3.database.entries;

/**
 * The Player mDatabase constants.
 */
public interface IPlayerEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player";

    String COLUMN_NAME = "name";
    String COLUMN_JSON = "json";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_NAME + TEXT_TYPE + UNIQUE + NOT_NULL + COMMA_SEP +
                    COLUMN_JSON + TEXT_TYPE + NOT_NULL +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}

