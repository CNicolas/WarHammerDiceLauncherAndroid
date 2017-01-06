package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.whfrp3.R;
import com.whfrp3.databinding.DialogResultsBinding;
import com.whfrp3.model.dices.LaunchResult;

/**
 * Fragment of the dialog used to display item's details.
 */
public class LaunchResultsDialogFragment extends DialogFragment {

    /**
     * Item used by the dialog.
     */
    private LaunchResult launchResult;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogResultsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_results, null, false);
        binding.setResult(launchResult);

        builder.setView(binding.getRoot());
        builder.setTitle(R.string.resultsTitle);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        LaunchResultsDialogFragment.this.dismiss();
                    }
                }
        );

        return builder.create();
    }

    /**
     * LaunchResult setter.
     *
     * @param launchResult LaunchResult used by the dialog.
     */

    public void setLaunchResult(LaunchResult launchResult) {
        this.launchResult = launchResult;
    }
}
