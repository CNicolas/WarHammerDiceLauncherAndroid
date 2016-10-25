package com.aku.warhammerdicelauncher.ihm.tools;

import android.text.Editable;
import android.text.TextWatcher;

import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.enums.Characteristic;

/**
 * Created by cnicolas on 18/10/2016.
 */

public class CharacteristicEditTextWatcher implements TextWatcher {
    private final Characteristic mCharacteristic;

    public CharacteristicEditTextWatcher(Characteristic playerInformation) {
        this.mCharacteristic = playerInformation;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setPlayerCharacteristic(mCharacteristic, s.toString());
        PlayerContext.updatePlayer();
    }

    private void setPlayerCharacteristic(Characteristic characteristic, String s) {
        Characteristics characteristics = PlayerContext.getPlayerInstance().getCharacteristics();

        int newValue;
        try {
            newValue = Integer.parseInt(s.toString());
        } catch (NumberFormatException nfe) {
            return;
        }

        switch (characteristic) {
            case STRENGTH:
                characteristics.setStrength(newValue);
                break;
            case TOUGHNESS:
                characteristics.setToughness(newValue);
                break;
            case AGILITY:
                characteristics.setAgility(newValue);
                break;
            case INTELLIGENCE:
                characteristics.setIntelligence(newValue);
                break;
            case WILLPOWER:
                characteristics.setWillpower(newValue);
                break;
            case FELLOWSHIP:
                characteristics.setFellowship(newValue);
                break;
            case STRENGTH_FORTUNE:
                characteristics.setStrength_fortune(newValue);
                break;
            case TOUGHNESS_FORTUNE:
                characteristics.setToughness_fortune(newValue);
                break;
            case AGILITY_FORTUNE:
                characteristics.setAgility_fortune(newValue);
                break;
            case INTELLIGENCE_FORTUNE:
                characteristics.setIntelligence_fortune(newValue);
                break;
            case WILLPOWER_FORTUNE:
                characteristics.setWillpower_fortune(newValue);
                break;
            case FELLOWSHIP_FORTUNE:
                characteristics.setFellowship_fortune(newValue);
                break;
            default:
                break;
        }

    }
}
