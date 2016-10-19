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
import com.aku.warhammerdicelauncher.tools.constants.IHandConstants;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(this);
        mHandDao = new HandDao(whdHelper);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupHandsSpinner();
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
                    Hand model = currentHandToModel();
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
        bundle.putSerializable(IHandConstants.HAND_TAG, currentHandToModel());
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
        NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(0);
        recklessPicker.setValue(0);
        conservativePicker.setValue(0);
        expertisePicker.setValue(0);
        fortunePicker.setValue(0);
        misfortunePicker.setValue(0);
        challengePicker.setValue(0);
    }
    //endregion

    //region Hand helpers
    private void useHand(String title) {
        if (title.trim().isEmpty()) {
            resetHand();
        } else {
            try {
                Hand dto = mHandDao.findByTitle(title);
                dtoToCurrentHand(dto);
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

    /**
     * Get current pickers' value and create a Hand with them
     */
    public Hand currentHandToModel() {
        Hand model = new Hand();

        model.setCharacteristic(((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue());
        model.setReckless(((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue());
        model.setConservative(((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue());
        model.setExpertise(((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue());
        model.setFortune(((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue());
        model.setMisfortune(((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue());
        model.setChallenge(((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue());

        return model;
    }

    /**
     * Uses the dto's values to set pickers' value
     *
     * @param dto
     */
    public void dtoToCurrentHand(Hand dto) {
        NumberPicker characteristicPicker = (NumberPicker) findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(dto.getCharacteristic());
        recklessPicker.setValue(dto.getReckless());
        conservativePicker.setValue(dto.getConservative());
        expertisePicker.setValue(dto.getExpertise());
        fortunePicker.setValue(dto.getFortune());
        misfortunePicker.setValue(dto.getMisfortune());
        challengePicker.setValue(dto.getChallenge());
    }
    //endregion

    private Hand prepareHandModel(String title) {
        Hand hand = currentHandToModel();
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
