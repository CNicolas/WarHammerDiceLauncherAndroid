package com.whfrp3.model.talents;

import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.skills.Skill;

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

    //region Overrides
    @Override
    public String toString() {
        return "TalentSearchFields{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", characteristic=" + characteristic +
                ", skill=" + skill +
                ", talentType=" + talentType +
                ", cooldownType=" + cooldownType + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TalentSearchFields that = (TalentSearchFields) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getCharacteristic() != that.getCharacteristic()) return false;
        if (getSkill() != null ? !getSkill().equals(that.getSkill()) : that.getSkill() != null)
            return false;
        if (getTalentType() != that.getTalentType()) return false;
        return getCooldownType() == that.getCooldownType();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        result = 31 * result + (getSkill() != null ? getSkill().hashCode() : 0);
        result = 31 * result + (getTalentType() != null ? getTalentType().hashCode() : 0);
        result = 31 * result + (getCooldownType() != null ? getCooldownType().hashCode() : 0);
        return result;
    }
    //endregion
}
