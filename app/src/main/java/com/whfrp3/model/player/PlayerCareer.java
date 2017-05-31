package com.whfrp3.model.player;

import com.whfrp3.model.Career;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Career of a player
 */
public class PlayerCareer {

    //region Properties

    /**
     * Technical identifier of the associated career.
     */
    private final long careerId;

    /**
     * Associated career.
     */
    private transient Career career;

    /**
     * Number of the career for the player.
     */
    private int number;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param career Associated career.
     * @param number Number of the career for the player.
     */
    public PlayerCareer(Career career, int number) {
        this.careerId = career.getId();
        this.career = career;
        this.number = number;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        // TODO : Create CareerHelper
    }

    //endregion

    //region Get & Set

    public long getCareerId() {
        return careerId;
    }

    public Career getCareer() {
        return career;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCareer other = (PlayerCareer) o;

        return new EqualsBuilder()
                .append(careerId, other.careerId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(careerId)
                .toHashCode();
    }

    //endregion
}
