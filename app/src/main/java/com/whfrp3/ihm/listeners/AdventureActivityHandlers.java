package com.whfrp3.ihm.listeners;

import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.PlayerHelper;

public class AdventureActivityHandlers {
    public void changeStress(int change) {
        PlayerHelper.changeStress(WHFRP3Application.getPlayer(), change);
    }

    public void changeExertion(int change) {
        PlayerHelper.changeExertion(WHFRP3Application.getPlayer(), change);
    }
}
