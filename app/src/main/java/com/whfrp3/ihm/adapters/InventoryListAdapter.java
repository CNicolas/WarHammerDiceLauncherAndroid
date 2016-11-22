package com.whfrp3.ihm.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.databinding.InventoryItemBinding;
import com.whfrp3.ihm.components.AnimatedExpandableListView;
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

    //region Constructor

    /**
     * Constructor.
     *
     * @param inflater View inflater.
     */
    public InventoryListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    //endregion

    /**
     * Set children data.
     *
     * @param children New children data.
     */
    public void setChildren(Map<ItemType, List<? extends Item>> children) {
        this.children = children;

        notifyDataSetChanged();
    }

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

        InventoryItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_item, null, false);
        binding.setItem(item);

        convertView = binding.getRoot();

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
