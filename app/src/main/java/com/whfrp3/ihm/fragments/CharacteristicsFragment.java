package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentCharacteristicsBinding;
import com.whfrp3.ihm.adapters.EnumSpinnerAdapter;
import com.whfrp3.model.enums.Race;
import com.whfrp3.tools.WHFRP3Application;

/**
 * The CharacteristicsFragment.
 */
public class CharacteristicsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        FragmentCharacteristicsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_characteristics, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setCarac(WHFRP3Application.getPlayer().getCharacteristics());

        setupRaceSpinner(inflater, binding.getRoot());

        setHasOptionsMenu(true);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("CharacteristicsFragment", String.format("%d = %d", startTime, difference));
        return binding.getRoot();
    }

    /**
     * Configure Race Spinner
     *
     * @param inflater
     * @param rootView
     */
    private void setupRaceSpinner(LayoutInflater inflater, View rootView) {
        Spinner raceSpinner = (Spinner) rootView.findViewById(R.id.player_race_spinner);

        raceSpinner.setAdapter(new EnumSpinnerAdapter(inflater, Race.values()));
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Race race = (Race) adapterView.getItemAtPosition(pos);

                WHFRP3Application.getPlayer().setRace(race);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                WHFRP3Application.getPlayer().setRace(null);
            }
        });
    }
}
