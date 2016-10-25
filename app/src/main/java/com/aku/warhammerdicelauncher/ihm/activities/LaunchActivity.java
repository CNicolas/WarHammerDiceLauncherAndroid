package com.aku.warhammerdicelauncher.ihm.activities;

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

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.HandDao;
import com.aku.warhammerdicelauncher.model.dices.DiceFaces;
import com.aku.warhammerdicelauncher.model.player.Hand;
import com.aku.warhammerdicelauncher.model.player.skill.Skill;
import com.aku.warhammerdicelauncher.tools.PlayerContext;
import com.aku.warhammerdicelauncher.tools.constants.IHandConstants;
import com.aku.warhammerdicelauncher.tools.constants.IPlayerConstants;
import com.aku.warhammerdicelauncher.tools.helpers.DialogHelper;
import com.aku.warhammerdicelauncher.tools.helpers.DicesRollerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aku.warhammerdicelauncher.R.id.action_update_hand;

/**
 * This activity allows to chose several dices to launch and see the results.
 */
public class LaunchActivity extends AppCompatActivity {
    private Spinner mHandsSpinner;
    private Menu mMenuLaunch;
    private HandDao mHandDao;

    private NumberPicker mCharacteristicNumberPicker;
    private NumberPicker mRecklessNumberPicker;
    private NumberPicker mConservativeNumberPicker;
    private NumberPicker mExpertiseNumberPicker;
    private NumberPicker mFortuneNumberPicker;
    private NumberPicker mMisfortuneNumberPicker;
    private NumberPicker mChallengeNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(this);
        mHandDao = new HandDao(whdHelper);

        setupHandsSpinner();
        initPickers();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Skill skill = (Skill) extras.getSerializable(IPlayerConstants.SKILL_TAG);
            fillPickersFromSkill(skill);
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

        if (itemId == R.id.action_save_hand) {
            saveHand();
            return true;
        }
        if (itemId == action_update_hand) {
            updateHand();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        mHandsSpinner.setOnItemSelectedListener(new HandsSpinnerItemSelectedListener(mHandDao.findAllTitles().size() > 0));
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
        bundle.putSerializable(IHandConstants.HAND_TAG, extractHandFromNumberPickers());
        bundle.putInt(IHandConstants.TIMES_TAG, times);

        Intent statisticsIntent = new Intent(this, StatisticsActivity.class);
        statisticsIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(StatisticsActivity.class);
        stackBuilder.addNextIntent(statisticsIntent);

        startActivity(statisticsIntent);
    }
    //endregion

    //region Save Hand

    /**
     * Save the current hand of dices in database for future use.
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
        mHandDao.insert(hand);
        updateUI();
    }
    //endregion

    //region Update Hand

    /**
     * Update the selected hand in database.
     */
    public void updateHand() {
        String currentHandName = (String) mHandsSpinner.getSelectedItem();
        if (!currentHandName.isEmpty()) {
            updateHandWithTitle(currentHandName);
            Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Update the hand in database, knowing the title.
     *
     * @param title the hand's title
     */
    private void updateHandWithTitle(String title) {
        Hand hand = prepareHandModel(title);
        mHandDao.update(hand, title);
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
                Hand dto = mHandDao.findByTitle(title);
                fillNumberPickersFromHand(dto);
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getClass().getName(), "useHand: ", nfe);
            }

            changeUpdateOptionsMenuItemVisibility(true);
        }
    }

    /**
     * Fill the HandsSpinner (containing the names of the hands from database).
     */
    private void fillHandsSpinner() {
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(mHandDao.findAllTitles());

        mHandsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.item_hand_spinner, titles));
    }
    //endregion

    //region Number Pickers

    /**
     * Fill the number pickers from a given Skill for the Player.
     *
     * @param skill the skill.
     */
    private void fillPickersFromSkill(Skill skill) {
        Hand startingHand = PlayerContext.getPlayerInstance().getCharacteristics().getCharacteristicHand(skill.getCharacteristic());
        startingHand.setExpertise(skill.getLevel());
        startingHand.setChallenge(1);

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
