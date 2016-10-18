package com.aku.warhammerdicelauncher.ihm.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.aku.warhammerdicelauncher.ihm.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.dices.DiceFaces;
import com.aku.warhammerdicelauncher.model.player.Hand;
import com.aku.warhammerdicelauncher.tools.helpers.DialogHelper;
import com.aku.warhammerdicelauncher.tools.helpers.DicesRollerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cnicolas on 12/05/2016.
 */
public class LaunchFragment extends Fragment {

    private Spinner handsSpinner;
    private Button updateButton;
    private boolean initialized;
    private View mRootView;

    public LaunchFragment() {
        // Empty constructor required for fragment subclasses
    }

    //region Overrides

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_launch, container, false);

        handsSpinner = (Spinner) mRootView.findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        handsSpinner.setSelection(0, false);
        handsSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        updateButton = (Button) mRootView.findViewById(R.id.updateButton);
        updateButton.setVisibility(View.GONE);

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillHandsSpinner();
    }

    //endregion

    //region Roll Dices
    public void rollDices() {
        try {
            Hand dto = currentHandToDto();
            Map<DiceFaces, Integer> res = DicesRollerHelper.rollDices(dto);

            DialogHelper.showLaunchResults(res, getActivity());
        } catch (Exception e) {
            Log.e(getClass().getName(), "rollDices: ", e);
            throw e;
        }
    }
    //endregion

    //region Save Hand
    public void saveHand() {
        try {
            Activity ctx = getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(ctx);
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

        updateUI((MainActivity) getActivity());
    }
    //endregion

    //region Update Hand
    public void updateHand() {
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

            mRootView.findViewById(R.id.updateButton).setVisibility(View.VISIBLE);
        }
    }

    private String getCurrentHandName() {
        String currentHandName = (String) handsSpinner.getSelectedItem();
        return currentHandName;
    }

    private void fillHandsSpinner() {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(getActivity());
        HandDao dao = new HandDao(whdHelper);
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(dao.findAllTitles());

        handsSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_hand_spinner, titles));
    }
    //endregion

    //region Number Pickers

    /**
     * Get current pickers' value and create a Hand with them
     */
    public Hand currentHandToDto() {
        Hand dto = new Hand();

        dto.setCharacteristic(((NumberPicker) mRootView.findViewById(R.id.numberPickerCharacteristic)).getValue());
        dto.setReckless(((NumberPicker) mRootView.findViewById(R.id.numberPickerReckless)).getValue());
        dto.setConservative(((NumberPicker) mRootView.findViewById(R.id.numberPickerConservative)).getValue());
        dto.setExpertise(((NumberPicker) mRootView.findViewById(R.id.numberPickerExpertise)).getValue());
        dto.setFortune(((NumberPicker) mRootView.findViewById(R.id.numberPickerFortune)).getValue());
        dto.setMisfortune(((NumberPicker) mRootView.findViewById(R.id.numberPickerMisfortune)).getValue());
        dto.setChallenge(((NumberPicker) mRootView.findViewById(R.id.numberPickerChallenge)).getValue());

        return dto;
    }

    /**
     * Uses the dto's values to set pickers' value
     *
     * @param dto
     */
    public void dtoToCurrentHand(Hand dto) {
        NumberPicker characteristicPicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerChallenge);

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
        NumberPicker characteristicPicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) mRootView.findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(0);
        recklessPicker.setValue(0);
        conservativePicker.setValue(0);
        expertisePicker.setValue(0);
        fortunePicker.setValue(0);
        misfortunePicker.setValue(0);
        challengePicker.setValue(0);

        mRootView.findViewById(R.id.updateButton).setVisibility(View.GONE);
    }
    //endregion

    //region DTO
    private HandDao getHandDao() {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(getActivity());
        return new HandDao(whdHelper);
    }

    private Hand prepareDto(String title) {
        Hand hand = currentHandToDto();
        hand.setTitle(title);
        return hand;
    }
    //endregion

    private void updateUI(MainActivity ctx) {
        fillHandsSpinner();
        ctx.invalidateOptionsMenu();
    }

    //region Listeners
    private class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener {
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
                Toast.makeText(getActivity(), R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
            } else {
                saveHandWithTitle(input.getText().toString());
            }

        }
    }
    //endregion
}