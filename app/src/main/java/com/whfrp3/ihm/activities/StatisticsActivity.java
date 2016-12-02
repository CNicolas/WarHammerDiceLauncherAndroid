package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.whfrp3.R;
import com.whfrp3.databinding.ActivityStatisticsBinding;
import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.model.dices.Hand;
import com.whfrp3.model.dices.LaunchStatistics;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IHandConstants;
import com.whfrp3.tools.helpers.DicesRollerHelper;

import java.util.HashMap;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {
    //region Fields
    private Hand mHand;
    private int mTimes;
    private LaunchStatistics mStats;
    //endregion

    //region Overrides
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            mHand = (Hand) getIntent().getExtras().getSerializable(IHandConstants.HAND_BUNDLE_TAG);
            mTimes = getIntent().getExtras().getInt(IHandConstants.TIMES_BUNDLE_TAG);
        } else {
            // TODO : Add error treatment
        }

        mStats = new LaunchStatistics();
        mStats.setTimes(mTimes);

        ActivityStatisticsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_statistics);
        binding.setStats(mStats);

        callRollThread();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        WHFRP3Application.setActivity(this);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra(IHandConstants.HAND_BUNDLE_TAG, mHand);
        setResult(RESULT_CANCELED, intent);
        super.finish();
    }

    //region Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }
    //endregion
    //endregion

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
        mStats.setInProgress(true);

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
                    mStats.setInProgress(false);
                }
            }));
        }
    }
    //endregion

    /**
     * Launch the hand "mTimes" and show the statistics.
     */
    private void launchDicesAndGetStatistics() {
        int successfulLaunches = 0;

        Map<DiceFaces, Integer> allThrows = new HashMap<>();
        for (int i = 0; i < mTimes; i++) {
            Map<DiceFaces, Integer> map = DicesRollerHelper.rollDices(mHand);

            for (DiceFaces face : map.keySet()) {
                int old = allThrows.get(face) != null ? allThrows.get(face) : 0;
                allThrows.put(face, old + map.get(face));
            }

            successfulLaunches += DicesRollerHelper.isSuccessful(map) ? 1 : 0;
        }

        double averageSuccessNumber = allThrows.containsKey(DiceFaces.SUCCESS) ? allThrows.get(DiceFaces.SUCCESS) / ((double) mTimes) : 0;
        double averageBoonNumber = allThrows.containsKey(DiceFaces.BOON) ? allThrows.get(DiceFaces.BOON) / ((double) mTimes) : 0;
        double averageSigmarNumber = allThrows.containsKey(DiceFaces.SIGMAR) ? allThrows.get(DiceFaces.SIGMAR) / ((double) mTimes) : 0;
        double averageChaosNumber = allThrows.containsKey(DiceFaces.CHAOS) ? allThrows.get(DiceFaces.CHAOS) / ((double) mTimes) : 0;
        double averageFailureNumber = allThrows.containsKey(DiceFaces.FAILURE) ? allThrows.get(DiceFaces.FAILURE) / ((double) mTimes) : 0;
        double averageBaneNumber = allThrows.containsKey(DiceFaces.BANE) ? allThrows.get(DiceFaces.BANE) / ((double) mTimes) : 0;

        mStats.setSuccessfulLaunches(successfulLaunches);
        mStats.setAverageSuccess(averageSuccessNumber);
        mStats.setAverageBoon(averageBoonNumber);
        mStats.setAverageSigmar(averageSigmarNumber);
        mStats.setAverageChaos(averageChaosNumber);
        mStats.setAverageFailure(averageFailureNumber);
        mStats.setAverageBane(averageBaneNumber);
    }
}
