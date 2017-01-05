package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.whfrp3.R;
import com.whfrp3.databinding.DialogTalentSearchBinding;
import com.whfrp3.ihm.activities.TalentsActivity;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.TalentHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the dialog used to display the search form
 */
public class PlayerTalentSearchDialogFragment extends TalentSearchDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        initTalentSearchFields();

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogTalentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_talent_search, null, false);
        binding.setTalentSearch(mTalentSearch);

        builder.setView(binding.getRoot());
        builder.setTitle(R.string.search);
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Talent> talentsFound = TalentHelper.getInstance().searchForPlayer(mTalentSearch, WHFRP3Application.getPlayer());
                if (talentsFound.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IMainConstants.TALENTS_LIST_BUNDLE_TAG, (ArrayList<Talent>) talentsFound);
                    bundle.putBoolean(IPlayerActivityConstants.CAN_ADD_TO_PLAYER_BUNDLE_TAG, true);

                    Intent talentsIntent = new Intent(getActivity(), TalentsActivity.class);
                    talentsIntent.putExtras(bundle);

                    getActivity().startActivityForResult(talentsIntent, IMainConstants.TALENTS_REQUEST);
                } else {
                    ToastNotification.error("No Talent Found !");
                }
            }
        });

        setupTalentTypesSpinner(inflater, binding.getRoot());
        setupCooldownSpinner(inflater, binding.getRoot());

        return builder.create();
    }
}
