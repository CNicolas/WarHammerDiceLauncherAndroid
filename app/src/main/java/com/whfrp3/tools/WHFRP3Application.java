package com.whfrp3.tools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.whfrp3.database.Database;
import com.whfrp3.model.player.Player;
import com.whfrp3.tools.helpers.SkillHelper;
import com.whfrp3.tools.helpers.SpecialisationHelper;
import com.whfrp3.tools.helpers.TalentHelper;

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
     * Current Activity
     */
    private static Activity mActivity;

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

        // Load talents
        TalentHelper.getInstance().loadTalents();

        // Load skills
        SkillHelper.getInstance().loadSkills();

        // Load specializations
        SpecializationHelper.getInstance().loadSpecializations();
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

    public static Drawable getResourceDrawable(int resId) {
        return ContextCompat.getDrawable(mContext, resId);
    }

    public static int getResourceColor(int resId) {
        return ContextCompat.getColor(mContext, resId);
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

    /**
     * Getter of the current activity.
     *
     * @return Current activity.
     */
    public static Activity getActivity() {
        return WHFRP3Application.mActivity;
    }

    public static void setActivity(Activity activity) {
        mActivity = activity;
        Log.d("WHFRP3Application", "New Activity : " + activity.getClass().getName());
    }
}
