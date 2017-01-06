package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentPlayerSkillsBinding;
import com.whfrp3.tools.WHFRP3Application;

/**
 * The SkillFragment.
 */
public class PlayerSkillsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlayerSkillsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_skills, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());

        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.action_add).setVisible(false);
    }
}
