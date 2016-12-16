package com.whfrp3.database.entries;

/**
 * The PlayerSpecialization database constants.
 */
public interface IPlayerSpecializationEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player_specialization";

    String COLUMN_SPECIALIZATION_ID = "specialization_id";
    String COLUMN_PLAYER_ID = "player_id";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_SPECIALIZATION_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_ID + ")" +
                    " PRIMARY KEY (" + COLUMN_SPECIALIZATION_ID + ", " + COLUMN_PLAYER_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
