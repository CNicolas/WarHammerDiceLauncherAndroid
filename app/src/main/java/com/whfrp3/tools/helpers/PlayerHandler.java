package com.whfrp3.tools.helpers;

import android.view.View;

import com.whfrp3.model.player.PlayerV2;
import com.whfrp3.tools.PlayerContext;

/**
 * Created by cnicolas on 04/11/2016.
 */

public class PlayerHandler {
    public void updatePlayer(View v, PlayerV2 playerV2) {
        PlayerContext.setPlayer(playerV2.getPlayer());
        PlayerContext.updatePlayer(v.getContext());
    }
}
