package com.whfrp3.model.player;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.Skill;
import com.whfrp3.model.enums.Characteristic;

/**
 * Skill of a player.
 */
public class PlayerSkill {

    //region Properties

    /**
     * Associated skill.
     */
    private final Skill skill;

    /**
     * Id of the associated player.
     */
    private final long playerId;

    /**
     * Level of the associated skill.
     */
    private int level;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerSkill [");
        sb.append("skillId=").append(skill.getId()).append(", ");
        sb.append("playerId=").append(playerId).append(", ");
        sb.append("level=").append(level);
        sb.append("]");

        return sb.toString();
    }

    //endregion
}