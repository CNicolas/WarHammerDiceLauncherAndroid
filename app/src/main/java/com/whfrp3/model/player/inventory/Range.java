package com.whfrp3.model.player.inventory;

/**
 * Portée possibles.
 */
public enum Range {
    /**
     * Engagé.
     */
    ENGAGED,

    /**
     * Courte.
     */
    SHORT,

    /**
     * Moyenne.
     */
    MEDIUM,

    /**
     * Longue.
     */
    LONG,

    /**
     * Extrême.
     */
    EXTREME;

    public boolean isLessThanOrEqualTo(Range otherRange) {
        return ordinal() <= otherRange.ordinal();
    }
}
