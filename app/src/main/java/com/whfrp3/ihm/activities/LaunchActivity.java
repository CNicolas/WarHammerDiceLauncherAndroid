package com.whfrp3.ihm.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whfrp3.R;
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

    private NumberPicker mCharacteristicNumberPicker;
    private NumberPicker mRecklessNumberPicker;
    private NumberPicker mConservativeNumberPicker;
    private NumberPicker mExpertiseNumberPicker;
    private NumberPicker mFortuneNumberPicker;
    private NumberPicker mMisfortuneNumberPicker;
    private NumberPicker mChallengeNumberPicker;
    private int mBackToPreviousFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupHandsSpinner();
        initPickers();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Skill skill = (Skill) extras.getSerializable(SKILL_BUNDLE_TAG);
            if (skill != null) {
                fillNumberPickersFromSkill(skill);
            }
            mBackToPreviousFragment = extras.getInt(CURRENT_FRAGMENT_POSITION_BUNDLE_TAG);
        }

        setResult(mBackToPreviousFragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_CANCELED) {
            if (requestCode == STATS_REQUEST) {
                Hand hand = (Hand) intent.getSerializableExtra(IHandConstants.HAND_BUNDLE_TAG);
                fillNumberPickersFromHand(hand);
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
    //endregion

    //region Init

    /**
     * Init the NumberPickers fields.
     */
    private void initPickers() {
        mCharacteristicNumberPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        mRecklessNumberPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        mConservativeNumberPicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        mExpertiseNumberPicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        mFortuneNumberPicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        mMisfortuneNumberPicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        mChallengeNumberPicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
    }

    /**
     * Set up the HandsSpinner.
     */
    private void setupHandsSpinner() {
        mHandsSpinner = (Spinner) findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        mHandsSpinner.setSelection(0, false);
        mHandsSpinner.setOnItemSelectedListener(new HandsSpinnerItemSelectedListener(WHFRP3Application.getDatabase().getHandDao().findAllTitles().size() > 0));
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
                    Hand model = extractHandFromNumberPickers();
                    Map<DiceFaces, Integer> res = DicesRollerHelper.rollDices(model);

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
        bundle.putSerializable(IHandConstants.HAND_BUNDLE_TAG, extractHandFromNumberPickers());
        bundle.putInt(IHandConstants.TIMES_BUNDLE_TAG, times);

        Intent statisticsIntent = new Intent(this, StatisticsActivity.class);
        statisticsIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(StatisticsActivity.class);
        stackBuilder.addNextIntent(statisticsIntent);

        startActivityForResult(statisticsIntent, STATS_REQUEST);
    }
    //endregion

    //region Save Hand

    /**
     * Save the current hand of dices in mDatabase for future use.
     */
    private void saveHand() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton(R.string.ok, new SaveHandDialogResultOkClickListener(input));
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
    private void saveNewHandWithTitle(String title) {
        Hand hand = prepareHandModel(title);
        WHFRP3Application.getDatabase().getHandDao().insert(hand);
        updateUI();
    }
    //endregion

    //region Update Hand

    /**
     * Update the selected hand in mDatabase.
     */
    public void updateHand() {
        String currentHandName = (String) mHandsSpinner.getSelectedItem();
        if (!currentHandName.isEmpty()) {
            updateHandWithTitle(currentHandName);
            Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Update the hand in mDatabase, knowing the title.
     *
     * @param title the hand's title
     */
    private void updateHandWithTitle(String title) {
        Hand hand = prepareHandModel(title);
        WHFRP3Application.getDatabase().getHandDao().update(hand);
    }
    //endregion

    //region Reset Hand

    /**
     * Event click which reset the number pickers value.
     *
     * @param v the calling view
     */
    public void resetHand(View v) {
        resetHand();
    }

    /**
     * Reset the number pickers value.
     */
    private void resetHand() {
        mCharacteristicNumberPicker.setValue(0);
        mRecklessNumberPicker.setValue(0);
        mConservativeNumberPicker.setValue(0);
        mExpertiseNumberPicker.setValue(0);
        mFortuneNumberPicker.setValue(0);
        mMisfortuneNumberPicker.setValue(0);
        mChallengeNumberPicker.setValue(0);
    }
    //endregion

    //region Hand helpers

    /**
     * Use a hand from the spinner which contains the saved hands name.
     *
     * @param title the title of the hand.
     */
    private void useHand(String title) {
        if (title.trim().isEmpty()) {
            resetHand();
        } else {
            try {
                Hand hand = WHFRP3Application.getDatabase().getHandDao().findByTitle(title);
                fillNumberPickersFromHand(hand);
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getClass().getName(), "useHand: ", nfe);
            }

            changeUpdateOptionsMenuItemVisibility(true);
        }
    }

    /**
     * Fill the HandsSpinner (containing the names of the hands from mDatabase).
     */
    private void fillHandsSpinner() {
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(WHFRP3Application.getDatabase().getHandDao().findAllTitles());

        mHandsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.item_hand_spinner, titles));
    }
    //endregion

    //region Number Pickers

    /**
     * Fill the number pickers from a given Skill for the Player.
     *
     * @param skill the skill.
     */
    private void fillNumberPickersFromSkill(Skill skill) {
        Player player = WHFRP3Application.getPlayer();

        Hand startingHand = player.getCharacteristics().getCharacteristicHand(skill.getCharacteristic());
        startingHand.setExpertise(skill.getLevel());
        startingHand.setChallenge(1);

        int playerConservative = player.getConservative();
        if (playerConservative > 0) {
            startingHand.setConservative(playerConservative);
            startingHand.setCharacteristic(startingHand.getCharacteristic() - playerConservative);
        }

        int playerReckless = player.getReckless();
        if (playerReckless > 0) {
            startingHand.setReckless(playerReckless);
            startingHand.setCharacteristic(startingHand.getCharacteristic() - playerReckless);
        }

        fillNumberPickersFromHand(startingHand);
    }

    /**
     * Create a Hand from the number pickers' value.
     */
    public Hand extractHandFromNumberPickers() {
        Hand model = new Hand();

        model.setCharacteristic(mCharacteristicNumberPicker.getValue());
        model.setReckless(mRecklessNumberPicker.getValue());
        model.setConservative(mConservativeNumberPicker.getValue());
        model.setExpertise(mExpertiseNumberPicker.getValue());
        model.setFortune(mFortuneNumberPicker.getValue());
        model.setMisfortune(mMisfortuneNumberPicker.getValue());
        model.setChallenge(mChallengeNumberPicker.getValue());

        return model;
    }

    /**
     * Uses the given Hand values to set pickers' value.
     *
     * @param model the Hand.
     */
    public void fillNumberPickersFromHand(Hand model) {
        mCharacteristicNumberPicker.setValue(model.getCharacteristic());
        mRecklessNumberPicker.setValue(model.getReckless());
        mConservativeNumberPicker.setValue(model.getConservative());
        mExpertiseNumberPicker.setValue(model.getExpertise());
        mFortuneNumberPicker.setValue(model.getFortune());
        mMisfortuneNumberPicker.setValue(model.getMisfortune());
        mChallengeNumberPicker.setValue(model.getChallenge());
    }
    //endregion

    /**
     * Create a hand of dices from number pickers and the given title.
     *
     * @param title the title for the Hand.
     * @return a Hand.
     */
    private Hand prepareHandModel(String title) {
        Hand hand = extractHandFromNumberPickers();
        hand.setTitle(title);
        return hand;
    }

    /**
     * Fills the hands spinner and updates the menu.
     */
    private void updateUI() {
        fillHandsSpinner();
        invalidateOptionsMenu();
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

    //region Listeners

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
                    resetHand();
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

    /**
     * Handle the result of the dialog when saving a hand.
     */
    private class SaveHandDialogResultOkClickListener implements DialogInterface.OnClickListener {
        private final EditText input;

        SaveHandDialogResultOkClickListener(EditText input) {
            this.input = input;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (input.getText().toString().trim().isEmpty()) {
                Toast.makeText(LaunchActivity.this, R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
            } else {
                saveNewHandWithTitle(input.getText().toString());
            }

        }
    }
    //endregion
}
