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
import com.whfrp3.ihm.listeners.AdventureActivityHandlers;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.WHFRP3Application;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        long startTime = System.currentTimeMillis();

        FragmentAdventureBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adventure, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setHandlers(new AdventureActivityHandlers());

        setupStance(binding.getRoot());

        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }

    private void setupStance(View rootView) {
        TextView currentStanceTextView = (TextView) rootView.findViewById(R.id.currentStance);
        BindableDiscreteSeekBar playerStance = (BindableDiscreteSeekBar) rootView.findViewById(R.id.player_stance);

        playerStance.setOnProgressChangeListener(new StanceChangeListener(getActivity(), currentStanceTextView));
        playerStance.setMin(-1 * WHFRP3Application.getPlayer().getMax_conservative());
        playerStance.setMax(WHFRP3Application.getPlayer().getMax_reckless());
    }

}
