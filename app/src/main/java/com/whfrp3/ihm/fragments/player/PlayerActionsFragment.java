package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentPlayerActionsBinding;
import com.whfrp3.tools.WHFRP3Application;

/**
 * Player actions Fragment
 */
public class PlayerActionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlayerActionsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_actions, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());

        return binding.getRoot();
    }
}
