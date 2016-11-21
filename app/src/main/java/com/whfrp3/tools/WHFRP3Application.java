package com.whfrp3.tools;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.whfrp3.database.Database;
import com.whfrp3.model.player.Player;

/**
 * Application context provider.
 */
public class WHFRP3Application extends Application {

    //region Properties

    /**
     * Application context.
     */
    private static Context mContext;

    /**
     * Database manager.
     */
    private static Database mDatabase;

    /**
     * Current player.
     */
    private static Player mPlayer;

    //endregion

    @Override
    public void onCreate() {
        super.onCreate();

        // Store application context
        WHFRP3Application.mContext = getApplicationContext();

        // Initialize mDatabase manager and open mDatabase connection
        mDatabase = new Database(mContext);
        mDatabase.open();
    }

    @Override
    public void onTerminate() {
        // Close mDatabase connection
        mDatabase.close();

        super.onTerminate();
    }

    //region Player
    public static Player getPlayer() {
        return mPlayer;
    }

    public static Player initEmptyPlayer() {
        mPlayer = new Player();
        return mPlayer;
    }

    public static void setPlayer(Player player) {
        mPlayer = player;
        Log.d("Player Context SET", mPlayer.toString());
    }
    //endregion

    public static String getResourceString(int resId) {
        return mContext.getString(resId);
    }

    /**
     * Getter of the application context.
     *
     * @return Application context.
     */
    public static Context getAppContext() {
        return WHFRP3Application.mContext;
    }

    /**
     * Getter of the mDatabase manager.
     *
     * @return Database manager.
     */
    public static Database getDatabase() {
        return mDatabase;
    }
}
