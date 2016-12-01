package com.whfrp3.ihm.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.ItemEditActivity;
import com.whfrp3.ihm.adapters.InventoryListAdapter;
import com.whfrp3.ihm.listeners.InventoryListeners;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Equipment;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.IPlayerActivityConstants;
import com.whfrp3.tools.helpers.PlayerHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory fragment.
 */
public class InventoryFragment extends Fragment
        implements IPlayerActivityConstants, View.OnClickListener, AdapterView.OnItemLongClickListener {

    private InventoryListAdapter adapter;
    private ExpandableListView mExpListView;

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

        mExpListView = (ExpandableListView) rootView.findViewById(R.id.inventory_list);
        mExpListView.setOnGroupClickListener(new InventoryListeners.InventoryHeaderClickListener());
        mExpListView.setOnChildClickListener(new InventoryListeners.InventoryItemClickListener(getFragmentManager()));
        mExpListView.setOnItemLongClickListener(this);

        adapter = new InventoryListAdapter(inflater);
        refreshInventoryView();

        mExpListView.setAdapter(adapter);

        long difference = System.currentTimeMillis() - startTime;
        Log.d("InventoryFragment", String.format("%d = %d", startTime, difference));

        return rootView;
    }

    @Override
    public void onClick(View view) {
        startItemEditActivity(null);
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
    private void startItemEditActivity(@Nullable Long itemId) {
        int request = ADD_ITEM_REQUEST;

        Bundle bundle = new Bundle();
        bundle.putInt(IPlayerActivityConstants.CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION);

        if (itemId != null) {
            bundle.putLong(ItemEditActivity.ITEM_ID_KEY, itemId);
            request = EDIT_ITEM_REQUEST;
        }

        Intent launchIntent = new Intent(getActivity(), ItemEditActivity.class);
        launchIntent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addParentStack(ItemEditActivity.class);
        stackBuilder.addNextIntent(launchIntent);

        startActivityForResult(launchIntent, request);
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

        PlayerHelper.notifyBinding();
    }

    private void showDialogOnLongClick(final Item item, final int menuArrayId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.item_menu_title).setItems(menuArrayId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichPos) {
                if (menuArrayId == R.array.item_menu_actions3 && whichPos == 0) {
                    // Unequip the item
                    ((Equipment) item).setEquipped(false);
                    PlayerHelper.notifyEquipmentBinding();
                } else if (menuArrayId == R.array.item_menu_actions2 && whichPos == 0) {
                    // Equip the item
                    ((Equipment) item).setEquipped(true);
                    PlayerHelper.notifyEquipmentBinding();
                } else if ((menuArrayId == R.array.item_menu_actions1 && whichPos == 0)
                        || (menuArrayId == R.array.item_menu_actions2 && whichPos == 1)
                        || (menuArrayId == R.array.item_menu_actions3 && whichPos == 1)) {
                    // Edit item
                    startItemEditActivity(item.getId());
                } else {
                    // Delete item
                    WHFRP3Application.getPlayer().removeItem(item);
                    refreshInventoryView();
                }
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (ExpandableListView.getPackedPositionType(id) != ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            return false;
        }

        int groupPosition = ExpandableListView.getPackedPositionGroup(id);
        int childPosition = ExpandableListView.getPackedPositionChild(id);

        final Item item = (Item) mExpListView.getExpandableListAdapter().getChild(groupPosition, childPosition);

        // Select menu dialog content
        final int menuArrayId;
        if (item.isEquipable()) {
            menuArrayId = (((Equipment) item).isEquipped()) ? R.array.item_menu_actions3 : R.array.item_menu_actions2;
        } else {
            menuArrayId = R.array.item_menu_actions1;
        }

        showDialogOnLongClick(item, menuArrayId);

        return true;
    }
}
