package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentAdventureBinding;
import com.whfrp3.ihm.components.BindableDiscreteSeekBar;
import com.whfrp3.ihm.listeners.AdventureHandlers;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.WHFRP3Application;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment {
    private BindableDiscreteSeekBar mPlayerStance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        FragmentAdventureBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adventure, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setHandlers(new AdventureHandlers());

        mPlayerStance = (BindableDiscreteSeekBar) binding.getRoot().findViewById(R.id.player_stance);
        TextView currentStanceTextView = (TextView) binding.getRoot().findViewById(R.id.currentStance);
        mPlayerStance.setOnProgressChangeListener(new StanceChangeListener(getActivity(), currentStanceTextView));
        mPlayerStance.setMin(-1 * WHFRP3Application.getPlayer().getMax_conservative());
        mPlayerStance.setMax(WHFRP3Application.getPlayer().getMax_reckless());

        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }

}
