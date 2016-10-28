package com.whfrp3.ihm.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.whfrp3.R;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.tools.helpers.ViewHelper;

/**
 * Fragment of the dialog used to display item's details.
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

        // Set item details
        ViewHelper.setTextViewValue(view, R.id.inventoryItemName, item.getName());
        ViewHelper.setTextViewValue(view, R.id.inventoryItemDescription, item.getDescription());
        ViewHelper.setTextViewValueId(view, R.id.inventoryItemQuality, item.getQuality().getLabelId());
        ViewHelper.setTextViewValue(view, R.id.inventoryItemQuantity, item.getQuantity());
        ViewHelper.setTextViewValue(view, R.id.inventoryItemEncumbrance, item.getEncumbrance());

        // Set specific details
        switch (item.getType()) {
            case ARMOR:
                ViewHelper.setViewVisibility(view, R.id.inventoryArmorDetails, View.VISIBLE);
                ViewHelper.setTextViewValue(view, R.id.inventoryArmorDefense, item.toArmor().getDefense());
                ViewHelper.setTextViewValue(view, R.id.inventoryArmorSoak, item.toArmor().getSoak());
                break;
            case WEAPON:
                ViewHelper.setViewVisibility(view, R.id.inventoryWeaponDetails, View.VISIBLE);
                ViewHelper.setTextViewValue(view, R.id.inventoryWeaponDamage, item.toWeapon().getDamage());
                ViewHelper.setTextViewValue(view, R.id.inventoryWeaponCriticalLevel, item.toWeapon().getCriticalLevel());
                break;
            case USABLE_ITEM:
                ViewHelper.setViewVisibility(view, R.id.inventoryUsableItemDetails, View.VISIBLE);
                ViewHelper.setTextViewValue(view, R.id.inventoryUsableItemLoad, item.toUsableItem().getLoad());
                break;
        }

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
