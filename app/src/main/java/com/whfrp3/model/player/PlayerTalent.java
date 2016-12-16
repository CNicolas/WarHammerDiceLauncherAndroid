package com.whfrp3.model.player;

import com.whfrp3.model.talents.Talent;

/**
 * Talent of a player.
 */
public class PlayerTalent {

    //region Properties

    /**
     * Associated talent.
     */
    private final Talent talent;

    /**
     * Id of the associated player.
     */
    private long playerId;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param talent Associated talent.
     * @param player Associated player.
     */
    public PlayerTalent(Talent talent, Player player) {
        this.talent = talent;
        this.playerId = player.getId();
    }

    /**
     * Constructor.
     *
     * @param talent   Associated talent.
     * @param playerId Id of the associated player.
     */
    public PlayerTalent(Talent talent, long playerId) {
        this.talent = talent;
        this.playerId = playerId;
    }

    //endregion

    //region Get & Set

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Talent getTalent() {
        return talent;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerTalent [");
        sb.append("talentId=").append(talent.getId()).append(", ");
        sb.append("playerId=").append(playerId);
        sb.append("]");

        return sb.toString();
    }

    //endregion
}
