package com.whfrp3.model.player.inventory;

import android.databinding.Bindable;

import com.whfrp3.BR;
import com.whfrp3.model.player.Player;

/**
 * Equipment class (Weapon or Armor).
 */
public abstract class Equipment extends Item {
    //region Properties

    /**
     * Is equipment equipped ?
     */
    private boolean isEquipped;

    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    Equipment() {
        super();
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    Equipment(Player player) {
        super(player);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    Equipment(Item item) {
        super(item);

        if (item instanceof Equipment) {
            Equipment equipment = (Equipment) item;
            setEquipped(equipment.isEquipable() && equipment.isEquipped());
        }
    }

    //endregion

    @Override
    @Bindable
    public boolean isEquipable() {
        return true;
    }

    //region Get & Set

    @Bindable
    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
        notifyPropertyChanged(BR.equipped);
    }

    //endregion
}
