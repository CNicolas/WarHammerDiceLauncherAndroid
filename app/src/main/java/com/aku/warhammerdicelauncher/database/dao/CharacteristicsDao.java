package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.ICharacteristicsEntryConstants;
import com.aku.warhammerdicelauncher.model.dto.CharacteristicsDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnicolas on 06/10/2016.
 */
public class CharacteristicsDao extends AbstractDao<CharacteristicsDto> {
    public CharacteristicsDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
    }

    @Override
    public List<CharacteristicsDto> findAll() {
        List<CharacteristicsDto> res = new ArrayList<>();
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(ICharacteristicsEntryConstants.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CharacteristicsDto dto = createDtoFromCursor(cursor);
                res.add(dto);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return res;
    }

    @Override
    public long insert(CharacteristicsDto dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();

        ContentValues values = contentValuesFromDto(dto);

        long res = db.insert(ICharacteristicsEntryConstants.TABLE_NAME, null, values);
        return res;
    }

    //region Delete
    @Override
    public long delete(CharacteristicsDto dto) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        String[] filters = {String.valueOf(dto.getId())};

        long res = db.delete(ICharacteristicsEntryConstants.TABLE_NAME, String.format("%s = ?", ICharacteristicsEntryConstants.COLUMN_NAME_ID), filters);
        return res;
    }
    //endregion

    //region Private Methods
    private ContentValues contentValuesFromDto(CharacteristicsDto dto) {
        ContentValues values = new ContentValues();

        values.put(ICharacteristicsEntryConstants.COLUMN_NAME_ID, dto.getId());

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

    private CharacteristicsDto createDtoFromCursor(Cursor cursor) {
        CharacteristicsDto dto = new CharacteristicsDto();

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
