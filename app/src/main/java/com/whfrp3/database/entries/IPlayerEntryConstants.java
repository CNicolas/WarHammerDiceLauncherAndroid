package com.whfrp3.database.entries;

/**
 * The Player database constants.
 */
public interface IPlayerEntryConstants extends IEntryConstants {
    String TABLE_NAME = "player";

    String COLUMN_NAME = "name";
    String COLUMN_RACE = "race";
    String COLUMN_AGE = "age";
    String COLUMN_SIZE = "size";
    String COLUMN_DESCRIPTION = "description";

    String COLUMN_CAREER = "career";
    String COLUMN_RANK = "rank";
    String COLUMN_EXPERIENCE = "experience";
    String COLUMN_MAX_EXPERIENCE = "max_experience";
    String COLUMN_WOUNDS = "wounds";
    String COLUMN_MAX_WOUNDS = "max_wounds";
    String COLUMN_CORRUPTION = "corruption";
    String COLUMN_MAX_CORRUPTION = "max_corruption";
    String COLUMN_RECKLESS = "reckless";
    String COLUMN_MAX_RECKLESS = "max_reckless";
    String COLUMN_CONSERVATIVE = "conservative";
    String COLUMN_MAX_CONSERVATIVE = "max_conservative";
    String COLUMN_STRESS = "stress";
    String COLUMN_EXERTION = "exertion";

    String COLUMN_MONEY_BRASS = "money_brass";
    String COLUMN_MONEY_SILVER = "money_silver";
    String COLUMN_MONEY_GOLD = "money_gold";

    String COLUMN_CHARACTERISTICS_ID = "characteristics_id";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_NAME + TEXT_TYPE + UNIQUE + NOT_NULL + COMMA_SEP +
                    COLUMN_RACE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_AGE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_SIZE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +

                    COLUMN_CAREER + TEXT_TYPE + COMMA_SEP +
                    COLUMN_RANK + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_EXPERIENCE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MAX_EXPERIENCE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_WOUNDS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MAX_WOUNDS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_CORRUPTION + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MAX_CORRUPTION + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MAX_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MAX_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_STRESS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_EXERTION + INTEGER_TYPE + COMMA_SEP +

                    COLUMN_MONEY_BRASS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MONEY_SILVER + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_MONEY_GOLD + INTEGER_TYPE + COMMA_SEP +

                    COLUMN_CHARACTERISTICS_ID + INTEGER_TYPE + COMMA_SEP +

                    " FOREIGN KEY (" + COLUMN_CHARACTERISTICS_ID + ") REFERENCES " + ICharacteristicsEntryConstants.TABLE_NAME + "(" + ICharacteristicsEntryConstants.COLUMN_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

}

