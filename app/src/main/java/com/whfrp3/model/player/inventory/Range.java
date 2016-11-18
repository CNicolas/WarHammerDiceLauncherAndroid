package com.whfrp3.model.player.inventory;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Ranges.
 */
public enum Range implements IEnumSpinner {
    /**
     * Engaged.
     */
    ENGAGED(R.string.range_engaged),

    /**
     * Short.
     */
    SHORT(R.string.range_short),

    /**
     * Medium.
     */
    MEDIUM(R.string.range_medium),

    /**
     * Long.
     */
    LONG(R.string.range_long),

    /**
     * Extreme.
     */
    EXTREME(R.string.range_extreme);

    /**
     * Item label id.
     */
    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    Range(int labelId) {
        this.labelId = labelId;
    }

    public boolean isLessThanOrEqualTo(Range otherRange) {
        return ordinal() <= otherRange.ordinal();
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
