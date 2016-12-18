package com.whfrp3.model.enums;

import com.whfrp3.R;

/**
 * List all the different characteristics.
 */
public enum Characteristic {

    /**
     * Strength.
     */
    STRENGTH(R.string.charac_strength_long, R.string.charac_strength_short),

    /**
     * Toughness.
     */
    TOUGHNESS(R.string.charac_toughness_long, R.string.charac_toughness_short),

    /**
     * Agility.
     */
    AGILITY(R.string.charac_agility_long, R.string.charac_agility_short),

    /**
     * Intelligence.
     */
    INTELLIGENCE(R.string.charac_intelligence_long, R.string.charac_intelligence_short),

    /**
     * Willpower.
     */
    WILLPOWER(R.string.charac_willpower_long, R.string.charac_willpower_short),

    /**
     * Fellowship.
     */
    FELLOWSHIP(R.string.charac_fellowship_long, R.string.charac_fellowship_short);

    /**
     * Long label id.
     */
    private final int longLabelId;

    /**
     * Short label id.
     */
    private final int shortLabelId;

    /**
     * Private constructor.
     *
     * @param longLabelId  Long label id.
     * @param shortLabelId Short label id.
     */
    Characteristic(final int longLabelId, final int shortLabelId) {
        this.longLabelId = longLabelId;
        this.shortLabelId = shortLabelId;
    }

    /**
     * Return characteristic long label id.
     *
     * @return Long label id.
     */
    public int getLongLabelId() {
        return longLabelId;
    }

    /**
     * Return characteristic short label id.
     *
     * @return Short label id.
     */
    public int getShortLabelId() {
        return shortLabelId;
    }
}
