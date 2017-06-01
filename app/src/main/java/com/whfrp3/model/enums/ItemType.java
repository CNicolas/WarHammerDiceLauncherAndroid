package com.whfrp3.model.enums;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.IEnumSpinner;

/**
 * Item types.
 */
public enum ItemType implements IEnumSpinner {
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
    private final int labelId;

    /**
     * Private constructor.
     *
     * @param labelId Item label id.
     */
    ItemType(int labelId) {
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

    @Override
    public int getLabelId() {
        return labelId;
    }
}
