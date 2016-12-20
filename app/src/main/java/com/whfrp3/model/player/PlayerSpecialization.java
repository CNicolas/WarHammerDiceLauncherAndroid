package com.whfrp3.model.player;

import com.whfrp3.model.skills.Specialization;

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
        return "PlayerSpecialization [" + "specializationId=" + specialization.getId() + ", " +
                "playerId=" + playerId +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSpecialization that = (PlayerSpecialization) o;

        return getPlayerId() == that.getPlayerId() && (getSpecialization() != null ? getSpecialization().equals(that.getSpecialization()) : that.getSpecialization() == null);

    }

    @Override
    public int hashCode() {
        int result = getSpecialization() != null ? getSpecialization().hashCode() : 0;
        result = 31 * result + (int) (getPlayerId() ^ (getPlayerId() >>> 32));
        return result;
    }
    //endregion
}
