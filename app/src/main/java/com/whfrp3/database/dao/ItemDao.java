package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.whfrp3.database.WarHammerDatabaseHelper;
import com.whfrp3.database.entries.IItemEntryConstants;
import com.whfrp3.model.player.Player;
import com.whfrp3.model.player.inventory.Armor;
import com.whfrp3.model.player.inventory.Item;
import com.whfrp3.model.player.inventory.ItemType;
import com.whfrp3.model.player.inventory.Quality;
import com.whfrp3.model.player.inventory.Range;
import com.whfrp3.model.player.inventory.UsableItem;
import com.whfrp3.model.player.inventory.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO de gestion de la table des objets.
 */
public class ItemDao extends AbstractDao<Item> implements IItemEntryConstants {
    //region Constructeurs
    /**
     * Constructeur.
     *
     * @param whdHelper Helper de la base de données.
     */
    public ItemDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);

        tableName = TABLE_NAME;
        columnId = COLUMN_ID;
    }
    //endregion

    //region Find
    public Item findByName(String name) {
        return findByStringInColumn(name, COLUMN_NAME);
    }

    public List<Item> findAllByPlayer(Player player) {
        return findAllByPlayerId(player.getId());
    }

    public List<Item> findAllByPlayerId(int playerId) {
        return findAllByIntegerInColumn(playerId, COLUMN_PLAYER_ID);
    }
    //endregion

    //region Insert
    public long insert(Item model, Player player) {
        model.setPlayerId(player.getId());
        return insert(model);
    }

    public List<Long> insertAll(List<Item> models, Player player) {
        List<Long> res = new ArrayList<>();

        for (Item item : models) {
            res.add(insert(item, player));
        }

        return res;
    }
    //endregion

    //region Update
    public long update(Item model, Player player) {
        if (model.getPlayerId() != player.getId()) {
            model.setPlayerId(player.getId());
        }
        return update(model);
    }
    //endregion

    //region Protected Methods
    @Override
    protected ContentValues contentValuesFromModel(Item item) {
        ContentValues values = new ContentValues();

        // Ajout des données de l'objet standard
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_DESCRIPTION, item.getDescription());
        values.put(COLUMN_ENCUMBRANCE, item.getEncumbrance());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_QUALITY, item.getQuality().toString());
        values.put(COLUMN_TYPE, item.getType().toString());
        values.put(COLUMN_PLAYER_ID, item.getPlayerId());

        // Ajout des données de l'objet utilisable
        if (item.getType() == ItemType.USABLE_ITEM) {
            UsableItem usableItem = (UsableItem) item;

            values.put(COLUMN_LOAD, usableItem.getLoad());
        }

        // Ajout des données de l'armure
        if (item.getType() == ItemType.ARMOR) {
            Armor armor = (Armor) item;

            values.put(COLUMN_IS_EQUIPPED, convertBooleanToInteger(armor.isEquiped()));
            values.put(COLUMN_SOAK, armor.getSoak());
            values.put(COLUMN_DEFENSE, armor.getDefense());
        }

        // Ajout des données de l'arme
        if (item.getType() == ItemType.WEAPON) {
            Weapon weapon = (Weapon) item;

            values.put(COLUMN_IS_EQUIPPED, convertBooleanToInteger(weapon.isEquiped()));
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

        // Ajout des valeurs des champs spécifiques aux différents types d'objets
        switch (type) {
            case ARMOR:
                Armor armor = new Armor();
                armor.setEquiped(convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_EQUIPPED))));
                armor.setSoak(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SOAK)));
                armor.setDefense(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DEFENSE)));
                dto = armor;
                break;
            case WEAPON:
                Weapon weapon = new Weapon();
                weapon.setEquiped(convertIntegerToBoolean(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_EQUIPPED))));
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

        // Ajout des champs communs à tous les types d'objets

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));

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
