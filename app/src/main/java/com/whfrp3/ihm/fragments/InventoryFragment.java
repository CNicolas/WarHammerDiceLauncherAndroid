package com.whfrp3.ihm.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.whfrp3.BR;
import com.whfrp3.R;
import com.whfrp3.ihm.activities.ItemEditActivity;
import com.whfrp3.ihm.adapters.InventoryListAdapter;
import com.whfrp3.ihm.fragments.dialog.ItemShowDialogFragment;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Equipment;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory fragment.
 */
public class InventoryFragment extends Fragment implements IPlayerActivityConstants, View.OnClickListener {

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

        final ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.inventory);

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
                    expListView.collapseGroup(groupPosition);
                    imageView.setRotation(90);
                } else {
                    expListView.expandGroup(groupPosition);
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

        expListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) != ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    return false;
                }

                int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                int childPosition = ExpandableListView.getPackedPositionChild(id);

                final Item item = (Item) expListView.getExpandableListAdapter().getChild(groupPosition, childPosition);

                // Select menu dialog content
                final int menuArrayId;
                if (item.isEquipable()) {
                    menuArrayId = (((Equipment) item).isEquipped()) ? R.array.item_menu_actions3 : R.array.item_menu_actions2;
                } else {
                    menuArrayId = R.array.item_menu_actions1;
                }

                // Build and show menu dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.item_menu_title)
                        .setItems(menuArrayId, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichPos) {
                                if (menuArrayId == R.array.item_menu_actions3 && whichPos == 0) {
                                    // Unequip the item
                                    ((Equipment) item).setEquipped(false);
                                    refreshInventoryView();
                                } else if (menuArrayId == R.array.item_menu_actions2 && whichPos == 0) {
                                    // Equip the item
                                    ((Equipment) item).setEquipped(true);
                                    refreshInventoryView();
                                } else if ((menuArrayId == R.array.item_menu_actions1 && whichPos == 0)
                                        || (menuArrayId == R.array.item_menu_actions2 && whichPos == 1)
                                        || (menuArrayId == R.array.item_menu_actions3 && whichPos == 1)) {
                                    // Edit item
                                    openEditActivity(item.getId());
                                } else {
                                    // Delete item
                                    WHFRP3Application.getPlayer().removeItem(item);
                                    refreshInventoryView();
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });

        adapter = new InventoryListAdapter(inflater);
        refreshInventoryView();

        expListView.setAdapter(adapter);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("InventoryFragment", String.format("%d = %d", startTime, difference));

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Intent launchIntent = new Intent(InventoryFragment.this.getActivity(), ItemEditActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(InventoryFragment.this.getContext());
        stackBuilder.addParentStack(ItemEditActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivityForResult(launchIntent, ADD_ITEM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_ITEM_REQUEST || requestCode == EDIT_ITEM_REQUEST) {
                refreshInventoryView();
            }
        }
    }

    /**
     * Open an ItemEditActivity to edit the selected item.
     *
     * @param itemId Item's id to edit.
     */
    private void openEditActivity(long itemId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ItemEditActivity.ITEM_ID_KEY, itemId);
        bundle.putInt(IPlayerActivityConstants.CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION);

        Intent launchIntent = new Intent(InventoryFragment.this.getActivity(), ItemEditActivity.class);
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(InventoryFragment.this.getContext());
        stackBuilder.addParentStack(ItemEditActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivityForResult(launchIntent, EDIT_ITEM_REQUEST);
    }

    /**
     * Refresh inventory expandable list.
     */
    private void refreshInventoryView() {
        Player player = WHFRP3Application.getPlayer();

        Map<ItemType, List<? extends Item>> items = new HashMap<>();

        items.put(ItemType.ARMOR, player.getArmors());
        items.put(ItemType.WEAPON, player.getWeapons());
        items.put(ItemType.USABLE_ITEM, player.getUsableItems());
        items.put(ItemType.ITEM, player.getItems());

        adapter.setChildren(items);

        WHFRP3Application.getPlayer().notifyPropertyChanged(BR.fullDefenseAmount);
        WHFRP3Application.getPlayer().notifyPropertyChanged(BR.fullSoakAmount);
        WHFRP3Application.getPlayer().notifyPropertyChanged(BR.currentEncumbrance);
        WHFRP3Application.getPlayer().notifyPropertyChanged(BR.equippedWeapons);
    }
}
