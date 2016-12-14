package com.whfrp3.database.entries;

/**
 * The PlayerSkill database constants.
 */
public interface IPlayerSkillEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player_skill";

    String COLUMN_SKILL_ID = "skill_id";
    String COLUMN_PLAYER_ID = "player_id";
    String COLUMN_LEVEL = "level";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_SKILL_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_LEVEL + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + COLUMN_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_ID + ")" +
                    " PRIMARY KEY (" + COLUMN_SKILL_ID + ", " + COLUMN_PLAYER_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
