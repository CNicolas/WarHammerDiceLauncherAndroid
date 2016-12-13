package com.whfrp3.model;

import com.whfrp3.model.enums.Characteristic;

/**
 * The skill model.
 */
public class Skill extends AbstractModel {

    //region Properties

    /**
     * PlayerSkill name.
     */
    private String name;

    /**
     * Associated characteristic.
     */
    private Characteristic characteristic;

    //endregion

    //region Constructor

    /**
     * Default constructor.
     */
    public Skill() {

    }

    /**
     * Constructor.
     *
     * @param name           PlayerSkill name.
     * @param characteristic Associated characteristic.
     */
    public Skill(String name, Characteristic characteristic) {
        this.name = name;
        this.characteristic = characteristic;
    }

    //endregion

    //region Getters

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
}
