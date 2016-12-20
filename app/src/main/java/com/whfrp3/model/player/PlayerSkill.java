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
     * Id of the associated player.
     */
    private long playerId;

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
     * @param skill  Associated skill.
     * @param player Associated player.
     */
    public PlayerSkill(Skill skill, Player player) {
        this.skill = skill;
        this.playerId = player.getId();
        this.level = 0;
    }

    /**
     * Constructor with skill level.
     *
     * @param skill    Associated skill.
     * @param playerId Id of the associated player.
     * @param level    Level of the associated skill.
     */
    public PlayerSkill(Skill skill, long playerId, int level) {
        this.skill = skill;
        this.playerId = playerId;
        this.level = level;
    }

    /**
     * Constructor with skill level.
     *
     * @param skill  Associated skill.
     * @param player Associated player.
     * @param level  Level of the associated skill.
     */
    public PlayerSkill(Skill skill, Player player, int level) {
        this.skill = skill;
        this.playerId = player.getId();
        this.level = level;
    }

    //endregion

    //region Get & Set

    public Skill getSkill() {
        return skill;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
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
        return "PlayerSkill [" + "skillId=" + skill.getId() + ", " + "playerId=" + playerId + ", " + "level=" + level + "]";
    }

    //endregion
}