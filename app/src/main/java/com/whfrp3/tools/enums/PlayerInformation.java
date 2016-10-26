package com.whfrp3.tools.enums;

import com.whfrp3.database.entries.IPlayerEntryConstants;

/**
 * List the player information and links them to the column in database.
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
    MAX_EXPERIENCE(IPlayerEntryConstants.COLUMN_MAX_EXPERIENCE),
    WOUNDS(IPlayerEntryConstants.COLUMN_WOUNDS),
    MAX_WOUNDS(IPlayerEntryConstants.COLUMN_MAX_WOUNDS),
    CORRUPTION(IPlayerEntryConstants.COLUMN_CORRUPTION),
    MAX_CORRUPTION(IPlayerEntryConstants.COLUMN_MAX_CORRUPTION),
    CONSERVATIVE(IPlayerEntryConstants.COLUMN_CONSERVATIVE),
    MAX_CONSERVATIVE(IPlayerEntryConstants.COLUMN_MAX_CONSERVATIVE),
    RECKLESS(IPlayerEntryConstants.COLUMN_RECKLESS),
    MAX_RECKLESS(IPlayerEntryConstants.COLUMN_MAX_RECKLESS);

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
