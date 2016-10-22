package com.aku.warhammerdicelauncher.model.player.inventory;

import com.aku.warhammerdicelauncher.model.player.Player;

/**
 * Classe représentant un objet utilisable.
 */
public class UsableItem extends Item {
    //region Propriétés
    /**
     * Nombre de charge de l'objet.
     */
    private int load;
    //endregion

    //region Constructeurs

    /**
     * Constructeur par défaut.
     */
    public UsableItem() {
        super();

        setType(ItemType.USABLE_ITEM);
    }

    /**
     * Constructeur avec le joueur associé en paramètre.
     *
     * @param player Joueur à associer à l'objet.
     */
    public UsableItem(Player player) {
        super(player);

        setType(ItemType.USABLE_ITEM);
    }
    //endregions

    //region Get & Set

    /**
     * Renvoie le nombre de charge de l'objet.
     *
     * @return Nombre de charge de l'objet.
     */
    public int getLoad() {
        return load;
    }

    /**
     * Modifie le nombre de charge de l'objet.
     *
     * @param load Nombre de charge de l'objet.
     */
    public void setLoad(int load) {
        this.load = load;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UsableItem [");
        sb.append(attributesToString());
        sb.append(", load=");
        sb.append(getLoad());
        sb.append("]");

        return sb.toString();
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
