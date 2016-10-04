package com.aku.warhammerdicelauncher.ihm.fragments;

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
import com.aku.warhammerdicelauncher.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.dao.HandDao;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.HandDto;
import com.aku.warhammerdicelauncher.utils.enums.DiceFace;
import com.aku.warhammerdicelauncher.utils.helpers.DialogHelper;
import com.aku.warhammerdicelauncher.utils.helpers.DicesRollerHelper;

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

    public LaunchFragment() {
        // Empty constructor required for fragment subclasses
    }

    //region Overrides

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_launch, container, false);

        handsSpinner = (Spinner) rootView.findViewById(R.id.hands_spinner);
        fillHandsSpinner();
        handsSpinner.setSelection(0, false);
        handsSpinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        updateButton = (Button) rootView.findViewById(R.id.updateButton);
        updateButton.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillHandsSpinner();
    }

    //endregion

    //region Roll Dices
    public void rollDices(MainActivity ctx) {
        try {
            HandDto dto = currentHandToDto(ctx);
            Map<DiceFace, Integer> res = DicesRollerHelper.rollDices(dto);

            DialogHelper.showLaunchResults(res, ctx);
        } catch (Exception e) {
            Log.e(getClass().getName(), "rollDices: ", e);
            throw e;
        }
    }
    //endregion

    //region Save Hand
    public void saveHand(MainActivity ctx) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(R.string.insertHandTitleTitle);

            final EditText input = new EditText(ctx);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton(R.string.ok, new ResultDialogOkClickListener(ctx, input));
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

    private void saveHandWithTitle(MainActivity ctx, String title) {
        HandDto handDto = prepareDto(ctx, title);

        getHandDao(ctx).insert(handDto);

        updateUI(ctx);
    }
    //endregion

    //region Update Hand
    public void updateHand(MainActivity ctx) {
        String currentHandName = getCurrentHandName();
        if (!currentHandName.isEmpty()) {
            updateHandWithTitle(ctx, currentHandName);
        }
    }

    private void updateHandWithTitle(MainActivity ctx, String title) {
        HandDto handDto = prepareDto(ctx, title);

        getHandDao(ctx).update(handDto, title);
    }
    //endregion

    //region Hand helpers
    private void useHand(MainActivity ctx, String title) {
        if (title.trim().isEmpty()) {
            resetHand(ctx);
        } else {
            HandDao dao = getHandDao(ctx);

            try {
                HandDto dto = dao.findByTitle(title);
                dtoToCurrentHand(ctx, dto);
            } catch (Resources.NotFoundException nfe) {
                Log.e(this.getClass().getName(), "useHand: ", nfe);
            }

            ctx.findViewById(R.id.updateButton).setVisibility(View.VISIBLE);
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
     * Get current pickers' value and create a HandDto with them
     */
    public HandDto currentHandToDto(MainActivity ctx) {
        HandDto dto = new HandDto();

        dto.setCharacteristic(((NumberPicker) ctx.findViewById(R.id.numberPickerCharacteristic)).getValue());
        dto.setReckless(((NumberPicker) ctx.findViewById(R.id.numberPickerReckless)).getValue());
        dto.setConservative(((NumberPicker) ctx.findViewById(R.id.numberPickerConservative)).getValue());
        dto.setExpertise(((NumberPicker) ctx.findViewById(R.id.numberPickerExpertise)).getValue());
        dto.setFortune(((NumberPicker) ctx.findViewById(R.id.numberPickerFortune)).getValue());
        dto.setMisfortune(((NumberPicker) ctx.findViewById(R.id.numberPickerMisfortune)).getValue());
        dto.setChallenge(((NumberPicker) ctx.findViewById(R.id.numberPickerChallenge)).getValue());

        return dto;
    }

    /**
     * Uses the dto's values to set pickers' value
     *
     * @param dto
     * @param ctx
     */
    public void dtoToCurrentHand(MainActivity ctx, HandDto dto) {
        NumberPicker characteristicPicker = (NumberPicker) ctx.findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) ctx.findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerChallenge);

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
     *
     * @param ctx
     */
    public void resetHand(MainActivity ctx) {
        NumberPicker characteristicPicker = (NumberPicker) ctx.findViewById(R.id.numberPickerCharacteristic);
        NumberPicker recklessPicker = (NumberPicker) ctx.findViewById(R.id.numberPickerReckless);
        NumberPicker conservativePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerConservative);
        NumberPicker expertisePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerExpertise);
        NumberPicker fortunePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerFortune);
        NumberPicker misfortunePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerMisfortune);
        NumberPicker challengePicker = (NumberPicker) ctx.findViewById(R.id.numberPickerChallenge);

        characteristicPicker.setValue(0);
        recklessPicker.setValue(0);
        conservativePicker.setValue(0);
        expertisePicker.setValue(0);
        fortunePicker.setValue(0);
        misfortunePicker.setValue(0);
        challengePicker.setValue(0);

        ctx.findViewById(R.id.updateButton).setVisibility(View.GONE);
    }
    //endregion

    //region DTO
    private HandDao getHandDao(MainActivity ctx) {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(ctx);
        return new HandDao(whdHelper);
    }

    private HandDto prepareDto(MainActivity ctx, String title) {
        HandDto handDto = currentHandToDto(ctx);
        handDto.setTitle(title);
        return handDto;
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
            MainActivity ctx = ((MainActivity) getActivity());
            if (initialized) {
                if (position == 0) {
                    resetHand(ctx);
                } else {
                    TextView selectedTextView = ((TextView) selectedItemView);
                    String title = selectedTextView.getText().toString();
                    useHand(ctx, title);
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
        private MainActivity context;

        public ResultDialogOkClickListener(MainActivity context, EditText input) {
            this.input = input;
            this.context = context;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (input.getText().toString().trim().isEmpty()) {
                Toast.makeText(context, R.string.empty_hand_name, Toast.LENGTH_SHORT).show();
            } else {
                saveHandWithTitle(context, input.getText().toString());
            }

        }
    }
    //endregion
}