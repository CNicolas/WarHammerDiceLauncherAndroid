package com.whfrp3.ihm.listeners;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.whfrp3.R;
import com.whfrp3.ihm.fragments.dialog.ItemShowDialogFragment;
import com.whfrp3.model.player.inventory.Item;


public abstract class InventoryListeners {
    public static class InventoryHeaderClickListener implements ExpandableListView.OnGroupClickListener {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            ImageView imageView = (ImageView) v.findViewById(R.id.inventoryGroupArrow);

            if (parent.isGroupExpanded(groupPosition)) {
                parent.collapseGroup(groupPosition);
                imageView.setRotation(90);
            } else {
                parent.expandGroup(groupPosition);
                imageView.setRotation(0);
            }
            return true;
        }
    }

    public static class InventoryItemClickListener implements ExpandableListView.OnChildClickListener {
        private final FragmentManager mFragmentManager;

        public InventoryItemClickListener(FragmentManager fragmentManager) {
            mFragmentManager = fragmentManager;
        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
            ItemShowDialogFragment dialog = new ItemShowDialogFragment();
            dialog.show(mFragmentManager, "ItemShowDialogFragment");
            dialog.setItem((Item) parent.getExpandableListAdapter().getChild(groupPosition, childPosition));

            return false;
        }
    }
}
