package com.whfrp3.database.entries;

/**
 * The PlayerCharacteristic database constants.
 */
public interface IPlayerCharacteristicEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player_characteristic";

    String COLUMN_CHARACTERISTIC = "characteristic";
    String COLUMN_PLAYER_ID = "player_id";
    String COLUMN_VALUE = "value";
    String COLUMN_FORTUNE_VALUE = "fortune_value";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_CHARACTERISTIC + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_VALUE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_FORTUNE_VALUE + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_ID + ")" +
                    " PRIMARY KEY (" + COLUMN_CHARACTERISTIC + ", " + COLUMN_PLAYER_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
