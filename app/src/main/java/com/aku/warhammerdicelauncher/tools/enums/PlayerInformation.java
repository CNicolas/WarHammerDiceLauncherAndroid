package com.aku.warhammerdicelauncher.tools.enums;

import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;

/**
 * Created by cnicolas on 14/10/2016.
 */

public enum PlayerInformation {
    NAME(IPlayerEntryConstants.COLUMN_NAME),
    CAREER(IPlayerEntryConstants.COLUMN_CAREER),
    RACE(IPlayerEntryConstants.COLUMN_RACE),
    AGE(IPlayerEntryConstants.COLUMN_AGE),
    SIZE(IPlayerEntryConstants.COLUMN_SIZE),
    DESCRIPTION(IPlayerEntryConstants.COLUMN_DESCRIPTION),

    RANK(IPlayerEntryConstants.COLUMN_RANK),
    EXPERIENCE(IPlayerEntryConstants.COLUMN_EXPERIENCE),
    MAX_EXPERIENCE(IPlayerEntryConstants.COLUMN_MAX_EXPERIENCE);

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
