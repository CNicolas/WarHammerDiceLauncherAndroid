package com.whfrp3.model.talents;

import com.whfrp3.model.Skill;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;

public class TalentSearchFields {
    private String name;
    private String description;
    private Characteristic characteristic;
    private Skill skill;
    private TalentType talentType;
    private CooldownType cooldownType;

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

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public TalentType getTalentType() {
        return talentType;
    }

    public void setTalentType(TalentType talentType) {
        this.talentType = talentType;
    }

    public CooldownType getCooldownType() {
        return cooldownType;
    }

    public void setCooldownType(CooldownType cooldownType) {
        this.cooldownType = cooldownType;
    }
}
