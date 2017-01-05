package com.whfrp3.database.entries;

/**
 * The PlayerTalent database constants.
 */
public interface IPlayerTalentEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player_talent";

    String COLUMN_TALENT_ID = "talent_id";
    String COLUMN_PLAYER_ID = "player_id";

    String COLUMN_IS_EQUIPPED = "is_equipped";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_TALENT_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_IS_EQUIPPED + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_ID + ")" +
                    " PRIMARY KEY (" + COLUMN_TALENT_ID + ", " + COLUMN_PLAYER_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
