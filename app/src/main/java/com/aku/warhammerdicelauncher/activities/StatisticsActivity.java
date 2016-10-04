package com.aku.warhammerdicelauncher.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.utils.constants.Constants;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;
import com.aku.warhammerdicelauncher.utils.helpers.DicesRollerHelper;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cnicolas on 04/10/2016.
 */

public class StatisticsActivity extends Activity {

    public static final String ALL_THROWS = "allThrows";
    public static final String SUCCESSFUL_THROWS = "successfulThrows";
    public static final String AVERAGE_SUCCESS = "averageBenefit";
    public static final String AVERAGE_BENEFIT = "averageBenefit";
    public static final String AVERAGE_SIGMAR = "averageSigmar";
    public static final String AVERAGE_CHAOS = "averageChaos";


    private HandDto dto;
    private int times;
    private DecimalFormat df;
    private int successfulRolls = 0;
    private Map<DiceFace, Integer> allThrows;

    private double averageSuccessNumber;
    private double averageBenefitNumber;
    private double averageSigmarNumber;
    private double averageChaosNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_layout);

        dto = (HandDto) getIntent().getExtras().getSerializable(Constants.HAND_TAG);
        times = getIntent().getExtras().getInt(Constants.TIMES_TAG);

        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        if (savedInstanceState != null) {
            dto = (HandDto) savedInstanceState.getSerializable(Constants.HAND_TAG);
            times = savedInstanceState.getInt(Constants.TIMES_TAG);

            allThrows = (HashMap) savedInstanceState.getSerializable(ALL_THROWS);
            successfulRolls = savedInstanceState.getInt(SUCCESSFUL_THROWS);
            averageSuccessNumber = savedInstanceState.getDouble(AVERAGE_BENEFIT);
            averageBenefitNumber = savedInstanceState.getDouble(AVERAGE_BENEFIT);
            averageSigmarNumber = savedInstanceState.getDouble(AVERAGE_SIGMAR);
            averageChaosNumber = savedInstanceState.getDouble(AVERAGE_CHAOS);
            updateUI();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Button button = (Button) findViewById(R.id.btn_relaunch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll();
            }
        });

        if (allThrows == null) {
            roll();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(Constants.HAND_TAG, dto);
        savedInstanceState.putInt(Constants.TIMES_TAG, times);

        savedInstanceState.putSerializable(ALL_THROWS, (HashMap) allThrows);
        savedInstanceState.putInt(SUCCESSFUL_THROWS, successfulRolls);
        savedInstanceState.putDouble(AVERAGE_SUCCESS, averageSuccessNumber);
        savedInstanceState.putDouble(AVERAGE_BENEFIT, averageBenefitNumber);
        savedInstanceState.putDouble(AVERAGE_SIGMAR, averageSigmarNumber);
        savedInstanceState.putDouble(AVERAGE_CHAOS, averageChaosNumber);

        super.onSaveInstanceState(savedInstanceState);
    }

    private void roll() {
        successfulRolls = 0;
        averageSuccessNumber = 0;
        averageBenefitNumber = 0;
        averageSigmarNumber = 0;
        averageChaosNumber = 0;

        allThrows = new HashMap<>();
        for (int i = 0; i < times; i++) {
            Map<DiceFace, Integer> map = DicesRollerHelper.rollDices(dto);

            for (DiceFace face : map.keySet()) {
                int old = allThrows.get(face) != null ? allThrows.get(face) : 0;
                allThrows.put(face, old + map.get(face));
            }

            successfulRolls += DicesRollerHelper.isSuccessful(map) ? 1 : 0;
        }

        averageSuccessNumber = allThrows.containsKey(DiceFace.SUCCESS) ? allThrows.get(DiceFace.SUCCESS) / ((double) times) : 0;
        averageBenefitNumber = allThrows.containsKey(DiceFace.BENEFIT) ? allThrows.get(DiceFace.BENEFIT) / ((double) times) : 0;
        averageSigmarNumber = allThrows.containsKey(DiceFace.SIGMAR) ? allThrows.get(DiceFace.SIGMAR) / ((double) times) : 0;
        averageChaosNumber = allThrows.containsKey(DiceFace.CHAOS) ? allThrows.get(DiceFace.CHAOS) / ((double) times) : 0;

        updateUI();
    }

    private void updateUI() {
        TextView throwsNumberView = (TextView) findViewById(R.id.throwsNumberView);
        throwsNumberView.setText(String.format(getResources().getString(R.string.throwsNumberFormat), String.valueOf(times)));

        double successPercentage = (successfulRolls * 100) / ((double) times);
        TextView successNumberTextView = (TextView) findViewById(R.id.successRollsView);
        successNumberTextView.setText(String.format(getResources().getString(R.string.successfulRollsNumberFormat), successfulRolls, df.format(successPercentage)));

        TextView averageSuccessTextView = (TextView) findViewById(R.id.averageSuccessView);
        averageSuccessTextView.setText(String.format(getResources().getString(R.string.averageSuccessFormat), df.format(averageSuccessNumber)));

        TextView averageBenefitTextView = (TextView) findViewById(R.id.averageBenefitView);
        averageBenefitTextView.setText(String.format(getResources().getString(R.string.averageBenefitFormat), df.format(averageBenefitNumber)));

        TextView averageSigmarTextView = (TextView) findViewById(R.id.averageSigmarView);
        averageSigmarTextView.setText(String.format(getResources().getString(R.string.averageSigmarFormat), df.format(averageSigmarNumber)));

        TextView averageChaosTextView = (TextView) findViewById(R.id.averageChaosView);
        averageChaosTextView.setText(String.format(getResources().getString(R.string.averageChaosFormat), df.format(averageChaosNumber)));
    }
}
