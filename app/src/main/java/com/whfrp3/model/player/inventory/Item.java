package com.whfrp3.model.player.inventory;

import com.whfrp3.model.IModel;
import com.whfrp3.model.player.Player;

/**
 * Classe représentant un objet.
 */
public class Item implements IModel {
    //region Propriétés
    /**
     * Identifiant technique.
     */
    private int id;

    /**
     * Nom de l'objet.
     */
    private String name;

    /**
     * Description.
     */
    private String description;

    /**
     * Encombrement.
     */
    private int encumbrance;

    /**
     * Quantité de cet objet dans l'inventaire du joueur.
     */
    private int quantity;

    /**
     * Qualité.
     */
    private Quality quality;

    /**
     * Type d'objet.
     */
    private ItemType type;

    /**
     * Identifiant technique du joueur associé.
     */
    private int playerId;
    //endregion

    //region Constructeurs

    /**
     * Constructeur par défaut.
     */
    public Item() {
        setType(ItemType.ITEM);
    }

    /**
     * Constructeur avec le joueur associé en paramètre.
     *
     * @param player Joueur à associer à l'objet.
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

    //region Méthodes de conversion de l'objet

    /**
     * Convertit l'objet en armure si son type est compatible.
     *
     * @return Armure.
     * @throws UnsupportedOperationException Impossible de convertir l'objet en armure.
     */
    public Armor toArmor() throws UnsupportedOperationException {
        if (getType() != ItemType.ARMOR) {
            throw new UnsupportedOperationException("Cet objet n'est pas une armure : " + toString());
        }

        return (Armor) this;
    }

    /**
     * Convertit l'objet en arme si son type est compatible.
     *
     * @return Arme.
     * @throws UnsupportedOperationException Impossible de convertir l'objet en arme.
     */
    public Weapon toWeapon() throws UnsupportedOperationException {
        if (getType() != ItemType.WEAPON) {
            throw new UnsupportedOperationException("Cet objet n'est pas une arme : " + toString());
        }

        return (Weapon) this;
    }

    /**
     * Convertit l'objet en objet utisable si son type est compatible.
     *
     * @return Objet utilisable.
     * @throws UnsupportedOperationException Impossible de convertir l'objet en objet utisable.
     */
    public UsableItem toUsableItem() throws UnsupportedOperationException {
        if (getType() != ItemType.USABLE_ITEM) {
            throw new UnsupportedOperationException("Cet objet n'est pas un objet utilisable : " + toString());
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
}
