package com.whfrp3.model.player;

import com.whfrp3.model.AbstractModel;
import com.whfrp3.model.enums.Characteristic;

/**
 * Skill of a player.
 */
public class PlayerSkill extends AbstractModel {

    //region Properties

    /**
     * Level of the associated skill.
     */
    private int level;

    /**
     * Id of the associated skill.
     */
    private final long skillId;

    /**
     * Id of the associated player.
     */
    private final long playerId;

    //endregion

    //region Constructors

    /**
     * Constructor without skill level.
     *
     * @param skillId  Id of the associated skill.
     * @param playerId Id of the associated player.
     */
    public PlayerSkill(long skillId, long playerId) {
        this.level = 0;
        this.skillId = skillId;
        this.playerId = playerId;
    }

    /**
     * Constructor with skill level.
     *
     * @param skillId  Id of the associated skill.
     * @param playerId Id of the associated player.
     * @param level    Level of the associated skill.
     */
    public PlayerSkill(long skillId, long playerId, int level) {
        this.level = level;
        this.skillId = skillId;
        this.playerId = playerId;
    }

    //endregion

    //region Get & Set

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getSkillId() {
        return skillId;
    }

    public long getPlayerId() {
        return playerId;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerSkill [");
        sb.append("level=").append(level).append(", ");
        sb.append("skillId=").append(skillId).append(", ");
        sb.append("playerId=").append(playerId);
        sb.append("]");

        return sb.toString();
    }

    //endregion
}