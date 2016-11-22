package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Player races.
 */
public enum Race implements IEnumSpinner {

    /**
     * Human race.
     */
    HUMAN(R.string.race_human),

    /**
     * Dwarf race.
     */
    DWARF(R.string.race_dwarf),

    /**
     * High elf race.
     */
    HIGH_ELF(R.string.race_high_elf),

    /**
     * Wood elf race.
     */
    WOOD_ELF(R.string.race_wood_elf);

    /**
     * Item label id.
     */
    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    Race(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
