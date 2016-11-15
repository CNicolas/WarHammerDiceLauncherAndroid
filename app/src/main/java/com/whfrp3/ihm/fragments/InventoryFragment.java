package com.whfrp3.ihm.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.InventoryListAdapter;
import com.whfrp3.ihm.components.AnimatedExpandableListView;
import com.whfrp3.ihm.fragments.inventory.ItemShowDialogFragment;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Quality;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.tools.PlayerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory fragment.
 */
public class InventoryFragment extends Fragment implements View.OnClickListener {

    private Map<ItemType, List<? extends Item>> items = new HashMap<>();
    private InventoryListAdapter adapter;

    public InventoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        FloatingActionButton addButton = (FloatingActionButton) rootView.findViewById(R.id.inventoryAddButton);
        addButton.setOnClickListener(this);

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

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                ItemShowDialogFragment dialog = new ItemShowDialogFragment();
                dialog.show(getActivity().getSupportFragmentManager(), "ItemShowDialogFragment");
                dialog.setItem((Item) expListView.getExpandableListAdapter().getChild(groupPosition, childPosition));

                return false;
            }
        });

        expListView.setOnItemLongClickListener(new ExpandableListView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int childPosition, long id) {
                Log.i("InventoryFragment", "Click long");

                return false;
            }
        });

        items.put(ItemType.ARMOR, new ArrayList<Armor>());
        items.put(ItemType.WEAPON, new ArrayList<Weapon>());
        items.put(ItemType.USABLE_ITEM, new ArrayList<UsableItem>());
        items.put(ItemType.ITEM, new ArrayList<Item>());

        adapter = new InventoryListAdapter(inflater, items);

        expListView.setAdapter(adapter);

        refreshInventoryView(null);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("InventoryFragment", String.format("%d = %d", startTime, difference));

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Player player = PlayerContext.getPlayerInstance();

        Weapon item = new Weapon(player);
        item.setName("Objet de test");
        item.setEncumbrance(2);
        item.setQuantity(4);
        item.setQuality(Quality.NORMAL);
        item.setCriticalLevel(4);
        item.setDamage(5);

        player.getInventory().add(item);

        refreshInventoryView(item.getType());
    }

    /**
     * Refresh inventory expandable list.
     *
     * @param itemType Item type of the list to refresh.
     */
    private void refreshInventoryView(ItemType itemType) {
        Player player = PlayerContext.getPlayerInstance();

        if (itemType == null) {
            items.put(itemType, player.getArmors());
            items.put(itemType, player.getWeapons());
            items.put(itemType, player.getUsableItems());
            items.put(itemType, player.getItems());
        } else {
            switch (itemType) {
                case ARMOR:
                    items.put(itemType, player.getArmors());
                    break;
                case WEAPON:
                    items.put(itemType, player.getWeapons());
                    break;
                case USABLE_ITEM:
                    items.put(itemType, player.getUsableItems());
                    break;
                case ITEM:
                    items.put(itemType, player.getItems());
                    break;
            }
        }

        adapter.notifyDataSetChanged();
    }
}
