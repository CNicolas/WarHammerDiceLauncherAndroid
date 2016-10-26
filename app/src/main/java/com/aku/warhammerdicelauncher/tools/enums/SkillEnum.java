package com.aku.warhammerdicelauncher.tools.enums;

import com.aku.warhammerdicelauncher.R;

/**
 * Lists the 18 basic skills with their associated characteristic.
 */
public enum SkillEnum {
    ATHLETICS(R.string.skill_athletics, Characteristic.STRENGTH),
    BALLISTIC(R.string.skill_ballistic, Characteristic.AGILITY),
    COORDINATION(R.string.skill_coordination, Characteristic.AGILITY),
    INTIMIDATE(R.string.skill_intimidate, Characteristic.STRENGTH),
    RESILIENCE(R.string.skill_resilience, Characteristic.TOUGHNESS),
    RIDE(R.string.skill_ride, Characteristic.AGILITY),
    SKULDUGGERY(R.string.skill_skulduggery, Characteristic.AGILITY),
    STEALTH(R.string.skill_stealth, Characteristic.AGILITY),
    WEAPON(R.string.skill_weapon, Characteristic.STRENGTH),

    CHARM(R.string.skill_charm, Characteristic.FELLOWSHIP),
    DISCIPLINE(R.string.skill_discipline, Characteristic.WILLPOWER),
    FIRST_AID(R.string.skill_first_aid, Characteristic.INTELLIGENCE),
    FOLKLORE(R.string.skill_folklore, Characteristic.INTELLIGENCE),
    GUILE(R.string.skill_guile, Characteristic.FELLOWSHIP),
    INTUITION(R.string.skill_intuition, Characteristic.FELLOWSHIP),
    LEADERSHIP(R.string.skill_leadership, Characteristic.FELLOWSHIP),
    NATURE_LORE(R.string.skill_nature_lore, Characteristic.INTELLIGENCE),
    OBSERVATION(R.string.skill_observation, Characteristic.INTELLIGENCE);

    private final int skillNameId;
    private final Characteristic characteristic;

    SkillEnum(final int skillNameId, final Characteristic characteristic) {
        this.skillNameId = skillNameId;
        this.characteristic = characteristic;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public int getSkillNameId() {
        return skillNameId;
    }
}
