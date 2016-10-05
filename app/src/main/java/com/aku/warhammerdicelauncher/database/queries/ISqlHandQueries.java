package com.aku.warhammerdicelauncher.database.queries;

import com.aku.warhammerdicelauncher.database.entries.HandEntry;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface ISqlHandQueries extends ISqlConstants {
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HandEntry.TABLE_NAME + " (" +
                    HandEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CHARACTERISTIC + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_EXPERTISE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_MISFORTUNE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CHALLENGE + INTEGER_TYPE +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HandEntry.TABLE_NAME;
}
