package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;
import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.Skill;
import com.aku.warhammerdicelauncher.model.player.inventory.Item;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class PlayerDao extends AbstractDao<Player> implements IPlayerEntryConstants {
    private final CharacteristicsDao characteristicsDao;
    private final SkillDao skillDao;
    private final ItemDao itemDao;

    public PlayerDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);

        tableName = TABLE_NAME;
        columnId = COLUMN_ID;

        characteristicsDao = new CharacteristicsDao(whdHelper);
        skillDao = new SkillDao(whdHelper);
        itemDao = new ItemDao(whdHelper);
    }

    //region Find
    public List<String> findAllNames() {
        return findAllValuesOfColumn(COLUMN_NAME);
    }

    public Player findByName(String name) {
        return findByStringInColumn(name, COLUMN_NAME);
    }
    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromModel(Player player) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, player.getName());
        values.put(COLUMN_RACE, player.getRace());
        values.put(COLUMN_AGE, player.getAge());
        values.put(COLUMN_SIZE, player.getSize());
        values.put(COLUMN_DESCRIPTION, player.getDescription());

        values.put(COLUMN_CAREER, player.getCareer());
        values.put(COLUMN_RANK, player.getRank());
        values.put(COLUMN_EXPERIENCE, player.getExperience());
        values.put(COLUMN_MAX_EXPERIENCE, player.getMax_experience());
        values.put(COLUMN_WOUNDS, player.getWounds());
        values.put(COLUMN_MAX_WOUNDS, player.getMax_wounds());
        values.put(COLUMN_CORRUPTION, player.getCorruption());
        values.put(COLUMN_MAX_CORRUPTION, player.getMax_corruption());
        values.put(COLUMN_RECKLESS, player.getReckless());
        values.put(COLUMN_MAX_RECKLESS, player.getMax_reckless());
        values.put(COLUMN_CONSERVATIVE, player.getConservative());
        values.put(COLUMN_MAX_CONSERVATIVE, player.getMax_conservative());

        values.put(COLUMN_MONEY_BRASS, player.getMoney_brass());
        values.put(COLUMN_MONEY_SILVER, player.getMoney_silver());
        values.put(COLUMN_MONEY_GOLD, player.getMoney_gold());

        values.put(COLUMN_CHARACTERISTICS_ID, player.getCharacteristics().getId());

        return values;
    }

    protected Player createModelFromCursor(Cursor cursor) {
        Player model = new Player();

        model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));

        model.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
        model.setRace(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RACE)));
        model.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE)));
        model.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SIZE)));
        model.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));

        model.setCareer(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAREER)));
        model.setRank(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RANK)));
        model.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCE)));
        model.setMax_experience(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_EXPERIENCE)));
        model.setWounds(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WOUNDS)));
        model.setMax_wounds(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_WOUNDS)));
        model.setCorruption(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CORRUPTION)));
        model.setMax_corruption(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_CORRUPTION)));
        model.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RECKLESS)));
        model.setMax_reckless(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_RECKLESS)));
        model.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CONSERVATIVE)));
        model.setMax_conservative(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MAX_CONSERVATIVE)));

        model.setMoney_brass(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_BRASS)));
        model.setMoney_silver(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_SILVER)));
        model.setMoney_gold(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MONEY_GOLD)));

        Characteristics characteristics = characteristicsDao.findById(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHARACTERISTICS_ID)));
        model.setCharacteristics(characteristics);

        List<Skill> skills = skillDao.findAllByPlayer(model);
        model.setSkills(skills);

        // Ajout des objets de l'inventaire du joueur
        List<Item> items = itemDao.findAllByPlayer(model);
        model.setInventory(items);

        return model;
    }
    //endregion
}
