package com.whfrp3.tools;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.whfrp3.model.player.Player;

/**
 * Application context provider.
 */
public class WHFRP3Application extends Application {
    /**
     * Application context.
     */
    private static Context context;
    private static Player mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        WHFRP3Application.context = getApplicationContext();
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

    /**
     * Getter of the application context.
     *
     * @return Application context.
     */
    public static Context getAppContext() {
        return WHFRP3Application.context;
    }
}
