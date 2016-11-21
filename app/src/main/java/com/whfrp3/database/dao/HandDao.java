package com.whfrp3.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.whfrp3.database.entries.IHandEntryConstants;
import com.whfrp3.model.player.Hand;

import java.util.List;

/**
 * DAO of hands.
 */
public class HandDao extends AbstractDao<Hand> implements IHandEntryConstants {

    //region Constructor

    /**
     * Constructor.
     *
     * @param database Database connection.
     */
    public HandDao(SQLiteDatabase database) {
        super(database, TABLE_NAME);
    }

    //endregion

    //region Find

    /**
     * Find all hands titles.
     *
     * @return Hands titles.
     */
    public List<String> findAllTitles() {
        return findAllValuesOfColumn(IHandEntryConstants.COLUMN_TITLE);
    }

    /**
     * Find a hand by its title.
     *
     * @param title Hand title.
     * @return Hand found.
     */
    public Hand findByTitle(String title) throws Resources.NotFoundException {
        return findByColumn(IHandEntryConstants.COLUMN_TITLE, title);
    }

    //endregion

    //region Protected methods

    @Override
    protected ContentValues contentValuesFromModel(Hand hand) {
        ContentValues values = new ContentValues();

        values.put(IHandEntryConstants.COLUMN_TITLE, hand.getTitle());
        values.put(IHandEntryConstants.COLUMN_CHARACTERISTIC, hand.getCharacteristic());
        values.put(IHandEntryConstants.COLUMN_RECKLESS, hand.getReckless());
        values.put(IHandEntryConstants.COLUMN_CONSERVATIVE, hand.getConservative());
        values.put(IHandEntryConstants.COLUMN_EXPERTISE, hand.getExpertise());
        values.put(IHandEntryConstants.COLUMN_FORTUNE, hand.getFortune());
        values.put(IHandEntryConstants.COLUMN_MISFORTUNE, hand.getMisfortune());
        values.put(IHandEntryConstants.COLUMN_CHALLENGE, hand.getChallenge());

        return values;
    }

    @Override
    protected Hand createModelFromCursor(Cursor cursor) {
        Hand dto = new Hand();

        dto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_ID)));

        dto.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_TITLE)));
        dto.setCharacteristic(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_CHARACTERISTIC)));
        dto.setReckless(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_RECKLESS)));
        dto.setConservative(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_CONSERVATIVE)));
        dto.setExpertise(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_EXPERTISE)));
        dto.setFortune(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_FORTUNE)));
        dto.setMisfortune(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_MISFORTUNE)));
        dto.setChallenge(cursor.getInt(cursor.getColumnIndexOrThrow(IHandEntryConstants.COLUMN_CHALLENGE)));

        return dto;
    }

    //endregion
}
