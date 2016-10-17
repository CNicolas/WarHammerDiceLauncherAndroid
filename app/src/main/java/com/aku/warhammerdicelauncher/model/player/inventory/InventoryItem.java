package com.aku.warhammerdicelauncher.model.player.inventory;

import com.aku.warhammerdicelauncher.model.IModel;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class InventoryItem implements IModel {
    private int id;
    private String name;
    private int encumbrance;
    private String notes;

    //region Constructors
    public InventoryItem() {
    }

    public InventoryItem(int id, String name, int encumbrance) {
        this.id = id;
        this.name = name;
        this.encumbrance = encumbrance;
    }
    //endregion

    //region Get & Set
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

    public int getEncumbrance() {
        return encumbrance;
    }

    public void setEncumbrance(int encumbrance) {
        this.encumbrance = encumbrance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", encumbrance=" + encumbrance +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItem that = (InventoryItem) o;

        if (getId() != that.getId()) return false;
        if (getEncumbrance() != that.getEncumbrance()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return getNotes() != null ? getNotes().equals(that.getNotes()) : that.getNotes() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getEncumbrance();
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        return result;
    }

    //endregion
}
