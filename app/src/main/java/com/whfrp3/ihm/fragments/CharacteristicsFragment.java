package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentCharacteristicsBinding;
import com.whfrp3.tools.BindingContext;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.constants.IPlayerConstants;

/**
 * The CharacteristicsFragment.
 */
public class CharacteristicsFragment extends Fragment {
    private BindingContext mBindingContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBindingContext = (BindingContext) bundle.getSerializable(IPlayerConstants.BINDING_CONTEXT_KEY);
        } else {
            mBindingContext = new BindingContext(false);
        }

        FragmentCharacteristicsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characteristics, container, false);
        binding.setPlayer(PlayerContext.getPlayerInstance());
        binding.setBindingContext(mBindingContext);
        binding.characteristicsEdittexts.setCarac(PlayerContext.getPlayerInstance().getCharacteristics());
        binding.characteristicsEdittexts.setBindingContext(mBindingContext);

        setHasOptionsMenu(true);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("CharacteristicsFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }
}
