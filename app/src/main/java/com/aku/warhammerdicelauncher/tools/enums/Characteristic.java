package com.aku.warhammerdicelauncher.tools.enums;

/**
 * Created by cnicolas on 06/10/2016.
 */

public enum Characteristic {
    STRENGTH("strength"),
    TOUGHNESS("toughness"),
    AGILITY("agility"),
    INTELLIGENCE("intelligence"),
    WILLPOWER("willpower"),
    FELLOWSHIP("fellowship"),
    STRENGTH_FORTUNE("strength_fortune"),
    TOUGHNESS_FORTUNE("toughness_fortune"),
    AGILITY_FORTUNE("agility_fortune"),
    INTELLIGENCE_FORTUNE("intelligence_fortune"),
    WILLPOWER_FORTUNE("willpower_fortune"),
    FELLOWSHIP_FORTUNE("fellowship_fortune");

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
