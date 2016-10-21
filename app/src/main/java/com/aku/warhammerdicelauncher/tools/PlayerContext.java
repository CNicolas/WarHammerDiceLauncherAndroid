package com.aku.warhammerdicelauncher.tools;

import com.aku.warhammerdicelauncher.model.player.Player;

/**
 * Created by cnicolas on 13/10/2016.
 */

public class PlayerContext {
    private static Player mPlayer;
    private static boolean isInEdition;

    public static Player getPlayerInstance() {
        if (mPlayer == null) {
            mPlayer = createEmptyPlayer();
        }
        return mPlayer;
    }

    public static Player createEmptyPlayer() {
        mPlayer = new Player();
        return mPlayer;
    }

    public static void setPlayer(Player player) {
        mPlayer = player;
    }

    public static boolean isInEdition() {
        return isInEdition;
    }

    public static void setIsInEdition(boolean isInEdition) {
        PlayerContext.isInEdition = isInEdition;
    }

    public static Player createTestPlayer() {
        mPlayer = createEmptyPlayer();

        mPlayer.setCareer("Ratier");
        mPlayer.setExperience(0);
        mPlayer.setMax_experience(16);
        mPlayer.getCharacteristics().setFellowship(3);
        mPlayer.getCharacteristics().setFellowship_fortune(1);
        mPlayer.getCharacteristics().setStrength(5);
        mPlayer.getCharacteristics().setStrength_fortune(1);
        mPlayer.getCharacteristics().setToughness(4);
        mPlayer.setDescription("Trop tanky !");

        return mPlayer;
    }
}
