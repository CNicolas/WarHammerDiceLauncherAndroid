package com.whfrp3.model.player.inventory;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Item qualities.
 */
public enum Quality implements IEnumSpinner {
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
     * Quality label id.
     */
    private int labelId;

    /**
     * Quality color
     */
    private int colorId;

    /**
     * Private constructor.
     *
     * @param labelId Quality label id.
     * @param colorId Quality color id
     */
    Quality(int labelId, int colorId) {
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
