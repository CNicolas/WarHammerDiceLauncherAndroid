package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Cooldown type.
 */
public enum CooldownType implements IEnumSpinner {

    /**
     * No cooldown.
     */
    NO_COOLDOWN(R.string.passive, true),

    /**
     * Cooldown for the active talents.
     */
    TALENT(R.string.talent, true),

    /**
     * Cooldown for the actions.
     */
    ACTION(R.string.action, false),

    /**
     * Game session cooldown.
     */
    SESSION(R.string.session, true);

    private final int labelId;

    private final boolean displayable;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    CooldownType(int labelId, boolean displayable) {
        this.labelId = labelId;
        this.displayable = displayable;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }

    public static List<CooldownType> getDisplayableTypes() {
        List<CooldownType> res = new ArrayList<>();
        for (CooldownType cooldownType : values()) {
            if (cooldownType.displayable) {
                res.add(cooldownType);
            }
        }

        return res;
    }
}
