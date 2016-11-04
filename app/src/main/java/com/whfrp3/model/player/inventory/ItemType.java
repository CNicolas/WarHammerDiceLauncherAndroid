package com.whfrp3.model.player.inventory;

/**
 * Item types.
 */
public enum ItemType {
    /**
     * Standard item.
     */
    ITEM("Standard item"),

    /**
     * Usable item.
     */
    USABLE_ITEM("Usable item"),

    /**
     * Weapon.
     */
    WEAPON("Weapon"),

    /**
     * Armor.
     */
    ARMOR("Armor");

    /**
     * Item type name.
     */
    private String name;

    /**
     * Private constructor.
     *
     * @param name Item type name.
     */
    ItemType(String name) {
        this.name = name;
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
     * Return item type name.
     *
     * @return Item type name.
     */
    public String getName() {
        return name;
    }
}
