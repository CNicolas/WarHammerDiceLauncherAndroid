package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Talent types.
 */
public enum TalentType implements IEnumSpinner {

    /**
     * Affinity.
     */
    AFFINITY(R.string.talent_affinity, R.color.blue),
    /**
     * Career.
     */
    CAREER(R.string.talent_career, R.color.black),
    /**
     * Faith.
     */
    FAITH(R.string.talent_faith, R.color.colorPrimary),
    /**
     * Order.
     */
    ORDER(R.string.talent_order, R.color.violet),
    /**
     * Reputation.
     */
    REPUTATION(R.string.talent_reputation, R.color.orange),
    /**
     * Tactics.
     */
    TACTICS(R.string.talent_tactics, R.color.reckless),
    /**
     * Tour.
     */
    TOUR(R.string.talent_tour, R.color.conservative);

    /**
     * Talent label id.
     */
    private int labelId;

    private int colorId;

    /**
     * Private constructor.
     *
     * @param labelId Talent type label id.
     */
    TalentType(int labelId, int colorId) {
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
