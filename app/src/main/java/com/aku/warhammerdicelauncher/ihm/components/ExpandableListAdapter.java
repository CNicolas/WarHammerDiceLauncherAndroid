package com.aku.warhammerdicelauncher.ihm.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.model.player.inventory.Item;
import com.aku.warhammerdicelauncher.model.player.inventory.ItemType;

import java.util.List;
import java.util.Map;

/**
 * Adapteur utilisé pour les listes extensibles.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    //region Propriétés
    /**
     *
     */
    private LayoutInflater inflater;

    /**
     * Liste des entêtes de la liste extensible.
     */
    private List<ItemType> headers;

    /**
     * Map contenant les listes des éléments à afficher en fonction des entêtes.
     */
    private Map<ItemType, List<? extends Item>> children;
    //endregion

    //region Constructeurs
    public ExpandableListAdapter(LayoutInflater inflater, List<ItemType> headers, Map<ItemType, List<? extends Item>> children) {
        this.inflater = inflater;
        this.headers = headers;
        this.children = children;
    }
    //endregion

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(headers.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Item item = (Item) getChild(groupPosition, childPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_list_item, null);
        }

        TextView txtView = (TextView) convertView.findViewById(R.id.expListItem);
        txtView.setText(item.getName());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(headers.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemType itemType = (ItemType) getGroup(groupPosition);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_list_group, null);
        }

        TextView txtView = (TextView) convertView.findViewById(R.id.expListGroupHeader);
        txtView.setText(itemType.toString());

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
