package com.whfrp3.model.player.inventory;

import com.whfrp3.R;

/**
 * Item types.
 */
public enum ItemType {
    /**
     * Standard item.
     */
    ITEM(R.string.item),

    /**
     * Usable item.
     */
    USABLE_ITEM(R.string.usable_item),

    /**
     * Weapon.
     */
    WEAPON(R.string.weapon),

    /**
     * Armor.
     */
    ARMOR(R.string.armor);

    /**
     * Item label id.
     */
    private int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    private ItemType(int labelId) {
        this.labelId = labelId;
    }

    /**
     * Return the ItemType value according to the ordinal.
     *
     * @param ordinal Ordinal of the ItemType.
     * @return ItemType value according to the ordinal.
     */
    public static ItemType getByOrdinal(int ordinal) {
        for (ItemType type : values()) {
            if (type.ordinal() == ordinal) {
                return type;
            }
        }

        throw new IllegalArgumentException("No ItemType value found for this ordinal [" + ordinal + "]");
    }

    /**
     * Return item label id.
     *
     * @return Item label id.
     */
    public int getLabelId() {
        return labelId;
    }
}
