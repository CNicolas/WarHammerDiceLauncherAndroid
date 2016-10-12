package com.aku.warhammerdicelauncher.model.dto;

import com.aku.warhammerdicelauncher.utils.enums.Characteristic;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class SkillDto implements IDto {
    private int id;
    private String name;
    private Characteristic characteristic;
    private int level;
    private int player_id;

    public SkillDto() {
    }

    public SkillDto(String name, Characteristic characteristic, int level, PlayerDto playerDto) {
        this.name = name;
        this.characteristic = characteristic;
        this.level = level;
        this.player_id = playerDto.getId();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "SkillDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characteristic=" + characteristic +
                ", level=" + level +
                ", player_id=" + player_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDto skillDto = (SkillDto) o;

        if (getId() != skillDto.getId()) return false;
        if (getLevel() != skillDto.getLevel()) return false;
        if (getPlayer_id() != skillDto.getPlayer_id()) return false;
        if (getName() != null ? !getName().equals(skillDto.getName()) : skillDto.getName() != null)
            return false;
        return getCharacteristic() == skillDto.getCharacteristic();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        result = 31 * result + getLevel();
        result = 31 * result + getPlayer_id();
        return result;
    }
//endregion
}