package com.whfrp3.ihm.listeners;

import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;

public class TalentsHandlers {
    public void addTalent(Talent talent) {
        Player player = WHFRP3Application.getPlayer();
        PlayerTalent playerTalent = new PlayerTalent(talent, player.getId());
        player.getPlayerTalents().add(playerTalent);


        ToastNotification.info(player.getPlayerTalents().toString());
    }
}
