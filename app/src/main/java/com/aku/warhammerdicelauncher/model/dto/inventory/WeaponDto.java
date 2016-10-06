package com.aku.warhammerdicelauncher.model.dto.inventory;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class WeaponDto extends InventoryItemDto {
    private int damage;
    private int critical;

    //region Constructors
    public WeaponDto() {
    }

    public WeaponDto(int id, String name, int encumbrance, int damage, int critical) {
        super(id, name, encumbrance);
        this.damage = damage;
        this.critical = critical;
    }
    //endregion

    //region Get & Set
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "WeaponDto{" +
                "damage=" + damage +
                ", critical=" + critical +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WeaponDto armourDto = (WeaponDto) o;

        if (getDamage() != armourDto.getDamage()) return false;
        return getCritical() == armourDto.getCritical();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDamage();
        result = 31 * result + getCritical();
        return result;
    }
    //endregion
}
