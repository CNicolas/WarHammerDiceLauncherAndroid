package com.aku.warhammerdicelauncher.ihm.tools;

import android.text.Editable;
import android.text.TextWatcher;

import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.enums.PlayerInformation;

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
    }

    private void setPlayerInformation(PlayerInformation playerInformation, String newValue) {
        Player player = PlayerContext.getPlayerInstance();
        try {
            switch (playerInformation) {
                case AGE:
                    player.setAge(Integer.parseInt(newValue));
                    return;
                case DESCRIPTION:
                    player.setDescription(newValue);
                    return;
                case EXPERIENCE:
                    player.setExperience(Integer.parseInt(newValue));
                    return;
                case MAX_EXPERIENCE:
                    player.setMax_experience(Integer.parseInt(newValue));
                    return;
                case WOUNDS:
                    player.setWounds(Integer.parseInt(newValue));
                    return;
                case MAX_WOUNDS:
                    player.setMax_wounds(Integer.parseInt(newValue));
                    return;
                case CORRUPTION:
                    player.setCorruption(Integer.parseInt(newValue));
                    return;
                case MAX_CORRUPTION:
                    player.setMax_corruption(Integer.parseInt(newValue));
                    return;
                case CONSERVATIVE:
                    player.setConservative(Integer.parseInt(newValue));
                    return;
                case MAX_CONSERVATIVE:
                    player.setMax_conservative(Integer.parseInt(newValue));
                    return;
                case RECKLESS:
                    player.setReckless(Integer.parseInt(newValue));
                    return;
                case MAX_RECKLESS:
                    player.setMax_reckless(Integer.parseInt(newValue));
                    return;
                case NAME:
                    player.setName(newValue);
                    return;
                case CAREER:
                    player.setCareer(newValue);
                    return;
                case RACE:
                    player.setRace(newValue);
                    return;
                case RANK:
                    player.setRank(Integer.parseInt(newValue));
                    return;
                case SIZE:
                    player.setSize(Double.parseDouble(newValue));
                    return;
                default:
                    return;
            }
        } catch (NumberFormatException nfe) {
            return;
        }
    }
}
