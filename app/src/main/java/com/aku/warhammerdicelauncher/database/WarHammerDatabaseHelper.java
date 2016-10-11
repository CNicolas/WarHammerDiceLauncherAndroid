package com.aku.warhammerdicelauncher.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aku.warhammerdicelauncher.database.queries.ICharacteristicsQueries;
import com.aku.warhammerdicelauncher.database.queries.IHandQueries;
import com.aku.warhammerdicelauncher.database.queries.IPlayerQueries;
import com.aku.warhammerdicelauncher.database.queries.ISkillQueries;

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
        db.execSQL(IHandQueries.SQL_CREATE_ENTRIES);

        db.execSQL(ICharacteristicsQueries.SQL_CREATE_ENTRIES);
        db.execSQL(ISkillQueries.SQL_CREATE_ENTRIES);
        db.execSQL(IPlayerQueries.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(IHandQueries.SQL_DELETE_ENTRIES);

        db.execSQL(ICharacteristicsQueries.SQL_DELETE_ENTRIES);
        db.execSQL(ISkillQueries.SQL_DELETE_ENTRIES);
        db.execSQL(IPlayerQueries.SQL_DELETE_ENTRIES);

        onCreate(db);
    }
}
