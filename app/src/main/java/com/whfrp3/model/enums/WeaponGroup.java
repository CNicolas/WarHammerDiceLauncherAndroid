package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Weapon groups list.
 */
public enum WeaponGroup implements IEnumSpinner {

    BARE_HAND(R.string.weapon_bare_hand),

    ONE_HAND(R.string.weapon_one_hand),

    TWO_HANDS(R.string.weapon_two_hands),

    WAND(R.string.weapon_wand),

    SCOURGE(R.string.weapon_scourge),

    POLEARM(R.string.weapon_polearm),

    SPEAR(R.string.weapon_spear),

    CAVALRY(R.string.weapon_cavalry),

    FENCING(R.string.weapon_fencing),

    CROSSBOW(R.string.weapon_crossbow),

    BOW(R.string.weapon_bow),

    THROW(R.string.weapon_throw),

    BLACK_POWDER(R.string.weapon_black_powder),

    SLING(R.string.weapon_sling);

    /**
     * Group label id.
     */
    private final int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Group label id.
     */
    WeaponGroup(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
