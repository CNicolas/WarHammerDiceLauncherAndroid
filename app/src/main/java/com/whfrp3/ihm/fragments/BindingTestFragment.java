package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentBindingTestBinding;
import com.whfrp3.model.player.PlayerV2;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.helpers.PlayerHandler;

/**
 * The AdventureFragment.
 */
public class BindingTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        FragmentBindingTestBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_binding_test, container, false);
        binding.setPlayer(new PlayerV2(PlayerContext.getPlayerInstance()));
        binding.setHandler(new PlayerHandler());

        long difference = System.currentTimeMillis() - startTime;
        Log.d("BindingTestFragment", String.format("%d = %d", startTime, difference));

        return binding.getRoot();
    }

}
