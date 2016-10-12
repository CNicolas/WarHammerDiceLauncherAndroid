package com.aku.warhammerdicelauncher.database.queries;

import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface IHandQueries extends IConstants {
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + IHandEntryConstants.TABLE_NAME + " (" +
                    IHandEntryConstants.COLUMN_NAME_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_CHARACTERISTIC + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_EXPERTISE + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_MISFORTUNE + INTEGER_TYPE + COMMA_SEP +
                    IHandEntryConstants.COLUMN_NAME_CHALLENGE + INTEGER_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + IHandEntryConstants.TABLE_NAME;
}
