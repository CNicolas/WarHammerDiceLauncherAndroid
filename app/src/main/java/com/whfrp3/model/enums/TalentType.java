package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Talent types.
 */
public enum TalentType implements IEnumSpinner {

    /**
     * Focus.
     */
    FOCUS(R.string.talent_focus),

    /**
     * Reputation.
     */
    REPUTATION(R.string.talent_reputation),

    /**
     * Tactics.
     */
    TACTICS(R.string.talent_tactics),

    /**
     * Career.
     */
    CAREER(R.string.talent_career);

    /**
     * Talent label id.
     */
    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Talent type label id.
     */
    TalentType(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
