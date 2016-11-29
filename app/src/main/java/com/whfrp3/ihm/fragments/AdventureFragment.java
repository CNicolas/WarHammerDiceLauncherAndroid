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
import com.whfrp3.ihm.fragments.dialog.ChangeMoneyDialogFragment;
import com.whfrp3.ihm.listeners.AdventureActivityHandlers;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.PlayerHelper;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment {
    /**
     * Add money operation code.
     */
    public static final int ADD_MONEY = 1;

    /**
     * Remove money operation code.
     */
    public static final int REMOVE_MONEY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        long startTime = System.currentTimeMillis();

        FragmentAdventureBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adventure, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setHandlers(new AdventureActivityHandlers());
        binding.setViewModel(this);

        setupStance(binding.getRoot());

        binding.getRoot().findViewById(R.id.encumbrance_bar).setEnabled(false);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }

    public void changeMoney(int operationCode) {
        ChangeMoneyDialogFragment dialog = new ChangeMoneyDialogFragment();
        dialog.setOperationCode(operationCode);

        dialog.show(getActivity().getSupportFragmentManager(), "ChangeMoneyDialogFragment");
    }

    private void setupStance(View rootView) {
        TextView currentStanceTextView = (TextView) rootView.findViewById(R.id.currentStance);
        BindableDiscreteSeekBar playerStance = (BindableDiscreteSeekBar) rootView.findViewById(R.id.player_stance);

        playerStance.setOnProgressChangeListener(new StanceChangeListener(currentStanceTextView));
        playerStance.setMin(-1 * WHFRP3Application.getPlayer().getMax_conservative());
        playerStance.setMax(WHFRP3Application.getPlayer().getMax_reckless());

        playerStance.setProgress(0);
        PlayerHelper.savePlayer(WHFRP3Application.getPlayer());
    }
}
