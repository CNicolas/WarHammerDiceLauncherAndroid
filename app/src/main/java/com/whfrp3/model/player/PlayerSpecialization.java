package com.whfrp3.model.player;

import com.whfrp3.model.Specialization;
import com.whfrp3.tools.helpers.SpecializationHelper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Specialization of a player.
 */
public class PlayerSpecialization {

    //region Properties

    /**
     * Technical identifier of the associated specialization.
     */
    private final long specializationId;

    /**
     * Associated specialization.
     */
    private transient Specialization specialization;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param specialization Associated specialization.
     */
    public PlayerSpecialization(Specialization specialization) {
        this.specializationId = specialization.getId();
        this.specialization = specialization;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        this.specialization = SpecializationHelper.getInstance().getSpecialization(specializationId);
    }

    //endregion

    //region Get & Set

    public long getSpecializationId() {
        return specializationId;
    }

    public Specialization getSpecialization() {
        return specialization;
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

        PlayerSpecialization other = (PlayerSpecialization) o;

        return new EqualsBuilder()
                .append(specializationId, other.specializationId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(specializationId)
                .toHashCode();
    }

    //endregion
}
