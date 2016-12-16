package com.whfrp3.model;

/**
 * The specialization model.
 */
public class Specialization extends AbstractModel {

    //region Properties

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
        final StringBuilder sb = new StringBuilder("Specialization{");
        sb.append("name='").append(name).append('\'');
        sb.append(", skill=").append(skill);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Specialization that = (Specialization) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        return getSkill() != null ? getSkill().equals(that.getSkill()) : that.getSkill() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSkill() != null ? getSkill().hashCode() : 0);
        return result;
    }
    //endregion
}
