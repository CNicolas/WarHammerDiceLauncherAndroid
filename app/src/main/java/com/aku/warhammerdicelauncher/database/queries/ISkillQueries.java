package com.aku.warhammerdicelauncher.database.queries;

import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;
import com.aku.warhammerdicelauncher.database.entries.ISkillEntryConstants;

/**
 * Created by cnicolas on 05/10/2016.
 */

public interface ISkillQueries extends IConstants {
    String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ISkillEntryConstants.TABLE_NAME + " (" +
                    ISkillEntryConstants.COLUMN_NAME_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP +
                    ISkillEntryConstants.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ISkillEntryConstants.COLUMN_NAME_CHARACTERISTIC + TEXT_TYPE + COMMA_SEP +
                    ISkillEntryConstants.COLUMN_NAME_LEVEL + TEXT_TYPE + COMMA_SEP +
                    ISkillEntryConstants.COLUMN_NAME_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + ISkillEntryConstants.COLUMN_NAME_PLAYER_ID + ") REFERENCES " + IPlayerEntryConstants.TABLE_NAME + "(" + IPlayerEntryConstants.COLUMN_NAME_ID + ")" +
                    " )";

    String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ISkillEntryConstants.TABLE_NAME;
}
