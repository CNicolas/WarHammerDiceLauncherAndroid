package com.whfrp3.model.player.inventory;

import com.whfrp3.model.player.Player;

/**
 * Armor's class.
 */
public class Armor extends Equipment {
    //region Properties
    /**
     * Soak.
     */
    private int soak;

    /**
     * Defense.
     */
    private int defense;
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Armor() {
        super();

        setType(ItemType.ARMOR);
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    public Armor(Player player) {
        super(player);

        setType(ItemType.ARMOR);
    }
    //endregion

    //region Get & Set
    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Armor [");
        sb.append(attributesToString());
        sb.append(", equipped=");
        sb.append(isEquipped());
        sb.append(", soak=");
        sb.append(getSoak());
        sb.append(", defense=");
        sb.append(getDefense());
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Armor armor = (Armor) o;

        if (getSoak() != armor.getSoak()) return false;
        return getDefense() == armor.getDefense();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getSoak();
        result = 31 * result + getDefense();
        return result;
    }
    //endregion
}
