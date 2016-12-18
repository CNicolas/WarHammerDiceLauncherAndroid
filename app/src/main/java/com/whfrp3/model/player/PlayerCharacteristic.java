package com.whfrp3.model.player;

import android.databinding.BaseObservable;

import com.whfrp3.model.dices.Hand;
import com.whfrp3.model.enums.Characteristic;

import java.io.Serializable;

/**
 * Characteristic of a player.
 */
public class PlayerCharacteristic extends BaseObservable implements Serializable {

    //region Properties

    /**
     * Characteristic.
     */
    private final Characteristic characteristic;

    /**
     * Id of the associated player.
     */
    private long playerId;

    /**
     * Value of the characteristic.
     */
    private int value;

    /**
     * Fortune value of the characteristic.
     */
    private int fortuneValue;

    //endregion

    //region Constructors

    /**
     * Constructor without characteristic value.
     *
     * @param characteristic Characteristic.
     * @param player         Associated player.
     */
    public PlayerCharacteristic(Characteristic characteristic, Player player) {
        this.characteristic = characteristic;
        this.playerId = player.getId();
        this.value = 0;
        this.fortuneValue = 0;
    }

    /**
     * Constructor without characteristic value.
     *
     * @param characteristic Characteristic.
     * @param playerId       Id of the associated player.
     */
    public PlayerCharacteristic(Characteristic characteristic, long playerId) {
        this.characteristic = characteristic;
        this.playerId = playerId;
        this.value = 0;
        this.fortuneValue = 0;
    }

    /**
     * Constructor with characteristic value.
     *
     * @param characteristic Characteristic.
     * @param playerId       Id of the associated player.
     * @param value          Value of the characteristic.
     * @param fortuneValue   Fortune value of the characteristic.
     */
    public PlayerCharacteristic(Characteristic characteristic, long playerId, int value, int fortuneValue) {
        this.characteristic = characteristic;
        this.playerId = playerId;
        this.value = value;
        this.fortuneValue = fortuneValue;
    }

    //endregion

    //region Get & Set

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFortuneValue() {
        return fortuneValue;
    }

    public void setFortuneValue(int fortuneValue) {
        this.fortuneValue = fortuneValue;
    }

    //endregion

    public Hand getHand() {
        Hand hand = new Hand();
        hand.setCharacteristic(value);
        hand.setFortune(fortuneValue);

        return hand;
    }

    //region Overrides

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlayerCharacteristic [");
        sb.append("characteristic=").append(characteristic.toString()).append(", ");
        sb.append("playerId=").append(playerId).append(", ");
        sb.append("value=").append(value).append(", ");
        sb.append("fortuneValue=").append(fortuneValue);
        sb.append("]");

        return sb.toString();
    }

    //endregion
}
