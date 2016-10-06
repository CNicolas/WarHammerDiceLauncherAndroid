package com.aku.warhammerdicelauncher.model.dto.inventory;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class ArmourDto extends InventoryItemDto {
    private int defense;
    private int soak;

    //region Constructors
    public ArmourDto() {
    }

    public ArmourDto(int id, String name, int encumbrance, int defense, int soak) {
        super(id, name, encumbrance);
        this.defense = defense;
        this.soak = soak;
    }
    //endregion

    //region Get & Set
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSoak() {
        return soak;
    }

    public void setSoak(int soak) {
        this.soak = soak;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "ArmourDto{" +
                "defense=" + defense +
                ", soak=" + soak +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ArmourDto armourDto = (ArmourDto) o;

        if (getDefense() != armourDto.getDefense()) return false;
        return getSoak() == armourDto.getSoak();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDefense();
        result = 31 * result + getSoak();
        return result;
    }
    //endregion
}
