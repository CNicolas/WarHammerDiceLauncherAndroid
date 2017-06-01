package com.whfrp3.model.player;

import com.whfrp3.model.enums.ItemQuality;
import com.whfrp3.model.item.Item;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Item of a player.
 */
public class PlayerItem implements Serializable {

    //region Properties

    /**
     * Technical identifier of the associated item.
     */
    private final long itemId;

    /**
     * Associated item.
     */
    private transient Item item;

    /**
     * Item's quantity.
     */
    private int quantity;

    /**
     * Item's quality.
     */
    private ItemQuality quality;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param item Associated item.
     */
    public PlayerItem(Item item) {
        this.itemId = item.getId();
        this.item = item;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        // TODO : Create ItemHelper
    }

    //endregion

    //region Get & Set

    public long getItemId() {
        return itemId;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemQuality getQuality() {
        return quality;
    }

    public void setQuality(ItemQuality quality) {
        this.quality = quality;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PlayerItem other = (PlayerItem) obj;

        return new EqualsBuilder()
                .append(itemId, other.itemId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(itemId)
                .toHashCode();
    }

    //endregion
}
