package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Talent types.
 */
public enum TalentType implements IEnumSpinner {

    /**
     * Affinity.
     */
    AFFINITY(R.string.talent_affinity, R.color.blue, true),
    /**
     * Career.
     */
    CAREER(R.string.talent_career, R.color.black, false),
    /**
     * Faith.
     */
    FAITH(R.string.talent_faith, R.color.colorPrimary, true),
    /**
     * Order.
     */
    ORDER(R.string.talent_order, R.color.violet, true),
    /**
     * Reputation.
     */
    REPUTATION(R.string.talent_reputation, R.color.orange, true),
    /**
     * Tactics.
     */
    TACTICS(R.string.talent_tactics, R.color.reckless, true),
    /**
     * Tour.
     */
    TOUR(R.string.talent_tour, R.color.conservative, true);

    /**
     * Talent label id.
     */
    private final int labelId;

    private final int colorId;

    private final boolean displayable;

    /**
     * Private constructor.
     *
     * @param labelId Talent type label id.
     */
    TalentType(int labelId, int colorId, boolean displayable) {
        this.labelId = labelId;
        this.colorId = colorId;
        this.displayable = displayable;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }

    public int getColorId() {
        return colorId;
    }

    public static List<TalentType> getDisplayableTypes() {
        List<TalentType> res = new ArrayList<>();
        for (TalentType talentType : values()) {
            if (talentType.displayable) {
                res.add(talentType);
            }
        }

        return res;
    }
}
