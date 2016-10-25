package com.aku.warhammerdicelauncher.ihm.activities;

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
    private void initPickers() {
        mCharacteristicNumberPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        mRecklessNumberPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        mConservativeNumberPicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        mExpertiseNumberPicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        mFortuneNumberPicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        mMisfortuneNumberPicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        mChallengeNumberPicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);
    }
    //endregion

    //region Roll Dices
    public void rollDices(View v) {
        try {
            String tagString = v.getTag().toString();
            int times = Integer.parseInt(tagString);

            switch (times) {
                case 10:
                    launchStatisticsActivity(10);
                    break;
                case 100:
                    launchStatisticsActivity(100);
                    break;
                case 1000:
                    launchStatisticsActivity(1000);
                    break;
                case 10000:
                    launchStatisticsActivity(10000);
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

    private void launchStatisticsActivity(int times) {
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

    private void saveHandWithTitle(String title) {
        Hand hand = prepareHandModel(title);
        mHandDao.insert(hand);
        updateUI(this);
    }
    //endregion

    //region Update Hand
    public void updateHand() {
        String currentHandName = getCurrentHandName();
        if (!currentHandName.isEmpty()) {
            updateHandWithTitle(currentHandName);
            Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG);
        }
    }

    private void updateHandWithTitle(String title) {
        Hand hand = prepareHandModel(title);
        mHandDao.update(hand, title);
    }
    //endregion

    //region Reset Hand
    public void resetHand(View v) {
        resetHand();
    }

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

    private String getCurrentHandName() {
        String currentHandName = (String) mHandsSpinner.getSelectedItem();
        return currentHandName;
    }

    private void fillHandsSpinner() {
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(mHandDao.findAllTitles());

        mHandsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.item_hand_spinner, titles));
    }
    //endregion

    //region Number Pickers
    private void fillPickersFromSkill(Skill skill) {
        Hand startingHand = PlayerContext.getPlayerInstance().getCharacteristics().getCharacteristicHand(skill.getCharacteristic());
        startingHand.setExpertise(skill.getLevel());
        startingHand.setChallenge(1);

        fillNumberPickersFromHand(startingHand);
    }

    /**
     * Get current pickers' value and create a Hand with them
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
     * Uses the model's values to set pickers' value
     *
     * @param model
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

    private Hand prepareHandModel(String title) {
        Hand hand = extractHandFromNumberPickers();
        hand.setTitle(title);
        return hand;
    }

    private void updateUI(Activity ctx) {
        fillHandsSpinner();
        ctx.invalidateOptionsMenu();
    }

    private void changeUpdateOptionsMenuItemVisibility(boolean isVisible) {
        MenuItem item = mMenuLaunch.findItem(action_update_hand);
        item.setVisible(isVisible);
    }

    private void setupHandsSpinner() {
        mHandsSpinner = (Spinner) findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        mHandsSpinner.setSelection(0, false);
        mHandsSpinner.setOnItemSelectedListener(new HandsSpinnerItemSelectedListener(mHandDao.findAllTitles().size() > 0));
    }

    //region Listeners
    private class HandsSpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        private boolean initialized;

        public HandsSpinnerItemSelectedListener(boolean initialized) {
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

    private class SaveHandDialogResultOkClickListener implements DialogInterface.OnClickListener {
        private final EditText input;

        public SaveHandDialogResultOkClickListener(EditText input) {
            this.input = input;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (input.getText().toString().trim().isEmpty()) {
                Toast.makeText(LaunchActivity.this, R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
            } else {
                saveHandWithTitle(input.getText().toString());
            }

        }
    }
    //endregion
}
