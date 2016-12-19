package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Cooldown type.
 */
public enum CooldownType implements IEnumSpinner {

    /**
     * No cooldown.
     */
    NO_COOLDOWN(R.string.passive),

    /**
     * Cooldown for the active search.
     */
    TALENT(R.string.talent),

    /**
     * Cooldown for the actions.
     */
    ACTION(R.string.action),

    /**
     * Game session cooldown.
     */
    SESSION(R.string.session);

    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    CooldownType(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
