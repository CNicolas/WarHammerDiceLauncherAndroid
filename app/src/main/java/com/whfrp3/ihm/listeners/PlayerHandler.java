package com.whfrp3.ihm.listeners;

import android.util.Log;
import android.view.View;

import com.whfrp3.model.player.Player;

public class PlayerHandler {
    public void updatePlayer(View v, Player player) {
//        PlayerContext.setPlayer(player);
//        PlayerContext.updatePlayer(v.getContext());
        Log.d("PlayerHandler", player.toString());
    }
}
