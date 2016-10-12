package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.ICharacteristicsEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;

/**
 * Created by cnicolas on 06/10/2016.
 */
public class CharacteristicsDao extends AbstractDao<CharacteristicsDto> {
    public CharacteristicsDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = ICharacteristicsEntryConstants.TABLE_NAME;
        columnNameId = ICharacteristicsEntryConstants.COLUMN_NAME_ID;
    }

    //region Private Methods
    protected ContentValues contentValuesFromDto(CharacteristicsDto dto) {
        ContentValues values = new ContentValues();

        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH, dto.getStrength());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS, dto.getToughness());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY, dto.getAgility());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE, dto.getIntelligence());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER, dto.getWillpower());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP, dto.getFellowship());

        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH_FORTUNE, dto.getStrength_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS_FORTUNE, dto.getToughness_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY_FORTUNE, dto.getAgility_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE_FORTUNE, dto.getIntelligence_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER_FORTUNE, dto.getWillpower_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP_FORTUNE, dto.getFellowship_fortune());

        return values;
    }

    protected CharacteristicsDto createDtoFromCursor(Cursor cursor) {
        CharacteristicsDto dto = new CharacteristicsDto();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnNameId)));

        dto.setStrength(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH)));
        dto.setToughness(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS)));
        dto.setAgility(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY)));
        dto.setIntelligence(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE)));
        dto.setWillpower(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER)));
        dto.setFellowship(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP)));

        dto.setStrength_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_STRENGTH_FORTUNE)));
        dto.setToughness_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_TOUGHNESS_FORTUNE)));
        dto.setAgility_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_AGILITY_FORTUNE)));
        dto.setIntelligence_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_INTELLIGENCE_FORTUNE)));
        dto.setWillpower_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_WILLPOWER_FORTUNE)));
        dto.setFellowship_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_NAME_FELLOWSHIP_FORTUNE)));

        return dto;
    }
    //endregion
}
