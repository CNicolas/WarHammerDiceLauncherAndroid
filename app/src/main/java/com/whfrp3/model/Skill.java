package com.whfrp3.model;

import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.SkillType;

/**
 * The skill model.
 */
public class Skill extends AbstractModel {

    //region Properties

    /**
     * Skill name.
     */
    private final String name;

    /**
     * Associated characteristic.
     */
    private final Characteristic characteristic;

    /**
     * Skill type.
     */
    private final SkillType type;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param id             Skill id.
     * @param name           Skill name.
     * @param characteristic Associated characteristic.
     * @param type           Skill type.
     */
    public Skill(long id, String name, Characteristic characteristic, SkillType type) {
        this.id = id;
        this.name = name;
        this.characteristic = characteristic;
        this.type = type;
    }

    //endregion

    //region Getters

    public String getName() {
        return name;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public SkillType getType() {
        return type;
    }

    //endregion
}
