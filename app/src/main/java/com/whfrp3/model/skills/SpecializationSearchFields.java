package com.whfrp3.model.skills;

import com.whfrp3.model.enums.Characteristic;

public class SpecializationSearchFields {
    private String name;
    private Skill skill;
    private Characteristic characteristic;

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

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    //region Overrides
    @Override
    public String toString() {
        return "SpecializationSearchFields{" + "name='" + name + '\'' + ", skill=" + skill + ", characteristic=" + characteristic + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecializationSearchFields that = (SpecializationSearchFields) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getSkill() != null ? !getSkill().equals(that.getSkill()) : that.getSkill() != null)
            return false;
        return getCharacteristic() == that.getCharacteristic();

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSkill() != null ? getSkill().hashCode() : 0);
        result = 31 * result + (getCharacteristic() != null ? getCharacteristic().hashCode() : 0);
        return result;
    }
    //endregion
}
