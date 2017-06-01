package com.whfrp3.ihm.fragments.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.whfrp3.R;
import com.whfrp3.ihm.activities.ItemEditActivity;
import com.whfrp3.ihm.adapters.InventoryListAdapter;
import com.whfrp3.ihm.listeners.InventoryListeners;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.item.Equipment;
import com.whfrp3.model.item.Item;
import com.whfrp3.model.enums.ItemType;
import com.whfrp3.model.player.PlayerItem;
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

        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.action_add).setVisible(false);
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
     * @param item Item to edit.
     */
    private void startItemEditActivity(@Nullable PlayerItem item) {
        int request = ADD_ITEM_REQUEST;

        Bundle bundle = new Bundle();
        bundle.putInt(IPlayerActivityConstants.CURRENT_FRAGMENT_POSITION_BUNDLE_TAG, IPlayerActivityConstants.INVENTORY_FRAGMENT_POSITION);

        if (item != null) {
            bundle.putSerializable(ItemEditActivity.ITEM_KEY, item);
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

    private void showPopupMenuOnLongClick(final PlayerItem item, final int menuId, final View view) {
        PopupMenu itemPopupMenu = new PopupMenu(getActivity(), view, Gravity.END);
        itemPopupMenu.getMenuInflater().inflate(menuId, itemPopupMenu.getMenu());
        itemPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_menu_edit:
                        startItemEditActivity(item);
                        break;
                    case R.id.item_menu_delete:
                        WHFRP3Application.getPlayer().removeItem(item);
                        refreshInventoryView();
                        break;
                    case R.id.item_menu_equip:
                        ((Equipment) item.getItem()).setEquipped(true);
                        PlayerHelper.notifyEquipmentBinding();
                        break;
                    case R.id.item_menu_unequip:
                        ((Equipment) item.getItem()).setEquipped(false);
                        PlayerHelper.notifyEquipmentBinding();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        itemPopupMenu.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (ExpandableListView.getPackedPositionType(id) != ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            return false;
        }

        int groupPosition = ExpandableListView.getPackedPositionGroup(id);
        int childPosition = ExpandableListView.getPackedPositionChild(id);

        final PlayerItem item = (PlayerItem) mExpListView.getExpandableListAdapter().getChild(groupPosition, childPosition);

        // Select menu dialog content
        final int menuId;
        if (item.getItem().isEquipable()) {
            menuId = (((Equipment) item.getItem()).isEquipped()) ? R.menu.equiped_item : R.menu.unequiped_item;
        } else {
            menuId = R.menu.simple_item;
        }

        showPopupMenuOnLongClick(item, menuId, view);

        return true;
    }
}
