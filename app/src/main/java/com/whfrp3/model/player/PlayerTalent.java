package com.whfrp3.model.player;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.talents.Talent;

/**
 * Talent of a player.
 */
public class PlayerTalent extends BaseObservable {

    //region Properties

    /**
     * Associated talent.
     */
    private final Talent talent;

    /**
     * Id of the associated player.
     */
    private long playerId;

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
     * @param talent   Associated talent.
     * @param playerId Id of the associated player.
     */
    public PlayerTalent(Talent talent, long playerId) {
        this.talent = talent;
        this.playerId = playerId;
    }

    //endregion

    public boolean isExhaustible() {
        CooldownType cooldown = talent.getCooldown();
        return cooldown != CooldownType.NO_COOLDOWN;
    }

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
        final StringBuffer sb = new StringBuffer("PlayerTalent{");
        sb.append("talent=").append(talent);
        sb.append(", playerId=").append(playerId);
        sb.append(", equipped=").append(equipped);
        sb.append(", exhausted=").append(exhausted);
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
