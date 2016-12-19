package com.whfrp3.model.skills;

import com.whfrp3.R;
import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.SkillType;
import com.whfrp3.tools.BindingUtils;

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
        return BindingUtils.string(R.string.skill_weapon).equals(getName());
    }

    public boolean isBallisticSkill() {
        return BindingUtils.string(R.string.skill_ballistic).equals(getName());
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
}
