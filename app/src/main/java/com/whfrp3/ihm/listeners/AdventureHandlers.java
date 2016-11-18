package com.whfrp3.ihm.listeners;

import com.whfrp3.tools.PlayerHelper;
import com.whfrp3.tools.WHFRP3Application;

public class AdventureHandlers {
    public void changeStress(int change) {
        PlayerHelper.changeStress(WHFRP3Application.getPlayer(), change);
    }

    public void changeExertion(int change) {
        PlayerHelper.changeExertion(WHFRP3Application.getPlayer(), change);
    }
}
