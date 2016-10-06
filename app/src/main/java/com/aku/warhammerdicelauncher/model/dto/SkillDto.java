package com.aku.warhammerdicelauncher.model.dto;

import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class SkillDto implements IDto {
    private String name;
    private Characteristic characteristic;

    public SkillDto() {
    }

    public SkillDto(String name, Characteristic characteristic) {
        this.name = name;
        this.characteristic = characteristic;
    }

    //region Get & Set
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
                "name='" + name + '\'' +
                ", characteristic=" + characteristic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDto skillDto = (SkillDto) o;

        if (!getName().equals(skillDto.getName())) return false;
        return getCharacteristic() == skillDto.getCharacteristic();

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCharacteristic().hashCode();
        return result;
    }
    //endregion
}