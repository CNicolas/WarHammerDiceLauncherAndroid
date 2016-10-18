package com.aku.warhammerdicelauncher.ihm.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.HandDao;
import com.aku.warhammerdicelauncher.model.player.Hand;
import com.aku.warhammerdicelauncher.utils.enums.DiceFaces;
import com.aku.warhammerdicelauncher.utils.helpers.DialogHelper;
import com.aku.warhammerdicelauncher.utils.helpers.DicesRollerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LaunchActivity extends AppCompatActivity {
    private Spinner handsSpinner;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        handsSpinner = (Spinner) findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        handsSpinner.setSelection(0, false);
        handsSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener(getHandDao().findAllTitles().size() > 0));

        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setVisibility(View.GONE);
    }

    //region Roll Dices
    public void rollDices(View v) {
        try {
            Hand dto = currentHandToDto();
            Map<DiceFaces, Integer> res = DicesRollerHelper.rollDices(dto);

            DialogHelper.showLaunchResults(res, this);
        } catch (Exception e) {
            Log.e(getClass().getName(), "rollDices: ", e);
            throw e;
        }
    }
    //endregion

    //region Save Hand
    public void saveHand(View v) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton(R.string.ok, new ResultDialogOkClickListener(input));
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
        Hand hand = prepareDto(title);

        getHandDao().insert(hand);

        updateUI(this);
    }
    //endregion

    //region Update Hand
    public void updateHand(View v) {
        String currentHandName = getCurrentHandName();
        if (!currentHandName.isEmpty()) {
            updateHandWithTitle(currentHandName);
        }
    }

    private void updateHandWithTitle(String title) {
        Hand hand = prepareDto(title);

        getHandDao().update(hand, title);
    }
    //endregion

    //region Reset Hand
    public void resetHand(View v) {
        resetHand();
    }
    //endregion

    //region Hand helpers
    private void useHand(String title) {
        if (title.trim().isEmpty()) {
            resetHand();
        } else {
            HandDao dao = getHandDao();

            try {
                Hand dto = dao.findByTitle(title);
                dtoToCurrentHand(dto);
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getClass().getName(), "useHand: ", nfe);
            }

            findViewById(R.id.updateButton).setVisibility(View.VISIBLE);
        }
    }

    private String getCurrentHandName() {
        String currentHandName = (String) handsSpinner.getSelectedItem();
        return currentHandName;
    }

    private void fillHandsSpinner() {
        HandDao dao = getHandDao();
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(dao.findAllTitles());

        handsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.item_hand_spinner, titles));
    }
    //endregion

    //region Number Pickers

    /**
     * Get current pickers' value and create a Hand with them
     */
    public Hand currentHandToDto() {
        Hand dto = new Hand();

        dto.setCharacteristic(((NumberPicker) findViewById(R.id.numberPickerCharacteristic)).getValue());
        dto.setReckless(((NumberPicker) findViewById(R.id.numberPickerReckless)).getValue());
        dto.setConservative(((NumberPicker) findViewById(R.id.numberPickerConservative)).getValue());
        dto.setExpertise(((NumberPicker) findViewById(R.id.numberPickerExpertise)).getValue());
        dto.setFortune(((NumberPicker) findViewById(R.id.numberPickerFortune)).getValue());
        dto.setMisfortune(((NumberPicker) findViewById(R.id.numberPickerMisfortune)).getValue());
        dto.setChallenge(((NumberPicker) findViewById(R.id.numberPickerChallenge)).getValue());

        return dto;
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

    /**
     * Reset the number pickers values
     */
    public void resetHand() {
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

        findViewById(R.id.updateButton).setVisibility(View.GONE);
    }
    //endregion

    //region DTO
    private HandDao getHandDao() {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(this);
        return new HandDao(whdHelper);
    }

    private Hand prepareDto(String title) {
        Hand hand = currentHandToDto();
        hand.setTitle(title);
        return hand;
    }
    //endregion

    private void updateUI(Activity ctx) {
        fillHandsSpinner();
        ctx.invalidateOptionsMenu();
    }

    //region Listeners
    private class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
        private boolean initialized;

        public SpinnerItemSelectedListener(boolean initialized) {
            this.initialized = initialized;
        }

        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            if (initialized) {
                if (position == 0) {
                    resetHand();
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
        }
    }

    private class ResultDialogOkClickListener implements DialogInterface.OnClickListener {
        private final EditText input;

        public ResultDialogOkClickListener(EditText input) {
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
