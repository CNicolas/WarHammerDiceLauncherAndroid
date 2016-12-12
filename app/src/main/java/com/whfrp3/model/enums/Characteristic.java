package com.whfrp3.model.enums;

import com.whfrp3.database.entries.ICharacteristicsEntryConstants;

/**
 * List all the different characteristics.
 */
public enum Characteristic {
    STRENGTH(ICharacteristicsEntryConstants.COLUMN_STRENGTH),
    TOUGHNESS(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS),
    AGILITY(ICharacteristicsEntryConstants.COLUMN_AGILITY),
    INTELLIGENCE(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE),
    WILLPOWER(ICharacteristicsEntryConstants.COLUMN_WILLPOWER),
    FELLOWSHIP(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP),

    STRENGTH_FORTUNE(ICharacteristicsEntryConstants.COLUMN_STRENGTH_FORTUNE),
    TOUGHNESS_FORTUNE(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS_FORTUNE),
    AGILITY_FORTUNE(ICharacteristicsEntryConstants.COLUMN_AGILITY_FORTUNE),
    INTELLIGENCE_FORTUNE(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE_FORTUNE),
    WILLPOWER_FORTUNE(ICharacteristicsEntryConstants.COLUMN_WILLPOWER_FORTUNE),
    FELLOWSHIP_FORTUNE(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP_FORTUNE);

    private final String characteristic;

    Characteristic(final String characteristic) {
        this.characteristic = characteristic;
    }

    public static Characteristic fromString(String text) {
        if (text != null) {
            for (Characteristic b : Characteristic.values()) {
                if (text.equalsIgnoreCase(b.characteristic)) {
                    return b;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return characteristic;
    }
}
