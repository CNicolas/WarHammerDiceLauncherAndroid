package com.whfrp3.model.item;

import android.databinding.Bindable;

import com.whfrp3.BR;
import com.whfrp3.model.enums.ItemType;
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
    public Equipment() {

    }

    /**
     * Constructor with item type.
     *
     * @param type Item type.
     */
    protected Equipment(ItemType type) {
        super(type);
    }

    //endregion

    //region Main methods

    @Override
    @Bindable
    public boolean isEquipable() {
        return true;
    }

    //endregion

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
