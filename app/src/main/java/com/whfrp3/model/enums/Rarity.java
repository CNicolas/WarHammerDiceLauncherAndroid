package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Rarity of items.
 */
public enum Rarity implements IEnumSpinner {

    COMMON(R.string.rarity_common),

    VERY_USUAL(R.string.rarity_very_usual),

    USUAL(R.string.rarity_usual),

    UNCOMMON(R.string.rarity_uncommon),

    EXOTIC(R.string.rarity_exotic);

    /**
     * Rarity label id.
     */
    private final int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Rarity label id.
     */
    Rarity(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
