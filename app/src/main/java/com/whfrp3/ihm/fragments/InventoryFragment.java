package com.whfrp3.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.InventoryListAdapter;
import com.whfrp3.ihm.components.AnimatedExpandableListView;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.tools.PlayerContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory fragment.
 */
public class InventoryFragment extends Fragment {

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        final AnimatedExpandableListView expListView = (AnimatedExpandableListView) rootView.findViewById(R.id.inventory);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                ImageView imageView = (ImageView) v.findViewById(R.id.inventoryGroupArrow);

                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (expListView.isGroupExpanded(groupPosition)) {
                    expListView.collapseGroupWithAnimation(groupPosition);
                    imageView.setRotation(90);
                } else {
                    expListView.expandGroupWithAnimation(groupPosition);
                    imageView.setRotation(0);
                }
                return true;
            }

        });

        Player player = PlayerContext.createTestPlayer();
        Map<ItemType, List<? extends Item>> items = new HashMap<>();
        items.put(ItemType.WEAPON, player.getWeapons());
        items.put(ItemType.ARMOR, player.getArmors());
        items.put(ItemType.USABLE_ITEM, player.getUsableItems());
        items.put(ItemType.ITEM, player.getItems());

        InventoryListAdapter adapter = new InventoryListAdapter(inflater, items);

        expListView.setAdapter(adapter);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("InventoryFragment", String.format("%d = %d", startTime, difference));

        return rootView;
    }
}
