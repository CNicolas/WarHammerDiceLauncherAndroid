package com.whfrp3.ihm.listeners;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.whfrp3.R;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;

public class TalentsHandlers {
    public void addTalent(Talent talent) {
        Player player = WHFRP3Application.getPlayer();
        player.addTalent(talent);

        ToastNotification.info(player.getPlayerTalents().toString());
    }

    public void showPopupMenu(View v) {
        openTalentPopupMenu(v);
    }

    private void openTalentPopupMenu(View view) {
        PopupMenu talentPopupMenu = new PopupMenu(WHFRP3Application.getAppContext(), view);
        talentPopupMenu.getMenuInflater().inflate(R.menu.player_talent, talentPopupMenu.getMenu());
        talentPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.talent_menu_exhaust:
                        ToastNotification.warning(WHFRP3Application.getResourceString(R.string.exhausted));
                        break;
                    case R.id.talent_menu_equip:
                        ToastNotification.info(WHFRP3Application.getResourceString(R.string.equipped));
                        break;
                    case R.id.talent_menu_delete:
                        ToastNotification.error(WHFRP3Application.getResourceString(R.string.deleted));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        talentPopupMenu.show();
    }
}
