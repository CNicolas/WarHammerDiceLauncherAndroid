package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentPlayerTalentsBinding;
import com.whfrp3.ihm.fragments.dialog.TalentSearchDialogFragment;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

/**
 * Player talents fragment.
 */
public class PlayerTalentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentPlayerTalentsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_talents, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());

        FloatingActionButton addTalentButton = (FloatingActionButton) binding.getRoot().findViewById(R.id.add_talent);
        addTalentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTalentSearchDialog();
            }
        });

        ListView talentsList = (ListView) binding.getRoot().findViewById(R.id.talents_list);
        talentsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastNotification.info("You clicked for a very long time !");
                return false;
            }
        });

        return binding.getRoot();
    }

    private void openTalentSearchDialog() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IPlayerActivityConstants.CAN_ADD_TO_PLAYER_BUNDLE_TAG, true);

        TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getActivity().getSupportFragmentManager(), "TalentSearchDialogFragment");
    }
}
