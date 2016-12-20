package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IEntryConstants;
import com.whfrp3.database.entries.IItemEntryConstants;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Quality;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.List;

/**
 * DAO of items.
 */
public class ItemDao extends AbstractDaoWithId<Item> implements IItemEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public ItemDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    public List<Item> findAllByPlayerId(long playerId) {
        return findAllByColumn(COLUMN_PLAYER_ID, String.valueOf(playerId));
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(Item item) {
        ContentValues values = new ContentValues();

        // Item data
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_ENCUMBRANCE, item.getEncumbrance());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_QUALITY, item.getQuality().toString());
        values.put(COLUMN_TYPE, item.getType().toString());
        values.put(COLUMN_PLAYER_ID, item.getPlayerId());

        // Usable item data
        if (item.getType() == ItemType.USABLE_ITEM) {
            UsableItem usableItem = (UsableItem) item;

            values.put(COLUMN_LOAD, usableItem.getLoad());
        }

        // Armor data
        if (item.getType() == ItemType.ARMOR) {
            Armor armor = (Armor) item;

            values.put(COLUMN_IS_EQUIPPED, convertBooleanToInteger(armor.isEquipped()));
            values.put(COLUMN_SOAK, armor.getSoak());
            values.put(COLUMN_DEFENSE, armor.getDefense());
        }

        // Weapon data
        if (item.getType() == ItemType.WEAPON) {
            Weapon weapon = (Weapon) item;

            values.put(COLUMN_IS_EQUIPPED, convertBooleanToInteger(weapon.isEquipped()));
            values.put(COLUMN_DAMAGE, weapon.getDamage());
            values.put(COLUMN_CRITICAL_LEVEL, weapon.getCriticalLevel());
            values.put(COLUMN_RANGE, weapon.getRange().toString());
        }

        return values;
    }

    @Override
    protected Item createModelFromCursor(Cursor cursor) {
        ItemType type = ItemType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)));

        Item dto;

        // Specifics values of each item type
        switch (type) {
            case ARMOR:
                Armor armor = new Armor();
                armor.setEquipped(convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_EQUIPPED))));
                armor.setSoak(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SOAK)));
                armor.setDefense(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DEFENSE)));
                dto = armor;
                break;
            case WEAPON:
                Weapon weapon = new Weapon();
                weapon.setEquipped(convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_EQUIPPED))));
                weapon.setDamage(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DAMAGE)));
                weapon.setCriticalLevel(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CRITICAL_LEVEL)));
                weapon.setRange(Range.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RANGE))));
                dto = weapon;
                break;
            case USABLE_ITEM:
                UsableItem usableItem = new UsableItem();
                usableItem.setLoad(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LOAD)));
                dto = usableItem;
                break;
            default:
                dto = new Item();
        }

        // Item data
        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IEntryConstants.COLUMN_ID)));

        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
        dto.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
        dto.setEncumbrance(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ENCUMBRANCE)));
        dto.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)));
        dto.setQuality(Quality.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUALITY))));
        dto.setPlayerId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAYER_ID)));

        return dto;
    }

    //endregion
}
