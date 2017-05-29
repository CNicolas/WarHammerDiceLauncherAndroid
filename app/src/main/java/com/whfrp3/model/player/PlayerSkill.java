package com.whfrp3.model.player;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.whfrp3.model.skills.Skill;

import java.io.Serializable;

/**
 * Skill of a player.
 */
public class PlayerSkill extends BaseObservable implements Serializable {

    //region Properties

    /**
     * Associated skill.
     */
    private final Skill skill;

    /**
     * Level of the associated skill.
     */
    private int level;

    private boolean specialized;

    //endregion

    //region Constructors

    /**
     * Constructor without skill level.
     *
     * @param skill Associated skill.
     */
    public PlayerSkill(Skill skill) {
        this.skill = skill;
        this.level = 0;
    }

    /**
     * Constructor with skill level.
     *
     * @param skill Associated skill.
     * @param level Level of the associated skill.
     */
    public PlayerSkill(Skill skill, int level) {
        this.skill = skill;
        this.level = level;
    }

    //endregion

    //region Get & Set

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Bindable
    public boolean isSpecialized() {
        return specialized;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }
//endregion

    //region Overrides

    @Override
    public String toString() {
        return "PlayerSkill [" + "skillId=" + skill.getId() + ", " + "level=" + level + "]";
    }

    //endregion
}