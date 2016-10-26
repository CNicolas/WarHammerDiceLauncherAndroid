package com.whfrp3.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment implements OnPlayerUpdateListener {
    private View mRootView;
    private DiscreteSeekBar mPlayerStance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        mRootView = inflater.inflate(R.layout.fragment_adventure, container, false);

        mPlayerStance = (DiscreteSeekBar) mRootView.findViewById(R.id.player_stance);
        onPlayerUpdate();

        mPlayerStance.setOnProgressChangeListener(new StanceChangeListener(getActivity()));

        PlayerContext.registerPlayerUpdateListener(this);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return mRootView;
    }

    @Override
    public void onPlayerUpdate() {
        mPlayerStance.setMin(-1 * PlayerContext.getPlayerInstance().getMax_conservative());
        mPlayerStance.setMax(PlayerContext.getPlayerInstance().getMax_reckless());
    }


}
