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
}
