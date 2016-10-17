package com.aku.warhammerdicelauncher.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aku.warhammerdicelauncher.database.entries.ICharacteristicsEntryConstants;
import com.aku.warhammerdicelauncher.database.entries.IHandEntryConstants;
import com.aku.warhammerdicelauncher.database.entries.IPlayerEntryConstants;
import com.aku.warhammerdicelauncher.database.entries.ISkillEntryConstants;

/**
 * Created by cnicolas on 10/05/2016.
 */
public class WarHammerDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WHFRP3.db";

    public WarHammerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IHandEntryConstants.SQL_CREATE_ENTRIES);

        db.execSQL(ICharacteristicsEntryConstants.SQL_CREATE_ENTRIES);
        db.execSQL(ISkillEntryConstants.SQL_CREATE_ENTRIES);
        db.execSQL(IPlayerEntryConstants.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(IHandEntryConstants.SQL_DELETE_ENTRIES);

        db.execSQL(ICharacteristicsEntryConstants.SQL_DELETE_ENTRIES);
        db.execSQL(ISkillEntryConstants.SQL_DELETE_ENTRIES);
        db.execSQL(IPlayerEntryConstants.SQL_DELETE_ENTRIES);

        onCreate(db);
    }
}
