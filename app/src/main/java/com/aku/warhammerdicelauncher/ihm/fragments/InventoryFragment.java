package com.aku.warhammerdicelauncher.ihm.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.aku.warhammerdicelauncher.R;
import com.aku.warhammerdicelauncher.ihm.components.ExpandableListAdapter;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.inventory.Item;
import com.aku.warhammerdicelauncher.model.player.inventory.ItemType;
import com.aku.warhammerdicelauncher.tools.PlayerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cnicolas on 23/09/2016.
 */

public class InventoryFragment extends Fragment {

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.inventory);

        List<ItemType> categories = new ArrayList<>();
        categories.add(ItemType.WEAPON);
        categories.add(ItemType.ARMOR);
        categories.add(ItemType.USABLE_ITEM);
        categories.add(ItemType.ITEM);

        Player player = PlayerContext.getPlayerInstance();
        Map<ItemType, List<? extends Item>> items = new HashMap<>();
        items.put(ItemType.WEAPON, player.getWeapons());
        items.put(ItemType.ARMOR, player.getArmors());
        items.put(ItemType.USABLE_ITEM, player.getUsableItems());
        items.put(ItemType.ITEM, player.getItems());

        ExpandableListAdapter adapter = new ExpandableListAdapter(inflater, categories, items);

        expListView.setAdapter(adapter);

        return rootView;
    }
}
