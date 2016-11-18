package com.whfrp3.ihm.adapters;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.ihm.components.AnimatedExpandableListView;
import com.whfrp3.model.player.inventory.Equipment;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;

import java.util.List;
import java.util.Map;

/**
 * Adapter used by the InventoryFragment to show the item list.
 */
public class InventoryListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    //region Properties
    /**
     * Inflater.
     */
    private LayoutInflater inflater;

    /**
     * Map containing inventory items by ItemType.
     */
    private Map<ItemType, List<? extends Item>> children;
    //endregion

    //region Constructors
    public InventoryListAdapter(LayoutInflater inflater, Map<ItemType, List<? extends Item>> children) {
        this.inflater = inflater;
        this.children = children;
    }
    //endregion

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(ItemType.getByOrdinal(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Item item = (Item) getChild(groupPosition, childPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.inventory_item, null);
        }

        // Set item name
        TextView nameView = (TextView) convertView.findViewById(R.id.inventoryItemName);
        nameView.setText(item.getName());

        if (item.isEquipable() && ((Equipment) item).isEquipped()) {
            nameView.setTypeface(nameView.getTypeface(), Typeface.BOLD);
        }

        // Set item quantity
        TextView quantityView = (TextView) convertView.findViewById(R.id.inventoryItemQuantity);
        quantityView.setText(String.valueOf(item.getQuantity()));

        // Set item quality
        TextView qualityView = (TextView) convertView.findViewById(R.id.inventoryItemQuality);
        qualityView.setText(item.getQuality().getLabelId());

        // Set item encumbrance
        TextView encumbranceView = (TextView) convertView.findViewById(R.id.inventoryItemEncumbrance);
        encumbranceView.setText(String.valueOf(item.getEncumbrance()));

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return children.get(ItemType.getByOrdinal(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return ItemType.getByOrdinal(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return ItemType.values().length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemType itemType = (ItemType) getGroup(groupPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.inventory_group_header, null);
        }

        TextView txtView = (TextView) convertView.findViewById(R.id.inventoryGroupName);
        txtView.setText(itemType.getLabelId());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
