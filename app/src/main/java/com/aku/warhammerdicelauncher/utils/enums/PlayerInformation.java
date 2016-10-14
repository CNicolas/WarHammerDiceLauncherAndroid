package com.aku.warhammerdicelauncher.utils.enums;

import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;

/**
 * Created by cnicolas on 14/10/2016.
 */

public enum PlayerInformation {
    NAME(IPlayerEntryConstants.COLUMN_NAME_NAME),
    RACE(IPlayerEntryConstants.COLUMN_NAME_RACE),
    AGE(IPlayerEntryConstants.COLUMN_NAME_AGE),
    SIZE(IPlayerEntryConstants.COLUMN_NAME_SIZE),
    DESCRIPTION(IPlayerEntryConstants.COLUMN_NAME_DESCRIPTION),

    RANK(IPlayerEntryConstants.COLUMN_NAME_RANK),
    EXPERIENCE(IPlayerEntryConstants.COLUMN_NAME_EXPERIENCE),
    MAX_EXPERIENCE(IPlayerEntryConstants.COLUMN_NAME_MAX_EXPERIENCE);

    private final String information;

    PlayerInformation(final String information) {
        this.information = information;
    }

    public static PlayerInformation fromString(String text) {
        if (text != null) {
            for (PlayerInformation b : PlayerInformation.values()) {
                if (text.equalsIgnoreCase(b.information)) {
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return information;
    }
}
