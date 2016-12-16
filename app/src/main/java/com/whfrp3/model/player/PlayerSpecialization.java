package com.whfrp3.model.player;

import com.whfrp3.model.Specialization;

/**
 * Specialization of a player.
 */
public class PlayerSpecialization {

    //region Properties

    /**
     * Associated specialization.
     */
    private final Specialization specialization;

    /**
     * Id of the associated player.
     */
    private long playerId;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param specialization Associated specialization.
     * @param player         Associated player.
     */
    public PlayerSpecialization(Specialization specialization, Player player) {
        this.specialization = specialization;
        this.playerId = player.getId();
    }

    /**
     * Constructor.
     *
     * @param specialization Associated specialization.
     * @param playerId       Id of the associated player.
     */
    public PlayerSpecialization(Specialization specialization, long playerId) {
        this.specialization = specialization;
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

    public Specialization getSpecialization() {
        return specialization;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerSpecialization [");
        sb.append("specializationId=").append(specialization.getId()).append(", ");
        sb.append("playerId=").append(playerId);
        sb.append("]");

        return sb.toString();
    }

    //endregion
}
