package com.whfrp3.model;

import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Talent class.
 */
public class Talent implements Comparable<Talent> {

    //region Properties

    /**
     * Technical identifier.
     */
    private long id;

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

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Talent other = (Talent) obj;

        return new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(description)
                .append(type)
                .append(cooldown)
                .toHashCode();
    }

    @Override
    public int compareTo(Talent talent) {
        int compared = type.compareTo(talent.getType());
        if (compared == 0) {
            compared = name.compareTo(talent.getName());
        }
        return compared;
    }

    //endregion
}
