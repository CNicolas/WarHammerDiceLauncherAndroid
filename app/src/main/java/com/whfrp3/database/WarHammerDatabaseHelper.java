package com.whfrp3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.whfrp3.database.entries.ICharacteristicsEntryConstants;
import com.whfrp3.database.entries.IHandEntryConstants;
import com.whfrp3.database.entries.IItemEntryConstants;
import com.whfrp3.database.entries.IPlayerEntryConstants;
import com.whfrp3.database.entries.ISkillEntryConstants;

/**
 * The Database Helper.
 */
public class WarHammerDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WHFRP3.db";

    public WarHammerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IHandEntryConstants.SQL_CREATE_ENTRIES);

        db.execSQL(ICharacteristicsEntryConstants.SQL_CREATE_ENTRIES);
        db.execSQL(ISkillEntryConstants.SQL_CREATE_ENTRIES);
        db.execSQL(IPlayerEntryConstants.SQL_CREATE_ENTRIES);
        db.execSQL(IItemEntryConstants.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(IHandEntryConstants.SQL_DELETE_ENTRIES);

        db.execSQL(ICharacteristicsEntryConstants.SQL_DELETE_ENTRIES);
        db.execSQL(ISkillEntryConstants.SQL_DELETE_ENTRIES);
        db.execSQL(IPlayerEntryConstants.SQL_DELETE_ENTRIES);
        db.execSQL(IItemEntryConstants.SQL_DELETE_ENTRIES);

        onCreate(db);
    }
}