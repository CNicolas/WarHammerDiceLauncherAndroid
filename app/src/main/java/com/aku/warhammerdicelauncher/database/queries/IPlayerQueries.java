package com.aku.warhammerdicelauncher.database.queries;

import com.aku.warhammerdicelauncher.database.entries.ICharacteristicsEntryConstants;
import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface IPlayerQueries extends IConstants {
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + IPlayerEntryConstants.TABLE_NAME + " (" +
                    IPlayerEntryConstants.COLUMN_NAME_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_NAME + TEXT_TYPE + UNIQUE + NOT_NULL + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_RACE + TEXT_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_AGE + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_SIZE + FLOAT_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +

                    IPlayerEntryConstants.COLUMN_NAME_RANK + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_EXPERIENCE + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MAX_EXPERIENCE + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_WOUNDS + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MAX_WOUNDS + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MAX_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MAX_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +

                    IPlayerEntryConstants.COLUMN_NAME_MONEY_BRASS + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MONEY_SILVER + INTEGER_TYPE + COMMA_SEP +
                    IPlayerEntryConstants.COLUMN_NAME_MONEY_GOLD + INTEGER_TYPE + COMMA_SEP +

                    IPlayerEntryConstants.COLUMN_NAME_CHARACTERISTICS_ID + INTEGER_TYPE + COMMA_SEP +

                    " FOREIGN KEY (" + IPlayerEntryConstants.COLUMN_NAME_CHARACTERISTICS_ID + ") REFERENCES " + ICharacteristicsEntryConstants.TABLE_NAME + "(" + ICharacteristicsEntryConstants.COLUMN_NAME_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + IPlayerEntryConstants.TABLE_NAME;
}
