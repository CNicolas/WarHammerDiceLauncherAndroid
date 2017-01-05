package com.whfrp3.ihm.listeners;

import android.content.Intent;
import android.os.Bundle;

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

        ToastNotification.info(talent.getName() + WHFRP3Application.getResourceString(R.string.added));
    }
}
