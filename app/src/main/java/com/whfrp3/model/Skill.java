package com.whfrp3.model;

import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.SkillType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * The skill model.
 */
public class Skill implements Serializable {

    //region Properties

    /**
     * Technical identifier.
     */
    private long id;

    /**
     * Skill name.
     */
    private String name;

    /**
     * Associated characteristic.
     */
    private Characteristic characteristic;

    /**
     * Skill type.
     */
    private SkillType type;

    //endregion

    //region Constructor

    /**
     * Default constructor.
     */
    public Skill() {

    }

    //endregion

    public boolean isFightSkill() {
        return isWeaponSkill() || isBallisticSkill();
    }

    public boolean isWeaponSkill() {
        return getId() == 9;
    }

    public boolean isBallisticSkill() {
        return getId() == 2;
    }

    //region Get & Set

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public SkillType getType() {
        return type;
    }

    public void setType(SkillType type) {
        this.type = type;
    }


    //endregion

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Skill other = (Skill) obj;

        return new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(characteristic)
                .append(type)
                .toHashCode();
    }

    //endregion
}
