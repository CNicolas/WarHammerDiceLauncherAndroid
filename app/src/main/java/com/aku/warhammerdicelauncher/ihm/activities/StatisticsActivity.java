package com.aku.warhammerdicelauncher.ihm.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.dices.DiceFaces;
import com.aku.warhammerdicelauncher.model.player.Hand;
import com.aku.warhammerdicelauncher.tools.constants.IHandConstants;
import com.aku.warhammerdicelauncher.tools.helpers.DicesRollerHelper;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsActivity extends Activity {

    public static final String ALL_THROWS = "allThrows";
    public static final String SUCCESSFUL_THROWS = "successfulThrows";
    public static final String AVERAGE_SUCCESS = "averageBenefit";
    public static final String AVERAGE_BENEFIT = "averageBenefit";
    public static final String AVERAGE_SIGMAR = "averageSigmar";
    public static final String AVERAGE_CHAOS = "averageChaos";

    private Hand dto;
    private int times;
    private DecimalFormat df;
    private int successfulRolls = 0;
    private Map<DiceFaces, Integer> allThrows;

    private double averageSuccessNumber;
    private double averageBenefitNumber;
    private double averageSigmarNumber;
    private double averageChaosNumber;

    private FloatingActionButton mRelaunchFab;
    private ScrollView mScrollView;
    private ProgressBar mProgressSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        dto = (Hand) getIntent().getExtras().getSerializable(IHandConstants.HAND_TAG);
        times = getIntent().getExtras().getInt(IHandConstants.TIMES_TAG);

        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        if (savedInstanceState != null) {
            dto = (Hand) savedInstanceState.getSerializable(IHandConstants.HAND_TAG);
            times = savedInstanceState.getInt(IHandConstants.TIMES_TAG);

            allThrows = (Map) savedInstanceState.getSerializable(ALL_THROWS);
            successfulRolls = savedInstanceState.getInt(SUCCESSFUL_THROWS);
            averageSuccessNumber = savedInstanceState.getDouble(AVERAGE_BENEFIT);
            averageBenefitNumber = savedInstanceState.getDouble(AVERAGE_BENEFIT);
            averageSigmarNumber = savedInstanceState.getDouble(AVERAGE_SIGMAR);
            averageChaosNumber = savedInstanceState.getDouble(AVERAGE_CHAOS);
            updateUI();
        }

        mRelaunchFab = (FloatingActionButton) findViewById(R.id.relaunch_fab);
        mScrollView = (ScrollView) findViewById(R.id.statistics_scroll_view);
        mProgressSpinner = (ProgressBar) findViewById(R.id.progress_spinner);
    }

    @Override
    public void onResume() {
        if (allThrows == null) {
            callRoll();
        }

        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(IHandConstants.HAND_TAG, dto);
        savedInstanceState.putInt(IHandConstants.TIMES_TAG, times);

        savedInstanceState.putSerializable(ALL_THROWS, (HashMap) allThrows);
        savedInstanceState.putInt(SUCCESSFUL_THROWS, successfulRolls);
        savedInstanceState.putDouble(AVERAGE_SUCCESS, averageSuccessNumber);
        savedInstanceState.putDouble(AVERAGE_BENEFIT, averageBenefitNumber);
        savedInstanceState.putDouble(AVERAGE_SIGMAR, averageSigmarNumber);
        savedInstanceState.putDouble(AVERAGE_CHAOS, averageChaosNumber);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void relaunch(View v) {
        callRoll();
    }

    private void callRoll() {
        showProgress(true);

        Thread t = new Thread(new Runnable() {
            public void run() {
                roll();
            }
        });
        t.start();
    }

    private void roll() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                successfulRolls = 0;
                averageSuccessNumber = 0;
                averageBenefitNumber = 0;
                averageSigmarNumber = 0;
                averageChaosNumber = 0;

                allThrows = new HashMap<>();
                for (int i = 0; i < times; i++) {
                    Map<DiceFaces, Integer> map = DicesRollerHelper.rollDices(dto);

                    for (DiceFaces face : map.keySet()) {
                        int old = allThrows.get(face) != null ? allThrows.get(face) : 0;
                        allThrows.put(face, old + map.get(face));
                    }

                    successfulRolls += DicesRollerHelper.isSuccessful(map) ? 1 : 0;
                }

                averageSuccessNumber = allThrows.containsKey(DiceFaces.SUCCESS) ? allThrows.get(DiceFaces.SUCCESS) / ((double) times) : 0;
                averageBenefitNumber = allThrows.containsKey(DiceFaces.BENEFIT) ? allThrows.get(DiceFaces.BENEFIT) / ((double) times) : 0;
                averageSigmarNumber = allThrows.containsKey(DiceFaces.SIGMAR) ? allThrows.get(DiceFaces.SIGMAR) / ((double) times) : 0;
                averageChaosNumber = allThrows.containsKey(DiceFaces.CHAOS) ? allThrows.get(DiceFaces.CHAOS) / ((double) times) : 0;
            }
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            runOnUiThread(new Thread(new Runnable() {
                @Override
                public void run() {
                    showProgress(false);
                }
            }));
        }

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

    private void showProgress(boolean isInProgress) {
        if (isInProgress) {
            mRelaunchFab.setVisibility(View.GONE);
            mScrollView.setVisibility(View.GONE);
            mProgressSpinner.setVisibility(View.VISIBLE);
        } else {
            mRelaunchFab.setVisibility(View.VISIBLE);
            mScrollView.setVisibility(View.VISIBLE);
            mProgressSpinner.setVisibility(View.GONE);
        }
        updateUI();
    }
}
