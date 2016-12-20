package com.whfrp3.model.player.inventory;

import com.whfrp3.model.player.Player;

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
        super();

        setType(ItemType.USABLE_ITEM);
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    public UsableItem(Player player) {
        super(player);

        setType(ItemType.USABLE_ITEM);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public UsableItem(Item item) {
        super(item);

        setType(ItemType.USABLE_ITEM);
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

    //region Overrides
    @Override
    public String toString() {
        return "UsableItem [" + attributesToString() + ", load=" + getLoad() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UsableItem that = (UsableItem) o;

        return getLoad() == that.getLoad();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getLoad();
        return result;
    }
    //endregion
}
