package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentCharacteristicsV2Binding;
import com.whfrp3.ihm.listeners.PlayerHandler;
import com.whfrp3.model.player.Player;

/**
 * The AdventureFragment.
 */
public class CharacteristicsFragmentV2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        FragmentCharacteristicsV2Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characteristics_v2, container, false);
        binding.setPlayer(new Player());
        binding.setHandler(new PlayerHandler());

        long difference = System.currentTimeMillis() - startTime;
        Log.d("CharacFragV2", String.format("%d = %d", startTime, difference));

        return binding.getRoot();
    }

}
