package com.whfrp3.model.player;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.whfrp3.model.Action;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Action of a player.
 */
public class PlayerAction extends BaseObservable {

    //region Properties

    /**
     * Technical identifier of the associated action.
     */
    private final long actionId;

    /**
     * Associated action.
     */
    private transient Action action;

    /**
     * Is action exhausted.
     */
    private boolean exhausted;

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param action Associated action.
     */
    public PlayerAction(Action action) {
        this.actionId = action.getId();
        this.action = action;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        // TODO : Create ActionHelper
    }

    //endregion

    //region Get & Set

    public long getActionId() {
        return actionId;
    }

    public Action getAction() {
        return action;
    }

    @Bindable
    public boolean isExhausted() {
        return exhausted;
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

        PlayerAction other = (PlayerAction) obj;

        return new EqualsBuilder()
                .append(actionId, other.actionId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(actionId)
                .toHashCode();
    }

    //endregion
}
