package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.whfrp3.database.WarHammerDatabaseHelper;
import com.whfrp3.database.entries.ICharacteristicsEntryConstants;
import com.whfrp3.model.player.Characteristics;

/**
 * The CharacteristicsDao.
 */
public class CharacteristicsDao extends AbstractDao<Characteristics> {
    public CharacteristicsDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = ICharacteristicsEntryConstants.TABLE_NAME;
        columnId = ICharacteristicsEntryConstants.COLUMN_ID;
    }

    //region Private Methods
    protected ContentValues contentValuesFromModel(Characteristics mmodel) {
        ContentValues values = new ContentValues();

        values.put(ICharacteristicsEntryConstants.COLUMN_ID, mmodel.getId());

        values.put(ICharacteristicsEntryConstants.COLUMN_STRENGTH, mmodel.getStrength());
        values.put(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS, mmodel.getToughness());
        values.put(ICharacteristicsEntryConstants.COLUMN_AGILITY, mmodel.getAgility());
        values.put(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE, mmodel.getIntelligence());
        values.put(ICharacteristicsEntryConstants.COLUMN_WILLPOWER, mmodel.getWillpower());
        values.put(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP, mmodel.getFellowship());

        values.put(ICharacteristicsEntryConstants.COLUMN_STRENGTH_FORTUNE, mmodel.getStrength_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS_FORTUNE, mmodel.getToughness_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_AGILITY_FORTUNE, mmodel.getAgility_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE_FORTUNE, mmodel.getIntelligence_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_WILLPOWER_FORTUNE, mmodel.getWillpower_fortune());
        values.put(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP_FORTUNE, mmodel.getFellowship_fortune());

        return values;
    }

    protected Characteristics createModelFromCursor(Cursor cursor) {
        Characteristics model = new Characteristics();

        model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(columnId)));

        model.setStrength(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_STRENGTH)));
        model.setToughness(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS)));
        model.setAgility(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_AGILITY)));
        model.setIntelligence(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE)));
        model.setWillpower(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_WILLPOWER)));
        model.setFellowship(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP)));

        model.setStrength_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_STRENGTH_FORTUNE)));
        model.setToughness_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_TOUGHNESS_FORTUNE)));
        model.setAgility_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_AGILITY_FORTUNE)));
        model.setIntelligence_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_INTELLIGENCE_FORTUNE)));
        model.setWillpower_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_WILLPOWER_FORTUNE)));
        model.setFellowship_fortune(cursor.getInt(cursor.getColumnIndexOrThrow(ICharacteristicsEntryConstants.COLUMN_FELLOWSHIP_FORTUNE)));

        return model;
    }
    //endregion
}
