package com.aku.warhammerdicelauncher.ihm.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.dao.CharacteristicsDao;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class CharacteristicsFragment extends Fragment {

    public CharacteristicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_characteristics, container, false);


        WarHammerDatabaseHelper whdHelper = new WarHammerDatabaseHelper(getActivity());
        CharacteristicsDao dao = new CharacteristicsDao(whdHelper);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(dao.findAll().get(0).toString());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


        return rootView;
    }

    private CharacteristicsDto createSampleDto() {
        CharacteristicsDto dto = new CharacteristicsDto();
        dto.setStrength(1);
        dto.setToughness(2);
        dto.setAgility(3);
        dto.setIntelligence(4);
        dto.setWillpower(5);
        dto.setFellowship(6);

        dto.setStrength_fortune(6);
        dto.setToughness_fortune(5);
        dto.setAgility_fortune(4);
        dto.setIntelligence_fortune(3);
        dto.setWillpower_fortune(2);
        dto.setFellowship_fortune(1);

        return dto;
    }
}
