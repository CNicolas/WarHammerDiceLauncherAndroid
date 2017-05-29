package com.whfrp3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.whfrp3.database.dao.HandDao;
import com.whfrp3.database.dao.ItemDao;
import com.whfrp3.database.dao.PlayerDao;
import com.whfrp3.database.entries.IHandEntryConstants;
import com.whfrp3.database.entries.IItemEntryConstants;
import com.whfrp3.database.entries.IPlayerEntryConstants;

/**
 * Application mDatabase manager.
 */
public class Database {
    //region Constants

    /**
     * Database name.
     */
    private static final String DATABASE_NAME = "WHFRP3.db";

    /**
     * Current mDatabase version.
     */
    private static final int DATABASE_VERSION = 1;

    //endregion

    //region Properties

    /**
     * Application context.
     */
    private final Context mContext;

    /**
     * Database helper.
     */
    private DatabaseHelper mDbHelper;

    //endregion

    //region DAOs

    /**
     * DAO of players.
     */
    private PlayerDao mPlayerDao;

    /**
     * DAO of items.
     */
    private ItemDao mItemDao;

    /**
     * DAO of hands.
     */
    private HandDao mHandDao;

    /**
     * Getter of DAO of players.
     *
     * @return DAO of players.
     */
    public PlayerDao getPlayerDao() {
        return mPlayerDao;
    }

    /**
     * Getter of DAO of items.
     *
     * @return DAO of items.
     */
    public ItemDao getItemDao() {
        return mItemDao;
    }

    /**
     * Getter of DAO of hands.
     *
     * @return DAO of hands.
     */
    public HandDao getHandDao() {
        return mHandDao;
    }

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param context Application context.
     */
    public Database(Context context) {
        mContext = context;
    }

    //endregion

    //region Public methods

    /**
     * Open the mDatabase connection and initialize all DAOs.
     */
    public void open() {
        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        mItemDao = new ItemDao(db);
        mHandDao = new HandDao(db);
        mPlayerDao = new PlayerDao(db);
    }

    /**
     * Close the mDatabase connection.
     */
    public void close() {
        mDbHelper.close();
    }

    //endregion

    //region WHFRP3 mDatabase helper

    /**
     * Application mDatabase helper.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        /**
         * Constructor.
         *
         * @param context Application's context.
         */
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IHandEntryConstants.SQL_CREATE_ENTRIES);

            db.execSQL(IPlayerEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IItemEntryConstants.SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(IHandEntryConstants.SQL_DELETE_ENTRIES);

            db.execSQL(IPlayerEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IItemEntryConstants.SQL_DELETE_ENTRIES);

            onCreate(db);
        }
    }

    //endregion
}
