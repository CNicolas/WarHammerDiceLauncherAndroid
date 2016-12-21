package com.whfrp3.model.talents;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Talent class.
 */
public class Talent extends AbstractModel {

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

    /**
     * Effects.
     */
    private List<TalentEffect> effects;

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

    public List<TalentEffect> getEffects() {
        if (effects == null) {
            effects = new ArrayList<>();
        }

        return effects;
    }

    public void setEffects(List<TalentEffect> effects) {
        this.effects = effects;
    }

    //endregion


    @Override
    public String toString() {
        return "Talent{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", cooldown=" + cooldown +
                ", effects=" + effects + '}';
    }
}
