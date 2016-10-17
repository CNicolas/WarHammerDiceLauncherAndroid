package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;
import com.aku.warhammerdicelauncher.model.player.Characteristics;
import com.aku.warhammerdicelauncher.model.player.Player;
import com.aku.warhammerdicelauncher.model.player.Skill;

import java.util.List;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class PlayerDao extends AbstractDao<Player> {
    private final CharacteristicsDao characteristicsDao;
    private final SkillDao skillDao;

    public PlayerDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);

        tableName = IPlayerEntryConstants.TABLE_NAME;
        columnNameId = IPlayerEntryConstants.COLUMN_ID;

        characteristicsDao = new CharacteristicsDao(whdHelper);
        skillDao = new SkillDao(whdHelper);
    }

    //region Find
    public List<String> findAllNames() {
        return findAllByField(IPlayerEntryConstants.COLUMN_NAME);
    }
    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromDto(Player player) {
        ContentValues values = new ContentValues();

        values.put(IPlayerEntryConstants.COLUMN_NAME, player.getName());
        values.put(IPlayerEntryConstants.COLUMN_RACE, player.getRace());
        values.put(IPlayerEntryConstants.COLUMN_AGE, player.getAge());
        values.put(IPlayerEntryConstants.COLUMN_SIZE, player.getSize());
        values.put(IPlayerEntryConstants.COLUMN_DESCRIPTION, player.getDescription());

        values.put(IPlayerEntryConstants.COLUMN_RANK, player.getRank());
        values.put(IPlayerEntryConstants.COLUMN_EXPERIENCE, player.getExperience());
        values.put(IPlayerEntryConstants.COLUMN_MAX_EXPERIENCE, player.getMax_experience());
        values.put(IPlayerEntryConstants.COLUMN_WOUNDS, player.getWounds());
        values.put(IPlayerEntryConstants.COLUMN_MAX_WOUNDS, player.getMax_wounds());
        values.put(IPlayerEntryConstants.COLUMN_RECKLESS, player.getReckless());
        values.put(IPlayerEntryConstants.COLUMN_MAX_RECKLESS, player.getMax_reckless());
        values.put(IPlayerEntryConstants.COLUMN_CONSERVATIVE, player.getConservative());
        values.put(IPlayerEntryConstants.COLUMN_MAX_CONSERVATIVE, player.getMax_conservative());

        values.put(IPlayerEntryConstants.COLUMN_MONEY_BRASS, player.getMoney_brass());
        values.put(IPlayerEntryConstants.COLUMN_MONEY_SILVER, player.getMoney_silver());
        values.put(IPlayerEntryConstants.COLUMN_MONEY_GOLD, player.getMoney_gold());

        values.put(IPlayerEntryConstants.COLUMN_CHARACTERISTICS_ID, player.getCharacteristics().getId());

        return values;
    }

    protected Player createDtoFromCursor(Cursor cursor) {
        Player dto = new Player();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnNameId)));

        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME)));
        dto.setRace(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_RACE)));
        dto.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_AGE)));
        dto.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_SIZE)));
        dto.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_DESCRIPTION)));

        dto.setRank(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_RANK)));
        dto.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_EXPERIENCE)));
        dto.setMax_experience(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MAX_EXPERIENCE)));
        dto.setWounds(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_WOUNDS)));
        dto.setMax_wounds(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MAX_WOUNDS)));
        dto.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_RECKLESS)));
        dto.setMax_reckless(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MAX_RECKLESS)));
        dto.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_CONSERVATIVE)));
        dto.setMax_conservative(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MAX_CONSERVATIVE)));

        dto.setMoney_brass(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MONEY_BRASS)));
        dto.setMoney_silver(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MONEY_SILVER)));
        dto.setMoney_gold(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_MONEY_GOLD)));

        Characteristics characteristics = characteristicsDao.findById(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_CHARACTERISTICS_ID)));
        dto.setCharacteristics(characteristics);

        List<Skill> skills = skillDao.findAllByPlayer(dto);
        dto.setSkills(skills);

        return dto;
    }
    //endregion
}
