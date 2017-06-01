package com.whfrp3.model.item;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.whfrp3.model.enums.ItemQuality;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.enums.ItemType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Item's class.
 */
public class Item extends BaseObservable implements Serializable {
    //region Properties

    /**
     * Technical identifier.
     */
    private long id;

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
     * Item's type.
     */
    private ItemType type;

    //endregion

    //region Constructors

    /**
     * Default constructor.
     */
    public Item() {
        setType(ItemType.ITEM);
    }

    /**
     * Constructor with item type.
     *
     * @param type Item type.
     */
    protected Item(ItemType type) {
        this.type = type;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item other = (Item) o;

        return new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }
    //endregion
}
