package com.whfrp3.ihm.listeners;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;

public class AdventureActivityHandlers implements IPlayerActivityConstants {
    public void changeStress(int change) {
        PlayerHelper.changeStress(WHFRP3Application.getPlayer(), change);
    }

    public void changeExertion(int change) {
        PlayerHelper.changeExertion(WHFRP3Application.getPlayer(), change);
    }

    public void showToast(View view, Weapon weapon) {
        Snackbar.make(view, weapon.toString(), Snackbar.LENGTH_SHORT).show();
    }
}
