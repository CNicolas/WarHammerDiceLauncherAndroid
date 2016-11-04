package com.whfrp3.ihm.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import com.whfrp3.model.player.Player;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.enums.PlayerInformation;

/**
 * Created by cnicolas on 18/10/2016.
 */

public class PlayerEditTextWatcher implements TextWatcher {
    private final PlayerInformation playerInformation;

    public PlayerEditTextWatcher(PlayerInformation playerInformation) {
        this.playerInformation = playerInformation;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setPlayerInformation(playerInformation, s.toString());
//        PlayerContext.updatePlayer();
    }

    private void setPlayerInformation(PlayerInformation playerInformation, String newValue) {
        Player player = PlayerContext.getPlayerInstance();
        try {
            switch (playerInformation) {
                case AGE:
                    player.setAge(Integer.parseInt(newValue));
                    break;
                case DESCRIPTION:
                    player.setDescription(newValue);
                    break;
                case EXPERIENCE:
                    player.setExperience(Integer.parseInt(newValue));
                    break;
                case MAX_EXPERIENCE:
                    player.setMax_experience(Integer.parseInt(newValue));
                    break;
                case WOUNDS:
                    int newWounds = Integer.parseInt(newValue);
                    if (newWounds >= 0 || newWounds <= player.getMax_wounds() + player.getCharacteristics().getToughness()) {
                        player.setWounds(newWounds);
                    }
                    break;
                case MAX_WOUNDS:
                    player.setMax_wounds(Integer.parseInt(newValue));
                    break;
                case CORRUPTION:
                    int newCorruption = Integer.parseInt(newValue);
                    if (newCorruption >= 0 || newCorruption <= player.getMax_corruption()) {
                        player.setCorruption(newCorruption);
                    }
                    break;
                case MAX_CORRUPTION:
                    player.setMax_corruption(Integer.parseInt(newValue));
                    break;
                case CONSERVATIVE:
                    player.setConservative(Integer.parseInt(newValue));
                    break;
                case MAX_CONSERVATIVE:
                    player.setMax_conservative(Integer.parseInt(newValue));
                    break;
                case RECKLESS:
                    player.setReckless(Integer.parseInt(newValue));
                    break;
                case MAX_RECKLESS:
                    player.setMax_reckless(Integer.parseInt(newValue));
                    break;
                case NAME:
                    player.setName(newValue);
                    break;
                case CAREER:
                    player.setCareer(newValue);
                    break;
                case RACE:
                    player.setRace(newValue);
                    break;
                case RANK:
                    player.setRank(Integer.parseInt(newValue));
                    break;
                case SIZE:
                    player.setSize(Double.parseDouble(newValue));
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException nfe) {

        }
    }
}
