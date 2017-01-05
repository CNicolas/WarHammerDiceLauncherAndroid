package com.whfrp3.model.talents;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;

/**
 * Talent class.
 */
public class Talent extends AbstractModel implements Comparable<Talent> {

    //region Properties

    /**
     * Name.
     */
    private String name;

    /**
     * Description.
     */
    private String description;

    /**
     * Type.
     */
    private TalentType type;

    /**
     * Cooldown type.
     */
    private CooldownType cooldown;
    //endregion

    //region Constructor

    public Talent() {

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

    public TalentType getType() {
        return type;
    }

    public void setType(TalentType type) {
        this.type = type;
    }

    public CooldownType getCooldown() {
        return cooldown;
    }

    public void setCooldown(CooldownType cooldown) {
        this.cooldown = cooldown;
    }

    //endregion

    @Override
    public String toString() {
        return "Talent{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", cooldown=" + cooldown + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Talent talent = (Talent) o;

        if (name != null ? !name.equals(talent.name) : talent.name != null) return false;
        if (description != null ? !description.equals(talent.description) : talent.description != null)
            return false;
        if (type != talent.type) return false;
        return cooldown == talent.cooldown;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (cooldown != null ? cooldown.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(Talent talent) {
        int compared = type.compareTo(talent.getType());
        if (compared == 0) {
            compared = name.compareTo(talent.getName());
        }
        return compared;
    }
}
