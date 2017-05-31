package com.whfrp3.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The specialization model.
 */
public class Specialization {

    //region Properties

    /**
     * Technical identifier.
     */
    private long id;

    /**
     * Specialization name.
     */
    private String name;

    /**
     * Associated skill.
     */
    private Skill skill;

    //endregion

    //region Constructor

    /**
     * Default constructor.
     */
    public Specialization() {

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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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

        Specialization other = (Specialization) obj;

        return new EqualsBuilder()
                .append(id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(skill)
                .toHashCode();
    }

    //endregion
}
