package com.whfrp3.model.item;

import com.whfrp3.model.enums.ItemType;
import com.whfrp3.model.enums.Range;
import com.whfrp3.model.enums.WeaponAttributs;
import com.whfrp3.model.enums.WeaponGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Weapon's class.
 */
public class Weapon extends Equipment {

    //region Properties

    /**
     * Damage.
     */
    private int damage;

    /**
     * Critical level.
     */
    private int criticalLevel;

    /**
     * Range.
     */
    private Range range;

    /**
     * Group.
     */
    private WeaponGroup group;

    /**
     * Attributs list.
     */
    private List<WeaponAttributs> attributs;

    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Weapon() {
        super(ItemType.WEAPON);
    }

    //endregion

    //region Main methods

    public boolean isDistance() {
        return range.ordinal() >= Range.SHORT.ordinal();
    }

    //endregion

    //region Get & Set

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public WeaponGroup getGroup() {
        return group;
    }

    public void setGroup(WeaponGroup group) {
        this.group = group;
    }

    public List<WeaponAttributs> getAttributs() {
        if (attributs == null) {
            attributs = new ArrayList<>();
        }

        return attributs;
    }

    public void setAttributs(List<WeaponAttributs> attributs) {
        this.attributs = attributs;
    }

    //endregion
}
