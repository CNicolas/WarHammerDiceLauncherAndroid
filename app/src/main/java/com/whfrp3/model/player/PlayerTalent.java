package com.whfrp3.model.player;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.Talent;
import com.whfrp3.tools.helpers.TalentHelper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Talent of a player.
 */
public class PlayerTalent extends BaseObservable {

    //region Properties

    /**
     * Technical identifier of the associated talent.
     */
    private final long talentId;

    /**
     * Associated talent.
     */
    private transient Talent talent;

    /**
     * Is talent equipped.
     */
    private boolean equipped;

    /**
     * Is talent exhausted.
     */
    private boolean exhausted;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param talent Associated talent.
     */
    public PlayerTalent(Talent talent) {
        this.talentId = talent.getId();
        this.talent = talent;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        this.talent = TalentHelper.getInstance().getTalentById(talentId);
    }

    public boolean isExhaustible() {
        CooldownType cooldown = talent.getCooldown();
        return cooldown != CooldownType.NO_COOLDOWN;
    }

    //endregion

    //region Get & Set

    public long getTalentId() {
        return talentId;
    }

    public Talent getTalent() {
        return talent;
    }

    @Bindable
    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
        notifyPropertyChanged(BR.equipped);
    }

    @Bindable
    public boolean isExhausted() {
        return exhausted && isExhaustible();
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
        notifyPropertyChanged(BR.exhausted);
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PlayerTalent other = (PlayerTalent) obj;

        return new EqualsBuilder()
                .append(talentId, other.talentId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(talentId)
                .toHashCode();
    }

    //endregion
}
