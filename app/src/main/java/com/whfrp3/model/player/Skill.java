package com.whfrp3.model.player;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.Characteristic;

/**
 * The Skill model.
 */
public class Skill extends AbstractModel {
    private String name;
    private Characteristic characteristic;
    private int level;
    private long playerId;

    public Skill() {
    }

    public Skill(String name, Characteristic characteristic, int level, Player player) {
        this.name = name;
        this.characteristic = characteristic;
        this.level = level;
        this.playerId = player.getId();
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characteristic=" + characteristic +
                ", level=" + level +
                ", playerId=" + playerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (getId() != skill.getId()) return false;
        if (getLevel() != skill.getLevel()) return false;
        if (getPlayerId() != skill.getPlayerId()) return false;
        if (getName() != null ? !getName().equals(skill.getName()) : skill.getName() != null)
            return false;
        return getCharacteristic() == skill.getCharacteristic();

    }

    @Override
    public int hashCode() {
        int result = (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        result = 31 * result + getLevel();
        return result;
    }
    //endregion
}