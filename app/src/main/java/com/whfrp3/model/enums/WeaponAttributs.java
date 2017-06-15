package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Weapon attributs list.
 */
public enum WeaponAttributs implements IEnumSpinner {

    HEAVY(R.string.weapon_attr_heavy),

    DEFENSIVE(R.string.weapon_attr_defensive),

    FAST(R.string.weapon_attr_fast),

    SLOW(R.string.weapon_attr_slow),

    HATEFUL(R.string.weapon_attr_hateful),

    SPECIAL(R.string.weapon_attr_special),

    UNRELIABLE_1(R.string.weapon_attr_unreliable_1),

    UNRELIABLE_2(R.string.weapon_attr_unreliable_2),

    PIERCE_1(R.string.weapon_attr_pierce_1),

    CHARGE(R.string.weapon_attr_charge),

    THROW(R.string.weapon_attr_throw),

    PARALYZING(R.string.weapon_attr_paralyzing),

    DEFLAGRATION(R.string.weapon_attr_deflagration);

    /**
     * Attribut label id.
     */
    private final int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Attribut label id.
     */
    WeaponAttributs(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public int getLabelId() {
        return labelId;
    }
}
