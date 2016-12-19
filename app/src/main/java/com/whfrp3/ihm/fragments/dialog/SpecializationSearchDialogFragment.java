package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.whfrp3.R;
import com.whfrp3.databinding.DialogSpecializationSearchBinding;
import com.whfrp3.ihm.activities.SpecializationsActivity;
import com.whfrp3.ihm.adapters.EnumSpinnerAdapter;
import com.whfrp3.ihm.adapters.SkillsSpinnerAdapter;
import com.whfrp3.model.enums.Characteristic;
import com.whfrp3.model.skills.Skill;
import com.whfrp3.model.skills.Specialization;
import com.whfrp3.model.skills.SpecializationSearchFields;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.helpers.SkillHelper;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the dialog used to display the search form
 */
public class SpecializationSearchDialogFragment extends DialogFragment {

    /**
     * Search fields used by the dialog.
     */
    private SpecializationSearchFields mSpecializationSearch;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        initSpecializationSearchFields();

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogSpecializationSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_specialization_search, null, false);
        binding.setSpecSearchFields(mSpecializationSearch);

        builder.setView(binding.getRoot());
        builder.setTitle(R.string.search);
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Specialization> specializationsFound = SpecializationHelper.getInstance().search(mSpecializationSearch);
                if (specializationsFound.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IMainConstants.SPECIALIZATIONS_LIST_BUNDLE_TAG, (ArrayList<Specialization>) specializationsFound);
                    bundle.putSerializable(IMainConstants.CHARACTERISTIC_BUNDLE_TAG, mSpecializationSearch.getCharacteristic());
                    bundle.putSerializable(IMainConstants.SKILL_BUNDLE_TAG, mSpecializationSearch.getSkill());

                    Intent specializationIntent = new Intent(getActivity(), SpecializationsActivity.class);
                    specializationIntent.putExtras(bundle);

                    getActivity().startActivityForResult(specializationIntent, IMainConstants.SPECIALIZATIONS_REQUEST);
                } else {
                    ToastNotification.error("No Specialization Found !");
                }
            }
        });

        setupSkillsSpinner(inflater, binding.getRoot());
        setupCharacteristicsSpinner(inflater, binding.getRoot());

        return builder.create();
    }

    //region Spinners
    private void setupSkillsSpinner(LayoutInflater inflater, View rootView) {
        Spinner skillsSpinner = (Spinner) rootView.findViewById(R.id.skills_spinner);

        List<Skill> skills = SkillHelper.getInstance().getSkills();
        skills.add(0, null);

        skillsSpinner.setAdapter(new SkillsSpinnerAdapter(inflater, skills));
        skillsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Skill skill = (Skill) adapterView.getItemAtPosition(pos);
                mSpecializationSearch.setSkill(skill);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mSpecializationSearch.setSkill(null);
            }
        });
    }

    private void setupCharacteristicsSpinner(LayoutInflater inflater, View rootView) {
        Spinner characteristicsSpinner = (Spinner) rootView.findViewById(R.id.characteristics_spinner);

        Characteristic[] characteristicValues = Characteristic.values();
        Characteristic[] characteristics = new Characteristic[characteristicValues.length + 1];
        characteristics[0] = null;
        System.arraycopy(characteristicValues, 0, characteristics, 1, characteristicValues.length);

        characteristicsSpinner.setAdapter(new EnumSpinnerAdapter(inflater, characteristics));
        characteristicsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Characteristic characteristic = (Characteristic) adapterView.getItemAtPosition(pos);
                mSpecializationSearch.setCharacteristic(characteristic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mSpecializationSearch.setCharacteristic(null);
            }
        });
    }
    //endregion

    @Override
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);

        initSpecializationSearchFields();

        Characteristic characteristic = (Characteristic) bundle.getSerializable(IMainConstants.CHARACTERISTIC_BUNDLE_TAG);
        mSpecializationSearch.setCharacteristic(characteristic);

        Skill skill = (Skill) bundle.getSerializable(IMainConstants.SKILL_BUNDLE_TAG);
        mSpecializationSearch.setSkill(skill);
    }

    private void initSpecializationSearchFields() {
        if (mSpecializationSearch == null) {
            mSpecializationSearch = new SpecializationSearchFields();
        }
    }
}
