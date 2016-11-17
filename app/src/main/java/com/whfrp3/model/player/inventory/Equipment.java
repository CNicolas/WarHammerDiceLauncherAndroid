package com.whfrp3.model.player.inventory;

import com.whfrp3.model.player.Player;

/**
 * Equipment class (Weapon or Armor).
 */
public abstract class Equipment extends Item {
    //region Properties

    /**
     * Is equipment equipped ?
     */
    private boolean isEquiped;

    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Equipment() {
        super();
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    public Equipment(Player player) {
        super(player);
    }

    //endregion

    @Override
    public boolean isEquipable() {
        return true;
    }

    //region Get & Set

    public boolean isEquiped() {
        return isEquiped;
    }

    public void setEquiped(boolean equiped) {
        isEquiped = equiped;
    }

    //endregion
}
