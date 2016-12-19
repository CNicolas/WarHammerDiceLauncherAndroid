package com.whfrp3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.whfrp3.database.dao.HandDao;
import com.whfrp3.database.dao.ItemDao;
import com.whfrp3.database.dao.PlayerCharacteristicDao;
import com.whfrp3.database.dao.PlayerDao;
import com.whfrp3.database.dao.PlayerSkillDao;
import com.whfrp3.database.dao.PlayerSpecializationDao;
import com.whfrp3.database.dao.PlayerTalentDao;
import com.whfrp3.database.entries.IHandEntryConstants;
import com.whfrp3.database.entries.IItemEntryConstants;
import com.whfrp3.database.entries.IPlayerCharacteristicEntryConstants;
import com.whfrp3.database.entries.IPlayerEntryConstants;
import com.whfrp3.database.entries.IPlayerSkillEntryConstants;
import com.whfrp3.database.entries.IPlayerSpecializationEntryConstants;
import com.whfrp3.database.entries.IPlayerTalentEntryConstants;

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
     * DAO of player characteristics.
     */
    private PlayerCharacteristicDao mPlayerCharacteristic;

    /**
     * DAO of player skills.
     */
    private PlayerSkillDao mPlayerSkillDao;

    /**
     * DAO of player specializations.
     */
    private PlayerSpecializationDao mPlayerSpecializationDao;

    /**
     * DAO of player search.
     */
    private PlayerTalentDao mPlayerTalentDao;

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
     * Getter of DAO of player characteristics.
     *
     * @return DAO of player characteristics.
     */
    public PlayerCharacteristicDao getmPlayerCharacteristic() {
        return mPlayerCharacteristic;
    }

    /**
     * Getter of DAO of player skills.
     *
     * @return DAO of player skills.
     */
    public PlayerSkillDao getPlayerSkillDao() {
        return mPlayerSkillDao;
    }

    /**
     * Getter of DAO of player specializations.
     *
     * @return DAO of player specializations.
     */
    public PlayerSpecializationDao getPlayerSpecializationDao() {
        return mPlayerSpecializationDao;
    }

    /**
     * Getter of DAO of player search.
     *
     * @return DAO of player search.
     */
    public PlayerTalentDao getPlayerTalentDao() {
        return mPlayerTalentDao;
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

        mPlayerCharacteristic = new PlayerCharacteristicDao(db);
        mPlayerSkillDao = new PlayerSkillDao(db);
        mPlayerSpecializationDao = new PlayerSpecializationDao(db);
        mPlayerTalentDao = new PlayerTalentDao(db);
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

            db.execSQL(IPlayerCharacteristicEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IPlayerSkillEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IPlayerSpecializationEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IPlayerTalentEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IPlayerEntryConstants.SQL_CREATE_ENTRIES);
            db.execSQL(IItemEntryConstants.SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(IHandEntryConstants.SQL_DELETE_ENTRIES);

            db.execSQL(IPlayerCharacteristicEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IPlayerSkillEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IPlayerSpecializationEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IPlayerTalentEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IPlayerEntryConstants.SQL_DELETE_ENTRIES);
            db.execSQL(IItemEntryConstants.SQL_DELETE_ENTRIES);

            onCreate(db);
        }
    }

    //endregion
}
