package com.whfrp3.database.entries;

/**
 * The Characteristics mDatabase constants.
 */
public interface ICharacteristicsEntryConstants extends IEntryConstants {
    String TABLE_NAME = "characteristics";

    String COLUMN_STRENGTH = "strength";
    String COLUMN_TOUGHNESS = "toughness";
    String COLUMN_AGILITY = "agility";
    String COLUMN_INTELLIGENCE = "intelligence";
    String COLUMN_WILLPOWER = "willpower";
    String COLUMN_FELLOWSHIP = "fellowship";

    String COLUMN_STRENGTH_FORTUNE = "strength_fortune";
    String COLUMN_TOUGHNESS_FORTUNE = "toughness_fortune";
    String COLUMN_AGILITY_FORTUNE = "agility_fortune";
    String COLUMN_INTELLIGENCE_FORTUNE = "intelligence_fortune";
    String COLUMN_WILLPOWER_FORTUNE = "willpower_fortune";
    String COLUMN_FELLOWSHIP_FORTUNE = "fellowship_fortune";

    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    COLUMN_STRENGTH + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_TOUGHNESS + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_AGILITY + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_INTELLIGENCE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_WILLPOWER + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_FELLOWSHIP + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_STRENGTH_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_TOUGHNESS_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_AGILITY_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_INTELLIGENCE_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_WILLPOWER_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_FELLOWSHIP_FORTUNE + INTEGER_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
