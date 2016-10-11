package com.aku.warhammerdicelauncher.database.queries;

import com.aku.warhammerdicelauncher.database.entries.ICharacteristicsEntryConstants;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface ICharacteristicsQueries extends IConstants {
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ICharacteristicsEntryConstants.TABLE_NAME + " (" +
                    ICharacteristicsEntryConstants.COLUMN_NAME_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP_FORTUNE + INTEGER_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ICharacteristicsEntryConstants.TABLE_NAME;
}
