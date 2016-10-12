package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.PlayerDto;

/**
 * Created by cnicolas on 12/10/2016.
 */

public class PlayerDao extends AbstractDao<PlayerDto> {
    private CharacteristicsDao characteristicsDao;

    public PlayerDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);

        tableName = IPlayerEntryConstants.TABLE_NAME;
        columnNameId = IPlayerEntryConstants.COLUMN_NAME_ID;

        characteristicsDao = new CharacteristicsDao(whdHelper);
    }

    //region Private Methods
    protected ContentValues contentValuesFromDto(PlayerDto playerDto) {
        ContentValues values = new ContentValues();

        values.put(IPlayerEntryConstants.COLUMN_NAME_NAME, playerDto.getName());
        values.put(IPlayerEntryConstants.COLUMN_NAME_RACE, playerDto.getRace());
        values.put(IPlayerEntryConstants.COLUMN_NAME_AGE, playerDto.getAge());
        values.put(IPlayerEntryConstants.COLUMN_NAME_SIZE, playerDto.getSize());
        values.put(IPlayerEntryConstants.COLUMN_NAME_DESCRIPTION, playerDto.getDescription());

        values.put(IPlayerEntryConstants.COLUMN_NAME_RANK, playerDto.getRank());
        values.put(IPlayerEntryConstants.COLUMN_NAME_EXPERIENCE, playerDto.getExperience());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MAX_EXPERIENCE, playerDto.getMax_experience());
        values.put(IPlayerEntryConstants.COLUMN_NAME_WOUNDS, playerDto.getWounds());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MAX_WOUNDS, playerDto.getMax_wounds());
        values.put(IPlayerEntryConstants.COLUMN_NAME_RECKLESS, playerDto.getReckless());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MAX_RECKLESS, playerDto.getMax_reckless());
        values.put(IPlayerEntryConstants.COLUMN_NAME_CONSERVATIVE, playerDto.getConservative());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MAX_CONSERVATIVE, playerDto.getMax_conservative());

        values.put(IPlayerEntryConstants.COLUMN_NAME_MONEY_BRASS, playerDto.getMoney_brass());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MONEY_SILVER, playerDto.getMoney_silver());
        values.put(IPlayerEntryConstants.COLUMN_NAME_MONEY_GOLD, playerDto.getMoney_gold());

        values.put(IPlayerEntryConstants.COLUMN_NAME_CHARACTERISTICS_ID, playerDto.getCharacteristics().getId());

        return values;
    }

    protected PlayerDto createDtoFromCursor(Cursor cursor) {
        PlayerDto dto = new PlayerDto();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnNameId)));

        dto.setName(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_NAME)));
        dto.setRace(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_RACE)));
        dto.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_AGE)));
        dto.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_SIZE)));
        dto.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_DESCRIPTION)));

        dto.setRank(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_RANK)));
        dto.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_EXPERIENCE)));
        dto.setMax_experience(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MAX_EXPERIENCE)));
        dto.setWounds(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_WOUNDS)));
        dto.setMax_wounds(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MAX_WOUNDS)));
        dto.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_RECKLESS)));
        dto.setMax_reckless(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MAX_RECKLESS)));
        dto.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_CONSERVATIVE)));
        dto.setMax_conservative(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MAX_CONSERVATIVE)));

        dto.setMoney_brass(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MONEY_BRASS)));
        dto.setMoney_silver(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MONEY_SILVER)));
        dto.setMoney_gold(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_MONEY_GOLD)));

        dto.setCharacteristics(characteristicsDao.findById(cursor.getInt(cursor.getColumnIndexOrThrow(IPlayerEntryConstants.COLUMN_NAME_CHARACTERISTICS_ID))));

        return dto;
    }
    //endregion
}
