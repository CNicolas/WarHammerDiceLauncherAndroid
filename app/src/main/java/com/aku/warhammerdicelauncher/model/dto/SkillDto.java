package com.aku.warhammerdicelauncher.model.dto;

import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class SkillDto implements IDto {
    private int id;
    private String name;
    private Characteristic characteristic;
    private int

    public SkillDto() {
    }

    public SkillDto(String name, Characteristic characteristic) {
        this.name = name;
        this.characteristic = characteristic;
    }

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

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return "SkillDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characteristic=" + characteristic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDto skillDto = (SkillDto) o;

        if (getId() != skillDto.getId()) return false;
        if (getName() != null ? !getName().equals(skillDto.getName()) : skillDto.getName() != null)
            return false;
        return getCharacteristic() == skillDto.getCharacteristic();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        return result;
    }
    //endregion
}