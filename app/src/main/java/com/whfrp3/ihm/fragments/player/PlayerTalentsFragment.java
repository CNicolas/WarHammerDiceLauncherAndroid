package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentPlayerTalentsBinding;
import com.whfrp3.ihm.fragments.dialog.PlayerTalentSearchDialogFragment;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

/**
 * Player talents fragment.
 */
public class PlayerTalentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentPlayerTalentsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_talents, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.action_add).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            openTalentSearchDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openTalentSearchDialog() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IPlayerActivityConstants.CAN_ADD_TO_PLAYER_BUNDLE_TAG, true);

        PlayerTalentSearchDialogFragment dialog = new PlayerTalentSearchDialogFragment();
        dialog.setArguments(bundle);
        dialog.show(getActivity().getSupportFragmentManager(), "PlayerTalentSearchDialogFragment");
    }
}
