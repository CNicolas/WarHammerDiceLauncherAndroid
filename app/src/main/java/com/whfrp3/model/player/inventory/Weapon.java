package com.whfrp3.model.player.inventory;

import com.whfrp3.model.player.Player;

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
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Weapon() {
        super();

        setType(ItemType.WEAPON);
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    public Weapon(Player player) {
        super(player);

        setType(ItemType.WEAPON);
    }
    //endregion

    public boolean canBeUsed(Range range) {
        return isEquipable() && isEquipped() && getRange().isLessThanOrEqualTo(range);
    }

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
    //endregion

    //region Overrides
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Weapon [");
        sb.append(attributesToString());
        sb.append(", damage=");
        sb.append(getDamage());
        sb.append(", criticalLevel=");
        sb.append(getCriticalLevel());
        sb.append(", range=");
        sb.append(getRange());
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Weapon weapon = (Weapon) o;

        if (getDamage() != weapon.getDamage()) return false;
        if (getCriticalLevel() != weapon.getCriticalLevel()) return false;
        return getRange() == weapon.getRange();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDamage();
        result = 31 * result + getCriticalLevel();
        result = 31 * result + getRange().hashCode();
        return result;
    }
    //endregion
}
