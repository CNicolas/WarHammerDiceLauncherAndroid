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
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Quality;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.ArrayList;
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

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                // TODO : implement inventory item popup detail
                ItemDialogFragment dialog = new ItemDialogFragment();
                dialog.show(getActivity().getSupportFragmentManager(), "ItemDialogFragment");
                dialog.setItem((Item) expListView.getExpandableListAdapter().getChild(groupPosition, childPosition));

                return false;
            }
        });

        Player player = createTestPlayer();
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

    private Player createTestPlayer() {
        Player res = new Player();

        res.setCareer("Ratier");
        res.setExperience(0);
        res.setMax_experience(16);
        res.getCharacteristics().setFellowship(3);
        res.getCharacteristics().setFellowship_fortune(1);
        res.getCharacteristics().setStrength(5);
        res.getCharacteristics().setStrength_fortune(1);
        res.getCharacteristics().setToughness(4);
        res.setDescription("Trop tanky !");

        // Ajout des objets de test
        res.setInventory(new ArrayList<Item>());

        Armor armor = new Armor(res);
        armor.setName("Slip de combat");
        armor.setDescription("Slip en titane renforcé, pour les nains vénères.");
        armor.setEncumbrance(5);
        armor.setQuantity(1);
        armor.setQuality(Quality.SUPERIOR);
        armor.setSoak(5);
        armor.setDefense(2);
        res.getInventory().add(armor);

        Weapon weapon = new Weapon(res);
        weapon.setName("Epée en bois");
        weapon.setDescription("Parce qu'un nain vénère ça utilise une épée en bois !");
        weapon.setEncumbrance(3);
        weapon.setQuantity(1);
        weapon.setQuality(Quality.LOW);
        weapon.setDamage(2);
        weapon.setCriticalLevel(4);
        weapon.setRange(Range.ENGAGED);
        res.getInventory().add(weapon);

        UsableItem usableItem = new UsableItem(res);
        usableItem.setName("Potion sent bon");
        usableItem.setDescription("Parce qu'il faut bien compenser le manque de savon.");
        usableItem.setEncumbrance(3);
        usableItem.setQuantity(1);
        usableItem.setQuality(Quality.NORMAL);
        usableItem.setLoad(2);
        res.getInventory().add(usableItem);

        Item item = new Item(res);
        item.setName("Oreille de gobelin");
        item.setEncumbrance(0);
        item.setQuantity(1);
        item.setQuality(Quality.LOW);
        res.getInventory().add(item);

        return res;
    }
}
