package com.aku.warhammerdicelauncher.ihm.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.activities.MainActivity;
import com.aku.warhammerdicelauncher.model.dao.HandDao;
import com.aku.warhammerdicelauncher.model.database.helper.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.model.dto.HandDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 12/05/2016.
 */
public class LaunchFragment extends Fragment {

    private Spinner handsSpinner;

    public LaunchFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_launch, container, false);

        handsSpinner = (Spinner) rootView.findViewById(R.id.hands_spinner);
        handsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String title = ((TextView) selectedItemView).getText().toString();
                ((MainActivity) getActivity()).useHand(title);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        fillHandsSpinner();
    }

    public void fillHandsSpinner() {
        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(getActivity());
        HandDao dao = new HandDao(whdHelper);
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.addAll(dao.findAllTitles());

        handsSpinner.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.hand_spinner_item, titles));
    }

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
     */
    public void dtoToCurrentHand(HandDto dto, MainActivity ctx) {
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
    }

}