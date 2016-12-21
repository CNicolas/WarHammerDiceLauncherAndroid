package com.whfrp3.model.player.inventory;

import android.databinding.Bindable;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.player.Player;

/**
 * Item's class.
 */
public class Item extends AbstractModel {
    //region Properties
    /**
     * Item's name.
     */
    private String name;

    /**
     * Item's description.
     */
    private String description;

    /**
     * Item's encumbrance.
     */
    private int encumbrance;

    /**
     * Item's quantity.
     */
    private int quantity;

    /**
     * Item's quality.
     */
    private Quality quality;

    /**
     * Item's type.
     */
    private ItemType type;

    /**
     * Player's id linked with the item.
     */
    private long playerId;
    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Item() {
        setType(ItemType.ITEM);
    }

    /**
     * Constructor with linked player.
     *
     * @param player Player to link with the item.
     */
    public Item(Player player) {
        playerId = player.getId();

        setType(ItemType.ITEM);
    }

    /**
     * Constructor from another Item.
     *
     * @param item the given item.
     */
    public Item(Item item) {
        setId(item.getId());
        setName(item.getName());
        setDescription(item.getDescription());
        setEncumbrance(item.getEncumbrance());
        setQuantity(item.getQuantity());
        setQuality(item.getQuality());
        setType(item.getType());
    }
    //endregion

    /**
     * Indicate if the item can be equipped.
     *
     * @return true if the item can be equipped, false otherwise.
     */
    @Bindable
    public boolean isEquipable() {
        return false;
    }

    //region Get & Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEncumbrance() {
        return encumbrance;
    }

    public void setEncumbrance(int encumbrance) {
        this.encumbrance = encumbrance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
    //endregion

    //region Item conversion methods

    /**
     * Convert the item into an armor, if possible.
     *
     * @return Armor.
     * @throws UnsupportedOperationException The item cannot be converted into an armor.
     */
    public Armor toArmor() throws UnsupportedOperationException {
        if (getType() != ItemType.ARMOR) {
            throw new UnsupportedOperationException("This item is not an armor : " + toString());
        }

        return (Armor) this;
    }

    /**
     * Convert the item into a weapon, if possible.
     *
     * @return Weapon.
     * @throws UnsupportedOperationException The item cannot be converted into a weapon.
     */
    public Weapon toWeapon() throws UnsupportedOperationException {
        if (getType() != ItemType.WEAPON) {
            throw new UnsupportedOperationException("This item is not a weapon : " + toString());
        }

        return (Weapon) this;
    }

    /**
     * Convert the item into an usable item, if possible.
     *
     * @return Usable item.
     * @throws UnsupportedOperationException The item cannot be converted into an usable item.
     */
    public UsableItem toUsableItem() throws UnsupportedOperationException {
        if (getType() != ItemType.USABLE_ITEM) {
            throw new UnsupportedOperationException("This item is not an usable item : " + toString());
        }

        return (UsableItem) this;
    }
    //endregion

    //region Overrides
    protected String attributesToString() {
        return "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description=" + getDescription() +
                ", encumbrance=" + getEncumbrance() +
                ", quantity=" + getQuantity() +
                ", quality=" + getQuality() +
                ", type=" + getType() +
                ", playerId=" + getPlayerId();
    }

    @Override
    public String toString() {
        return "Item [" + attributesToString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (getId() != item.getId()) return false;
        if (getEncumbrance() != item.getEncumbrance()) return false;
        if (getQuantity() != item.getQuantity()) return false;
        if (getPlayerId() != item.getPlayerId()) return false;
        if (!getName().equals(item.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        if (getQuality() != item.getQuality()) return false;
        return getType() == item.getType();

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getEncumbrance();
        result = 31 * result + getQuantity();
        result = 31 * result + getQuality().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
    //endregion
}
