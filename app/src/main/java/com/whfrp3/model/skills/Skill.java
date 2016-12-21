package com.whfrp3.model.skills;

import com.whfrp3.model.AbstractModel;
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

    public boolean isFightSkill() {
        return isWeaponSkill() || isBallisticSkill();
    }

    public boolean isWeaponSkill() {
        return getId() == 9;
    }

    public boolean isBallisticSkill() {
        return getId() == 2;
    }

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

    //region Overrides
    @Override
    public String toString() {
        return "Skill{" + "name='" + name + '\'' + ", characteristic=" + characteristic + ", type=" + type + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (getName() != null ? !getName().equals(skill.getName()) : skill.getName() != null)
            return false;
        if (getCharacteristic() != skill.getCharacteristic()) return false;
        return getType() == skill.getType();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
    //endregion
}
