package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Item qualities.
 */
public enum ItemQuality implements IEnumSpinner {
    /**
     * Low.
     */
    LOW(R.string.quality_low, R.color.grey),

    /**
     * Normal.
     */
    NORMAL(R.string.quality_normal, R.color.black_text),

    /**
     * Superior.
     */
    SUPERIOR(R.string.quality_superior, R.color.blue),

    /**
     * Magical.
     */
    MAGIC(R.string.quality_magic, R.color.reckless);

    /**
     * ItemQuality label id.
     */
    private final int labelId;

    /**
     * ItemQuality color
     */
    private final int colorId;

    /**
     * Private constructor.
     *
     * @param labelId ItemQuality label id.
     * @param colorId ItemQuality color id
     */
    ItemQuality(int labelId, int colorId) {
        this.labelId = labelId;
        this.colorId = colorId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }

    public int getColorId() {
        return colorId;
    }
}
