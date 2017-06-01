package com.whfrp3.model.item;

import com.whfrp3.model.enums.ItemType;

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
        super(ItemType.ARMOR);
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
}
