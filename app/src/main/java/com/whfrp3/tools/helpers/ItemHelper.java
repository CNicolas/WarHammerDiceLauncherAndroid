package com.whfrp3.tools.helpers;

import android.util.LongSparseArray;

import com.whfrp3.model.enums.ItemType;
import com.whfrp3.model.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Item helper used to manage items.
 */
public class ItemHelper {

    //region Properties

    /**
     * Unique instance of the helper.
     */
    private static ItemHelper instance;

    /**
     * Loaded items.
     */
    private final List<Item> items;

    /**
     * Loaded items by id.
     */
    private final LongSparseArray<Item> itemsById;

    /**
     * Loaded items by type.
     */
    private final Map<ItemType, List<Item>> itemsByType;

    //endregion

    //region Constructor

    /**
     * Default private constructor.
     */
    private ItemHelper() {
        items = new ArrayList<>();
        itemsById = new LongSparseArray<>();
        itemsByType = new HashMap<>();
        for (ItemType type : ItemType.values()) {
            itemsByType.put(type, new ArrayList<Item>());
        }
    }

    //endregion

    //region Main methods

    /**
     * Getter of the unique instance of the item helper.
     *
     * @return Unique instance of the item helper.
     */
    public static ItemHelper getInstance() {
        if (instance == null) {
            instance = new ItemHelper();
        }

        return instance;
    }

    //endregion

    /**
     * Returns all loaded items.
     *
     * @return All loaded items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Return the item with the given id.
     *
     * @param itemId Id of the item to return.
     * @return Item with the given id.
     */
    public Item getItemById(long itemId) {
        return itemsById.get(itemId);
    }

    /**
     * Return the items of the given type.
     *
     * @param type Type of the items to return.
     * @return Items of the given type.
     */
    public List<Item> getItemsByType(ItemType type) {
        return itemsByType.get(type);
    }
}
