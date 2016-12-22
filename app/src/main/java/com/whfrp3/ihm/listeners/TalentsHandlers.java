package com.whfrp3.ihm.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.TalentsActivity;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;

public class TalentsHandlers {
    public void startTalentActivity(TalentType talentType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IMainConstants.TALENT_TYPE_BUNDLE_TAG, talentType);

        Intent talentsIntent = new Intent(WHFRP3Application.getActivity(), TalentsActivity.class);
        talentsIntent.putExtras(bundle);

        WHFRP3Application.getActivity().startActivityForResult(talentsIntent, IMainConstants.TALENTS_REQUEST);
    }

    public void addTalent(Talent talent) {
        Player player = WHFRP3Application.getPlayer();
        player.addTalent(talent);

        ToastNotification.info(player.getPlayerTalents().toString());
    }

    public void showPopupMenu(View v) {
        openTalentPopupMenu(v);
    }

    private void openTalentPopupMenu(View view) {
        PopupMenu talentPopupMenu = new PopupMenu(WHFRP3Application.getActivity(), view);
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
