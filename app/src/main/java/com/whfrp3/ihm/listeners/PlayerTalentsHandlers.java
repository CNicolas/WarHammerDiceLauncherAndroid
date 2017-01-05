package com.whfrp3.ihm.listeners;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.whfrp3.R;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;

public class PlayerTalentsHandlers {
    public void showPopupMenu(View v, final PlayerTalent playerTalent) {
        final PopupMenu talentPopupMenu = new PopupMenu(WHFRP3Application.getActivity(), v);
        talentPopupMenu.getMenuInflater().inflate(R.menu.player_talent, talentPopupMenu.getMenu());

        setupPopupMenuVisibility(talentPopupMenu, playerTalent);

        talentPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    /**
                     * Exhaust
                     */
                    case R.id.talent_menu_exhaust:
                        playerTalent.setExhausted(true);
                        break;
                    case R.id.talent_menu_ready:
                        playerTalent.setExhausted(false);
                        ToastNotification.info(WHFRP3Application.getResourceString(R.string.ready));
                        break;
                    /**
                     * Equip
                     */
                    case R.id.talent_menu_equip:
                        playerTalent.setEquipped(true);
                        ToastNotification.info(WHFRP3Application.getResourceString(R.string.equipped));
                        break;
                    case R.id.talent_menu_unequip:
                        playerTalent.setEquipped(false);
                        break;
                    /**
                     * Delete
                     */
                    case R.id.talent_menu_delete:
                        WHFRP3Application.getPlayer().removeTalent(playerTalent.getTalent());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        talentPopupMenu.show();
    }

    private void setupPopupMenuVisibility(PopupMenu talentPopupMenu, PlayerTalent playerTalent) {
        if (playerTalent.isEquipped()) {
            talentPopupMenu.getMenu().findItem(R.id.talent_menu_equip).setVisible(false);

            if (playerTalent.isExhaustible()) {
                if (playerTalent.isExhausted()) {
                    talentPopupMenu.getMenu().findItem(R.id.talent_menu_exhaust).setVisible(false);
                } else {
                    talentPopupMenu.getMenu().findItem(R.id.talent_menu_ready).setVisible(false);
                }
            } else {
                talentPopupMenu.getMenu().findItem(R.id.talent_menu_exhaust).setVisible(false);
                talentPopupMenu.getMenu().findItem(R.id.talent_menu_ready).setVisible(false);
            }
        } else {
            talentPopupMenu.getMenu().findItem(R.id.talent_menu_unequip).setVisible(false);
            talentPopupMenu.getMenu().findItem(R.id.talent_menu_exhaust).setVisible(false);
            talentPopupMenu.getMenu().findItem(R.id.talent_menu_ready).setVisible(false);
        }
    }
}
