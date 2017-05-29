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
     */
    PlayerCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
        this.value = 0;
        this.fortuneValue = 0;
    }

    /**
     * Constructor with characteristic value.
     *
     * @param characteristic Characteristic.
     * @param value          Value of the characteristic.
     * @param fortuneValue   Fortune value of the characteristic.
     */
    public PlayerCharacteristic(Characteristic characteristic, int value, int fortuneValue) {
        this.characteristic = characteristic;
        this.value = value;
        this.fortuneValue = fortuneValue;
    }

    //endregion

    //region Get & Set

    public Characteristic getCharacteristic() {
        return characteristic;
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
        return "PlayerCharacteristic [" + "characteristic=" + characteristic.toString() + ", " +
                "value=" + value + ", " +
                "fortuneValue=" + fortuneValue +
                "]";
    }

    //endregion
}
