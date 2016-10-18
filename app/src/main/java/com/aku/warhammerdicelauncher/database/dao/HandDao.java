package com.aku.warhammerdicelauncher.database.dao;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aku.warhammerdicelauncher.database.WarHammerDatabaseHelper;
import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;
import com.aku.warhammerdicelauncher.model.player.Hand;

import java.util.List;

/**
 * Created by cnicolas on 11/05/2016.
 */
public class HandDao extends AbstractDao<Hand> {
    public HandDao(WarHammerDatabaseHelper whdHelper) {
        super(whdHelper);
        tableName = IHandEntryConstants.TABLE_NAME;
        columnId = IHandEntryConstants.COLUMN_ID;
    }

    //region Find
    public List<String> findAllTitles() {
        return findAllValuesOfColumn(IHandEntryConstants.COLUMN_TITLE);
    }

    public Hand findByTitle(String title) throws Resources.NotFoundException {
        String[] selectionArgs = {title};
        SQLiteDatabase db = whdHelper.getReadableDatabase();

        Cursor cursor = db.query(tableName, null, IHandEntryConstants.COLUMN_TITLE + "=?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            Hand dto = createDtoFromCursor(cursor);
            cursor.close();
            return dto;
        } else {
            cursor.close();
            throw new Resources.NotFoundException();
        }
    }
    //endregion

    //region Update
    public long update(Hand hand, String title) {
        SQLiteDatabase db = whdHelper.getWritableDatabase();
        ContentValues values = contentValuesFromDto(hand);
        String[] filters = {title};

        long res = db.update(tableName, values, String.format("%s = ?", IHandEntryConstants.COLUMN_TITLE), filters);
        return res;
    }
    //endregion

    //region Private Methods
    protected ContentValues contentValuesFromDto(Hand hand) {
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

    protected Hand createDtoFromCursor(Cursor cursor) {
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
