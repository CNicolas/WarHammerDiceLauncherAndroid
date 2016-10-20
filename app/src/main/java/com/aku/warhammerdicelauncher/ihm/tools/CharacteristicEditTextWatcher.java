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
        try {
            int newValue = Integer.parseInt(s.toString());
            setPlayerCharacteristic(mCharacteristic, newValue);
        } catch (NumberFormatException nfe) {

        }
    }

    private void setPlayerCharacteristic(Characteristic characteristic, int newValue) {
        Characteristics characteristics = PlayerContext.getPlayerInstance().getCharacteristics();
        switch (characteristic) {
            case STRENGTH:
                characteristics.setStrength(newValue);
                return;
            case TOUGHNESS:
                characteristics.setToughness(newValue);
                return;
            case AGILITY:
                characteristics.setAgility(newValue);
                return;
            case INTELLIGENCE:
                characteristics.setIntelligence(newValue);
                return;
            case WILLPOWER:
                characteristics.setWillpower(newValue);
                return;
            case FELLOWSHIP:
                characteristics.setFellowship(newValue);
                return;
            case STRENGTH_FORTUNE:
                characteristics.setStrength_fortune(newValue);
                return;
            case TOUGHNESS_FORTUNE:
                characteristics.setToughness_fortune(newValue);
                return;
            case AGILITY_FORTUNE:
                characteristics.setAgility_fortune(newValue);
                return;
            case INTELLIGENCE_FORTUNE:
                characteristics.setIntelligence_fortune(newValue);
                return;
            case WILLPOWER_FORTUNE:
                characteristics.setWillpower_fortune(newValue);
                return;
            case FELLOWSHIP_FORTUNE:
                characteristics.setFellowship_fortune(newValue);
                return;
            default:
                return;
        }
    }
}
