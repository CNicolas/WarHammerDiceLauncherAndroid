package com.whfrp3.model.enums;

/**
 * Cooldown type.
 */
public enum CooldownType {

    /**
     * No cooldown.
     */
    NO_COOLDOWN,

    /**
     * Cooldown for the active talents.
     */
    TALENT,

    /**
     * Cooldown for the actions.
     */
    ACTION,

    /**
     * Game session cooldown.
     */
    SESSION;
}
