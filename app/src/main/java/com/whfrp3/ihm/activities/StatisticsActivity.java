package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.model.dices.Hand;
import com.whfrp3.tools.constants.IHandConstants;
import com.whfrp3.tools.helpers.DicesRollerHelper;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {

    private Hand mHand;
    private int mTimes;
    private DecimalFormat df;
    private int successfulRolls = 0;
    private Map<DiceFaces, Integer> allThrows;

    private double averageSuccessNumber;
    private double averageBoonNumber;
    private double averageSigmarNumber;
    private double averageChaosNumber;
    private double averageFailureNumber;
    private double averageBaneNumber;

    private FloatingActionButton mRelaunchFab;
    private ScrollView mScrollView;
    private ProgressBar mProgressSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mHand = (Hand) getIntent().getExtras().getSerializable(IHandConstants.HAND_BUNDLE_TAG);
        mTimes = getIntent().getExtras().getInt(IHandConstants.TIMES_BUNDLE_TAG);

        df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        mRelaunchFab = (FloatingActionButton) findViewById(R.id.relaunch_fab);
        mScrollView = (ScrollView) findViewById(R.id.statistics_scroll_view);
        mProgressSpinner = (ProgressBar) findViewById(R.id.progress_spinner);

        callRollThread();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }

    /**
     * Event click for the launch button. Simply launch the hand again.
     *
     * @param v Calling view.
     */
    public void relaunch(View v) {
        callRollThread();
    }

    //region Thread roll

    /**
     * Start the thread which call the rollThread
     */
    private void callRollThread() {
        showProgress(true);

        Thread t = new Thread(new Runnable() {
            public void run() {
                rollThenUpdateUI();
            }
        });
        t.start();
    }

    /**
     * The thread which launches the dices.
     */
    private void rollThenUpdateUI() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                launchDicesAndGetStatistics();
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
    //endregion

    /**
     * Launch the hand "mTimes" and show the statistics.
     */
    private void launchDicesAndGetStatistics() {
        successfulRolls = 0;
        averageSuccessNumber = 0;
        averageBoonNumber = 0;
        averageSigmarNumber = 0;
        averageChaosNumber = 0;
        averageFailureNumber = 0;
        averageBaneNumber = 0;

        allThrows = new HashMap<>();
        for (int i = 0; i < mTimes; i++) {
            Map<DiceFaces, Integer> map = DicesRollerHelper.rollDices(mHand);

            for (DiceFaces face : map.keySet()) {
                int old = allThrows.get(face) != null ? allThrows.get(face) : 0;
                allThrows.put(face, old + map.get(face));
            }

            successfulRolls += DicesRollerHelper.isSuccessful(map) ? 1 : 0;
        }

        averageSuccessNumber = allThrows.containsKey(DiceFaces.SUCCESS) ? allThrows.get(DiceFaces.SUCCESS) / ((double) mTimes) : 0;
        averageBoonNumber = allThrows.containsKey(DiceFaces.BOON) ? allThrows.get(DiceFaces.BOON) / ((double) mTimes) : 0;
        averageSigmarNumber = allThrows.containsKey(DiceFaces.SIGMAR) ? allThrows.get(DiceFaces.SIGMAR) / ((double) mTimes) : 0;
        averageChaosNumber = allThrows.containsKey(DiceFaces.CHAOS) ? allThrows.get(DiceFaces.CHAOS) / ((double) mTimes) : 0;
        averageFailureNumber = allThrows.containsKey(DiceFaces.FAILURE) ? allThrows.get(DiceFaces.FAILURE) / ((double) mTimes) : 0;
        averageBaneNumber = allThrows.containsKey(DiceFaces.BANE) ? allThrows.get(DiceFaces.BANE) / ((double) mTimes) : 0;
    }

    //region UI

    /**
     * Set the values in TextViews.
     */
    private void updateUIStatistics() {
        String throwsNumberText = String.format(getResources().getString(R.string.throwsNumberFormat), String.valueOf(mTimes));
        TextView throwsNumberView = (TextView) findViewById(R.id.throwsNumberView);
        throwsNumberView.setText(throwsNumberText);

        double successPercentage = (successfulRolls * 100) / ((double) mTimes);
        String successNumberText = String.format(getResources().getString(R.string.successful_rolls_number_format), successfulRolls, df.format(successPercentage));
        TextView successNumberTextView = (TextView) findViewById(R.id.success_rolls);
        successNumberTextView.setText(successNumberText);

        String averageSuccessText = String.format(getResources().getString(R.string.average_success_format), df.format(averageSuccessNumber));
        TextView averageSuccessTextView = (TextView) findViewById(R.id.average_success);
        averageSuccessTextView.setText(averageSuccessText);

        String averageBoonText = String.format(getResources().getString(R.string.average_boon_format), df.format(averageBoonNumber));
        TextView averageBoonTextView = (TextView) findViewById(R.id.average_boon);
        averageBoonTextView.setText(averageBoonText);

        String averageSigmarText = String.format(getResources().getString(R.string.average_sigmar_format), df.format(averageSigmarNumber));
        TextView averageSigmarTextView = (TextView) findViewById(R.id.average_sigmar);
        averageSigmarTextView.setText(averageSigmarText);

        String averageChaosText = String.format(getResources().getString(R.string.average_chaos_format), df.format(averageChaosNumber));
        TextView averageChaosTextView = (TextView) findViewById(R.id.average_chaos);
        averageChaosTextView.setText(averageChaosText);

        String averageFailureText = String.format(getResources().getString(R.string.average_failure_format), df.format(averageFailureNumber));
        TextView averageFailureTextView = (TextView) findViewById(R.id.average_failure);
        averageFailureTextView.setText(averageFailureText);

        String averageBaneText = String.format(getResources().getString(R.string.average_bane_format), df.format(averageBaneNumber));
        TextView averageBaneTextView = (TextView) findViewById(R.id.average_bane);
        averageBaneTextView.setText(averageBaneText);
    }

    /**
     * Show or hide the progress spinner.
     *
     * @param isInProgress if the thread is not finished.
     */
    private void showProgress(boolean isInProgress) {
        if (isInProgress) {
            mRelaunchFab.setVisibility(View.GONE);
            mScrollView.setVisibility(View.GONE);
            mProgressSpinner.setVisibility(View.VISIBLE);
        } else {
            mRelaunchFab.setVisibility(View.VISIBLE);
            mScrollView.setVisibility(View.VISIBLE);
            mProgressSpinner.setVisibility(View.GONE);
            updateUIStatistics();
        }
    }
    //endregion


    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(IHandConstants.HAND_BUNDLE_TAG, mHand);
        setResult(RESULT_CANCELED, intent);
        super.finish();
    }
}
