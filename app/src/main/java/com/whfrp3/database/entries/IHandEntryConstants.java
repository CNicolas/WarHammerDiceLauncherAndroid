package com.whfrp3.database.entries;

/**
 * The Hand database constants.
 */
public interface IHandEntryConstants extends IEntryConstants {
    String TABLE_NAME = "hand";

    String COLUMN_TITLE = "title";
    String COLUMN_CHARACTERISTIC = "characteristic";
    String COLUMN_RECKLESS = "reckless";
    String COLUMN_CONSERVATIVE = "conservative";
    String COLUMN_EXPERTISE = "expertise";
    String COLUMN_FORTUNE = "fortune";
    String COLUMN_MISFORTUNE = "misfortune";
    String COLUMN_CHALLENGE = "challenge";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_CHARACTERISTIC + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_EXPERTISE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MISFORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_CHALLENGE + INTEGER_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
