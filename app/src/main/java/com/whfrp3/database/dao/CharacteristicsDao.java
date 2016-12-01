package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.ICharacteristicsEntryConstants;
import com.whfrp3.database.entries.IEntryConstants;
import com.whfrp3.model.player.Characteristics;

/**
 * DAO of characteristics.
 */
public class CharacteristicsDao extends AbstractDao<Characteristics> implements ICharacteristicsEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public CharacteristicsDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(Characteristics model) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, model.getId());

        values.put(COLUMN_STRENGTH, model.getStrength());
        values.put(COLUMN_TOUGHNESS, model.getToughness());
        values.put(COLUMN_AGILITY, model.getAgility());
        values.put(COLUMN_INTELLIGENCE, model.getIntelligence());
        values.put(COLUMN_WILLPOWER, model.getWillpower());
        values.put(COLUMN_FELLOWSHIP, model.getFellowship());

        values.put(COLUMN_STRENGTH_FORTUNE, model.getStrengthFortune());
        values.put(COLUMN_TOUGHNESS_FORTUNE, model.getToughnessFortune());
        values.put(COLUMN_AGILITY_FORTUNE, model.getAgilityFortune());
        values.put(COLUMN_INTELLIGENCE_FORTUNE, model.getIntelligenceFortune());
        values.put(COLUMN_WILLPOWER_FORTUNE, model.getWillpowerFortune());
        values.put(COLUMN_FELLOWSHIP_FORTUNE, model.getFellowshipFortune());

        return values;
    }

    @Override
    protected Characteristics createModelFromCursor(Cursor cursor) {
        Characteristics model = new Characteristics();

        model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IEntryConstants.COLUMN_ID)));

        model.setStrength(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STRENGTH)));
        model.setToughness(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TOUGHNESS)));
        model.setAgility(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGILITY)));
        model.setIntelligence(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELLIGENCE)));
        model.setWillpower(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WILLPOWER)));
        model.setFellowship(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FELLOWSHIP)));

        model.setStrengthFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STRENGTH_FORTUNE)));
        model.setToughnessFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TOUGHNESS_FORTUNE)));
        model.setAgilityFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGILITY_FORTUNE)));
        model.setIntelligenceFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_INTELLIGENCE_FORTUNE)));
        model.setWillpowerFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WILLPOWER_FORTUNE)));
        model.setFellowshipFortune(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FELLOWSHIP_FORTUNE)));

        return model;
    }

    //endregion
}
