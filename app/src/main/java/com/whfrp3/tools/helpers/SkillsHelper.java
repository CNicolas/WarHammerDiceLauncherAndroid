package com.whfrp3.tools.helpers;

import android.content.Context;

import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.PlayerContext;
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
     * @param context to fetch the string resources.
     * @return the list of new basic skills.
     */
    public static List<Skill> createBasicSkills(Context context) {
        Player player = PlayerContext.getPlayerInstance();

        List<Skill> res = new ArrayList<>();
        res.add(new Skill(context.getResources().getString(SkillEnum.ATHLETICS.getSkillNameId()), SkillEnum.ATHLETICS.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.BALLISTIC.getSkillNameId()), SkillEnum.BALLISTIC.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.COORDINATION.getSkillNameId()), SkillEnum.COORDINATION.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.INTIMIDATE.getSkillNameId()), SkillEnum.INTIMIDATE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.RESILIENCE.getSkillNameId()), SkillEnum.RESILIENCE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.RIDE.getSkillNameId()), SkillEnum.RIDE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.SKULDUGGERY.getSkillNameId()), SkillEnum.SKULDUGGERY.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.STEALTH.getSkillNameId()), SkillEnum.STEALTH.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.WEAPON.getSkillNameId()), SkillEnum.WEAPON.getCharacteristic(), 0, player));

        res.add(new Skill(context.getResources().getString(SkillEnum.CHARM.getSkillNameId()), SkillEnum.CHARM.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.DISCIPLINE.getSkillNameId()), SkillEnum.DISCIPLINE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.FIRST_AID.getSkillNameId()), SkillEnum.FIRST_AID.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.FOLKLORE.getSkillNameId()), SkillEnum.FOLKLORE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.GUILE.getSkillNameId()), SkillEnum.GUILE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.INTUITION.getSkillNameId()), SkillEnum.INTUITION.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.LEADERSHIP.getSkillNameId()), SkillEnum.LEADERSHIP.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.NATURE_LORE.getSkillNameId()), SkillEnum.NATURE_LORE.getCharacteristic(), 0, player));
        res.add(new Skill(context.getResources().getString(SkillEnum.OBSERVATION.getSkillNameId()), SkillEnum.OBSERVATION.getCharacteristic(), 0, player));

        return res;
    }
}
