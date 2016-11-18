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
    Quality(int labelId){
        this.labelId=labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
