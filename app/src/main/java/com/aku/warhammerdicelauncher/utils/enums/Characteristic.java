package com.aku.warhammerdicelauncher.utils.enums;

/**
 * Created by cnicolas on 06/10/2016.
 */

public enum Characteristic {
    STRENGTH("strength"),
    TOUGHNESS("toughness"),
    AGILITY("agility"),
    INTELLIGENCE("intelligence"),
    WILLPOWER("willpower"),
    FELLOWSHIP("fellowship");

    private final String characteristic;

    Characteristic(final String characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public String toString() {
        return characteristic;
    }
}
