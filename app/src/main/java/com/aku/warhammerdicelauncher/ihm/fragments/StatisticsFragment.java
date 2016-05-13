package com.aku.warhammerdicelauncher.ihm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.services.DicesRoller;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by cnicolas on 12/05/2016.
 */
public class StatisticsFragment extends Fragment {

    public static final String HAND = "hand";
    public static final String TIMES = "times";

    private HandDto dto;
    private int times;
    private DecimalFormat df;

    public StatisticsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        dto = (HandDto) getArguments().getSerializable(HAND);
        times = getArguments().getInt(TIMES);

        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Button button = (Button) getActivity().findViewById(R.id.btn_relaunch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reRoll();
            }
        });

        reRoll();
    }

    private void reRoll() {
        Map<DiceFace, Integer> map = DicesRoller.rollDices(dto, times);

        double total = 0;
        for (Integer i : map.values()) {
            total += i;
        }

        for (DiceFace face : Constants.statisticsResultsTextViews.keySet()) {
            TextView textView = (TextView) getActivity().findViewById(Constants.statisticsResultsTextViews.get(face));
            if (map.containsKey(face)) {
                String found = String.format("%1$-5s", map.get(face));
                double tmpDouble = ((double) map.get(face) * 100) / total;
                String percent = df.format(tmpDouble) + "%";
                String str = found + ": " + percent;
                textView.setText(str);
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }
}