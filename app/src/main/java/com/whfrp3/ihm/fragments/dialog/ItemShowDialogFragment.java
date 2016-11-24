package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.whfrp3.R;
import com.whfrp3.databinding.ItemShowDialogBinding;
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
        ItemShowDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_show_dialog, null, false);
        binding.setItem(item);

        builder.setView(binding.getRoot());

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
