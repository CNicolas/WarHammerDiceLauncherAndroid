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
import com.whfrp3.databinding.DialogTalentSearchBinding;
import com.whfrp3.ihm.activities.TalentsActivity;
import com.whfrp3.ihm.adapters.EnumSpinnerAdapter;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.talents.Talent;
import com.whfrp3.model.talents.TalentSearchFields;
import com.whfrp3.notification.ToastNotification;
import com.whfrp3.tools.constants.IMainConstants;
import com.whfrp3.tools.helpers.TalentHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment of the dialog used to display the search form
 */
public class TalentSearchDialogFragment extends DialogFragment {

    /**
     * Search fields used by the dialog.
     */
    private TalentSearchFields mTalentSearch;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        initTalentSearchFields();

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogTalentSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_talent_search, null, false);
        binding.setTalentSearch(mTalentSearch);

        builder.setView(binding.getRoot());
        builder.setTitle(R.string.search);
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Talent> talentsFound = TalentHelper.getInstance().search(mTalentSearch);
                if (talentsFound.size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IMainConstants.TALENTS_LIST_BUNDLE_TAG, (ArrayList<Talent>) talentsFound);

                    Intent talentsIntent = new Intent(getActivity(), TalentsActivity.class);
                    talentsIntent.putExtras(bundle);

                    getActivity().startActivityForResult(talentsIntent, IMainConstants.TALENTS_REQUEST);
                } else {
                    ToastNotification.error("No Talent Found !");
                }
            }
        });

        setupTalentTypesSpinner(inflater, binding.getRoot());
        setupCooldownSpinner(inflater, binding.getRoot());

        return builder.create();
    }

    private void setupTalentTypesSpinner(LayoutInflater inflater, View rootView) {
        Spinner talentTypesSpinner = (Spinner) rootView.findViewById(R.id.talent_type_spinner);

        TalentType[] talentTypes = new TalentType[5];
        talentTypes[0] = null;
        talentTypes[1] = TalentType.AFFINITY;
        talentTypes[2] = TalentType.REPUTATION;
        talentTypes[3] = TalentType.TACTICS;
        talentTypes[4] = TalentType.TOUR;

        talentTypesSpinner.setAdapter(new EnumSpinnerAdapter(inflater, talentTypes));
        talentTypesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                TalentType talentType = (TalentType) adapterView.getItemAtPosition(pos);
                mTalentSearch.setTalentType(talentType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mTalentSearch.setTalentType(null);
            }
        });

        int positionToSelect = 0;
        for (int i = 0; i < talentTypes.length; i++) {
            if (mTalentSearch.getTalentType() == talentTypes[i]) {
                positionToSelect = i;
                break;
            }
        }
        talentTypesSpinner.setSelection(positionToSelect, false);
    }

    private void setupCooldownSpinner(LayoutInflater inflater, View rootView) {
        Spinner cooldownSpinner = (Spinner) rootView.findViewById(R.id.cooldown_spinner);

        CooldownType[] cooldownTypes = new CooldownType[4];
        cooldownTypes[0] = null;
        cooldownTypes[1] = CooldownType.NO_COOLDOWN;
        cooldownTypes[2] = CooldownType.TALENT;
        cooldownTypes[3] = CooldownType.SESSION;

        cooldownSpinner.setAdapter(new EnumSpinnerAdapter(inflater, cooldownTypes));
        cooldownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                CooldownType cooldownType = (CooldownType) adapterView.getItemAtPosition(pos);
                mTalentSearch.setCooldownType(cooldownType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mTalentSearch.setCooldownType(null);
            }
        });
    }

    @Override
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);

        initTalentSearchFields();

        TalentType talentType = (TalentType) bundle.getSerializable(IMainConstants.TALENT_TYPE_BUNDLE_TAG);
        mTalentSearch.setTalentType(talentType);
    }

    private void initTalentSearchFields() {
        if (mTalentSearch == null) {
            mTalentSearch = new TalentSearchFields();
        }
    }
}
