package com.aku.warhammerdicelauncher.ihm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

/**
 * Created by cnicolas on 12/05/2016.
 */
public class StatisticsFragment extends Fragment {

    public static final String HAND = "hand";
    public static final String TIMES = "times";

    public StatisticsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        HandDto dto = (HandDto) getArguments().getSerializable(HAND);
        int times = getArguments().getInt(TIMES);

        // TRAITEMENT ET STATS
        //for (int i = 0; i < times; i++) {
        //    Map<DiceFace, Integer> res = DicesRoller.rollDices(dto);
        //}
        //

        return rootView;
    }

    public boolean isFragmentUIActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }
}