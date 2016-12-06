package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.whfrp3.R;
import com.whfrp3.databinding.DialogItemBinding;
import com.whfrp3.model.player.inventory.Item;

/**
 * Fragment of the dialog used to display item's details.
 */
public class ItemShowDialogFragment extends DialogFragment {

    /**
     * Item used by the dialog.
     */
    private Item item;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_item, null, false);
        binding.setItem(item);

        builder.setView(binding.getRoot());
        builder.setTitle(item.getName());
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ItemShowDialogFragment.this.dismiss();
                    }
                }
        );

        return builder.create();
    }

    /**
     * Item setter.
     *
     * @param item Item used by the dialog.
     */

    public void setItem(Item item) {
        this.item = item;
    }
}
