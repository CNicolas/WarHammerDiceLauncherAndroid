package com.whfrp3.model.item;

import com.whfrp3.model.enums.ItemType;

/**
 * Usable item's class.
 */
public class UsableItem extends Item {

    //region Properties

    /**
     * Item's loads.
     */
    private int load;

    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public UsableItem() {
        super(ItemType.USABLE_ITEM);
    }

    //endregion

    //region Main methods

    /**
     * Indicate if the object still has loads.
     *
     * @return true if the object has at least one load, false otherwise.
     */
    public boolean hasLoads() {
        return load > 0;
    }

    /**
     * Decrease the loads by 1.
     */
    public void decrementLoads() {
        if (load > 0) {
            load--;
        }
    }

    //endregion

    //region Get & Set

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    //endregion
}
