package com.whfrp3.tools.helpers;

import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.Skill;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.enums.SkillEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for skills, if needed.
 */
public abstract class SkillsHelper {

    /**
     * Create the 18 basic skills, at level 0 of expertise, for the current player.
     *
     * @return the list of new basic skills.
     */
    public static List<Skill> createBasicSkills() {
        Player player = WHFRP3Application.getPlayer();

        List<Skill> res = new ArrayList<>();
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.ATHLETICS.getSkillNameId()), SkillEnum.ATHLETICS.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.BALLISTIC.getSkillNameId()), SkillEnum.BALLISTIC.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.COORDINATION.getSkillNameId()), SkillEnum.COORDINATION.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.INTIMIDATE.getSkillNameId()), SkillEnum.INTIMIDATE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.RESILIENCE.getSkillNameId()), SkillEnum.RESILIENCE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.RIDE.getSkillNameId()), SkillEnum.RIDE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.SKULDUGGERY.getSkillNameId()), SkillEnum.SKULDUGGERY.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.STEALTH.getSkillNameId()), SkillEnum.STEALTH.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.WEAPON.getSkillNameId()), SkillEnum.WEAPON.getCharacteristic(), 0, player));

        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.CHARM.getSkillNameId()), SkillEnum.CHARM.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.DISCIPLINE.getSkillNameId()), SkillEnum.DISCIPLINE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.FIRST_AID.getSkillNameId()), SkillEnum.FIRST_AID.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.FOLKLORE.getSkillNameId()), SkillEnum.FOLKLORE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.GUILE.getSkillNameId()), SkillEnum.GUILE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.INTUITION.getSkillNameId()), SkillEnum.INTUITION.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.LEADERSHIP.getSkillNameId()), SkillEnum.LEADERSHIP.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.NATURE_LORE.getSkillNameId()), SkillEnum.NATURE_LORE.getCharacteristic(), 0, player));
        res.add(new Skill(WHFRP3Application.getResourceString(SkillEnum.OBSERVATION.getSkillNameId()), SkillEnum.OBSERVATION.getCharacteristic(), 0, player));

        return res;
    }
}
