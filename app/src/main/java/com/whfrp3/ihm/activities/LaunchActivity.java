package com.whfrp3.ihm.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.databinding.ActivityLaunchBinding;
import com.whfrp3.ihm.listeners.LaunchActivityHandlers;
import com.whfrp3.model.dices.DiceFaces;
import com.whfrp3.model.dices.Hand;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.skill.Skill;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IHandConstants;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.DialogHelper;
import com.whfrp3.tools.helpers.DicesRollerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.whfrp3.R.id.action_update_hand;

/**
 * This activity allows to chose several dices to launch and see the results.
 */
public class LaunchActivity extends AppCompatActivity implements IPlayerActivityConstants {
    private Spinner mHandsSpinner;
    private Menu mMenuLaunch;
    private Hand mHand;

    private int mBackToPreviousFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHand = new Hand();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Skill skill = (Skill) extras.getSerializable(SKILL_BUNDLE_TAG);
            if (skill != null) {
                mHand = getHandFromSkill(skill);
            }
            mBackToPreviousFragment = extras.getInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG);
        }

        ActivityLaunchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
        binding.setHand(mHand);
        binding.setHandlers(new LaunchActivityHandlers());
        binding.diceNumberPickers.setHand(mHand);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupHandsSpinner();

        setResult(mBackToPreviousFragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == STATS_REQUEST) {
                mHand.setFromHand((Hand) intent.getSerializableExtra(IHandConstants.HAND_BUNDLE_TAG));
            }
        }
    }

    //region Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_launch, menu);

        this.mMenuLaunch = menu;

        changeUpdateOptionsMenuItemVisibility(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, mBackToPreviousFragment);
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.action_save_hand:
                saveHand();
                break;
            case R.id.action_update_hand:
                updateHand();
                break;
        }

        return true;
    }

    /**
     * Change the visibility for the "update" menu item when not usable.
     *
     * @param isVisible if the updateOptionsMenu is visible.
     */
    private void changeUpdateOptionsMenuItemVisibility(boolean isVisible) {
        MenuItem item = mMenuLaunch.findItem(action_update_hand);
        item.setVisible(isVisible);
    }
    //endregion

    //region Roll Dices

    /**
     * Event click on the launch buttons : 1 launch or more to see the statistics.
     *
     * @param v the calling view
     */
    public void rollDices(View v) {
        try {
            String tagString = v.getTag().toString();
            int times = Integer.parseInt(tagString);

            switch (times) {
                case 10:
                    startStatisticsActivity(10);
                    break;
                case 100:
                    startStatisticsActivity(100);
                    break;
                case 1000:
                    startStatisticsActivity(1000);
                    break;
                case 10000:
                    startStatisticsActivity(10000);
                    break;
                default:
                    Map<DiceFaces, Integer> res = DicesRollerHelper.rollDices(mHand);

                    DialogHelper.showLaunchResults(res, this);
            }

        } catch (Exception e) {
            Log.e(getClass().getName(), "rollDices: ", e);
            throw e;
        }
    }

    /**
     * Start a StatisticsActivity with the current hand of dices.
     *
     * @param times Number of launches of the hand.
     */
    private void startStatisticsActivity(int times) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IHandConstants.HAND_BUNDLE_TAG, mHand);
        bundle.putInt(IHandConstants.TIMES_BUNDLE_TAG, times);

        Intent statisticsIntent = new Intent(this, StatisticsActivity.class);
        statisticsIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(StatisticsActivity.class);
        stackBuilder.addNextIntent(statisticsIntent);

        startActivityForResult(statisticsIntent, STATS_REQUEST);
    }
    //endregion

    //region Hand

    /**
     * Save the current hand of dices in mDatabase for future use.
     */
    private void saveHand() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            builder.setView(input);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().toString().trim().isEmpty()) {
                        Toast.makeText(LaunchActivity.this, R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
                    } else {
                        saveHandWithTitle(input.getText().toString());
                    }
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        } catch (Exception e) {
            Log.e(getClass().getName(), "saveHand: ", e);
            throw e;
        }
    }

    /**
     * Save the current hand of dices under the given title.
     *
     * @param title the title of the new Hand.
     */
    private void saveHandWithTitle(String title) {
        mHand.setTitle(title);
        WHFRP3Application.getDatabase().getHandDao().insert(mHand);
        updateUI();
    }

    /**
     * Update the selected hand in mDatabase.
     */
    public void updateHand() {
        String currentHandName = (String) mHandsSpinner.getSelectedItem();
        if (!currentHandName.isEmpty()) {
            try {
                WHFRP3Application.getDatabase().getHandDao().update(mHand);
                Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(this.getClass().getName(), "updateHand: ", e);
            }
        }
    }

    /**
     * Use a hand from the spinner which contains the saved hands name.
     *
     * @param title the title of the hand.
     */
    private void useHand(String title) {
        if (title.trim().isEmpty()) {
            mHand.reset();
        } else {
            try {
                mHand.setFromHand(WHFRP3Application.getDatabase().getHandDao().findByTitle(title));
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getClass().getName(), "useHand: ", nfe);
            }

            changeUpdateOptionsMenuItemVisibility(true);
        }
    }

    /**
     * Make a Hand from given Skill
     *
     * @param skill
     * @return
     */
    private Hand getHandFromSkill(Skill skill) {
        Player player = WHFRP3Application.getPlayer();

        Hand hand = player.getCharacteristics().getCharacteristicHand(skill.getCharacteristic());
        hand.setExpertise(skill.getLevel());
        hand.setChallenge(1);

        int playerConservative = player.getConservative();
        if (playerConservative > 0) {
            hand.setConservative(playerConservative);
            hand.setCharacteristic(hand.getCharacteristic() - playerConservative);
        }

        int playerReckless = player.getReckless();
        if (playerReckless > 0) {
            hand.setReckless(playerReckless);
            hand.setCharacteristic(hand.getCharacteristic() - playerReckless);
        }

        return hand;
    }
    //endregion

    //region Hands Spinner

    /**
     * Set up the HandsSpinner.
     */
    private void setupHandsSpinner() {
        mHandsSpinner = (Spinner) findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        mHandsSpinner.setSelection(0, false);

        boolean initialized = WHFRP3Application.getDatabase().getHandDao().findAllTitles().size() > 0;
        mHandsSpinner.setOnItemSelectedListener(new HandsSpinnerItemSelectedListener(initialized));
    }

    /**
     * Fill the HandsSpinner (containing the names of the hands from mDatabase).
     */
    private void fillHandsSpinner() {
        List<String> databaseTitles = WHFRP3Application.getDatabase().getHandDao().findAllTitles();

        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(databaseTitles);

        mHandsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.item_hand_spinner, titles));
    }

    /**
     * The HandsSpinner listener fills the number pickers when a hand is selected.
     */
    private class HandsSpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        private boolean initialized;

        HandsSpinnerItemSelectedListener(boolean initialized) {
            this.initialized = initialized;
        }

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            if (initialized) {
                if (position == 0) {
                    mHand.reset();
                    changeUpdateOptionsMenuItemVisibility(false);
                } else {
                    TextView selectedTextView = ((TextView) selectedItemView);
                    String title = selectedTextView.getText().toString();
                    useHand(title);
                }
            }
            initialized = true;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            changeUpdateOptionsMenuItemVisibility(false);
        }
    }
    //endregion

    /**
     * Fills the hands spinner and updates the menu.
     */
    private void updateUI() {
        fillHandsSpinner();
        invalidateOptionsMenu();
    }
}
