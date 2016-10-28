package com.whfrp3.model.player.inventory;

import com.whfrp3.R;

/**
 * Item qualities.
 */
public enum Quality {
    /**
     * Low.
     */
    LOW(R.string.quality_low),

    /**
     * Normal.
     */
    NORMAL(R.string.quality_normal),

    /**
     * Superior.
     */
    SUPERIOR(R.string.quality_superior);

    /**
     * Quality label id.
     */
    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Quality label id.
     */
    private Quality(int labelId){
        this.labelId=labelId;
    }

    /**
     * Return quality label id.
     *
     * @return Quality label id.
     */
    public int getLabelId() {
        return labelId;
    }
}
