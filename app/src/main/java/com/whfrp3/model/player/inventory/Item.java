package com.whfrp3.model.player.inventory;

import com.whfrp3.model.IModel;
import com.whfrp3.model.player.Player;

/**
 * Item's class.
 */
public class Item implements IModel {
    //region Properties
    /**
     * Technical identifier.
     */
    private int id;

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
    private int playerId;
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
    //endregion

    //region Get & Set
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
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
        StringBuilder sb = new StringBuilder("id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", encumbrance=");
        sb.append(getEncumbrance());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", quality=");
        sb.append(getQuality());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", playerId=");
        sb.append(getPlayerId());

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Item [");
        sb.append(attributesToString());
        sb.append("]");

        return sb.toString();
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
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getEncumbrance();
        result = 31 * result + getQuantity();
        result = 31 * result + getQuality().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getPlayerId();
        return result;
    }
    //endregion

    public static Item getItemFromType(ItemType type){
        switch (type){
            case ARMOR:
                return new Armor();
            case WEAPON:
                return new Weapon();
            case USABLE_ITEM:
                return new UsableItem();

            default:
                return new Item();
        }
    }
}
