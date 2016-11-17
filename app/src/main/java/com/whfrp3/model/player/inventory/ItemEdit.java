package com.whfrp3.model.player.inventory;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Class used by the ItemEditActivity.
 */
public class ItemEdit extends BaseObservable {
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
     * Damage.
     */
    private int damage;

    /**
     * Critical level.
     */
    private int criticalLevel;

    /**
     * Range.
     */
    private Range range;

    /**
     * Soak.
     */
    private int soak;

    /**
     * Defense.
     */
    private int defense;

    /**
     * Item's loads.
     */
    private int load;
    //endregion

    //region Constructors
    public ItemEdit(Item item) {
        setName(item.getName());
        setDescription(item.getDescription());
        setEncumbrance(item.getEncumbrance());
        setQuantity(item.getQuantity());
        setQuality(item.getQuality());
        setType(item.getType());

        switch (item.getType()) {
            case ARMOR:
                Armor armor = item.toArmor();
                setSoak(armor.getSoak());
                setDefense(armor.getDefense());
                break;
            case WEAPON:
                Weapon weapon = item.toWeapon();
                setDamage(weapon.getDamage());
                setCriticalLevel(weapon.getCriticalLevel());
                break;
            case USABLE_ITEM:
                UsableItem usableItem = item.toUsableItem();
                setLoad(usableItem.getLoad());
                break;
        }
    }
    //endregion

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

    @Bindable
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCriticalLevel() {
        return criticalLevel;
    }

    public void setCriticalLevel(int criticalLevel) {
        this.criticalLevel = criticalLevel;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    //endregion
}
