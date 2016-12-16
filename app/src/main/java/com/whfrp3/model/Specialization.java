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
}
