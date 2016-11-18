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
import com.whfrp3.ihm.listeners.AdventureHandlers;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.PlayerHelper;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment implements OnPlayerUpdateListener {
    private DiscreteSeekBar mPlayerStance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        FragmentAdventureBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adventure, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setHandlers(new AdventureHandlers());

        mPlayerStance = (DiscreteSeekBar) binding.getRoot().findViewById(R.id.player_stance);
        TextView currentStanceTextView = (TextView) binding.getRoot().findViewById(R.id.currentStance);
        mPlayerStance.setOnProgressChangeListener(new StanceChangeListener(getActivity(), currentStanceTextView));

        onPlayerUpdate();
        PlayerHelper.registerPlayerUpdateListener(this);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        onPlayerUpdate();
    }

    @Override
    public void onPlayerUpdate() {
        mPlayerStance.setMin(-1 * WHFRP3Application.getPlayer().getMax_conservative());
        mPlayerStance.setMax(WHFRP3Application.getPlayer().getMax_reckless());
    }
}
