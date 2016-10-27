package com.whfrp3.ihm.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.model.player.inventory.Item;

/**
 * Created by cfresque on 27/10/2016.
 */

public class ItemDialogFragment extends DialogFragment {

    /**
     * Item used by the dialog.
     */
    private Item item;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        View view = inflater.inflate(R.layout.dialog_item, null);
        builder.setView(view);

        // Set dialog values
        TextView nameView = (TextView) view.findViewById(R.id.inventoryItemName);
        nameView.setText(item.getName());

        TextView descriptionView = (TextView) view.findViewById(R.id.inventoryItemDescription);
        descriptionView.setText(item.getDescription());

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
