package com.whfrp3.database.entries;

/**
 * The Skill database constants.
 */
public interface ISkillEntryConstants extends IEntryConstants {
    String TABLE_NAME = "skill";

    String COLUMN_NAME = "name";
    String COLUMN_PLAYER_ID = "player_id";
    String COLUMN_CHARACTERISTIC = "characteristic";
    String COLUMN_LEVEL = "level";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_CHARACTERISTIC + TEXT_TYPE + COMMA_SEP +
                    COLUMN_LEVEL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
