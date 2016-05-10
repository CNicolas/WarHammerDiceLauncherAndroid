package com.aku.warhammerdicelauncher.model.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aku.warhammerdicelauncher.model.database.DicesHandContract.HandEntry;

/**
 * Created by cnicolas on 10/05/2016.
 */
public class WarHammerDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Hand.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HandEntry.TABLE_NAME + " (" +
                    HandEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CHARACTERISTIC + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_RECKLESS + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CONSERVATIVE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_EXPERTISE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_FORTUNE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_MISFORTUNE + INTEGER_TYPE + COMMA_SEP +
                    HandEntry.COLUMN_NAME_CHALLENGE + INTEGER_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HandEntry.TABLE_NAME;

    public WarHammerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
