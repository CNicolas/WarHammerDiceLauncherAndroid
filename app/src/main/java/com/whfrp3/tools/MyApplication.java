package com.whfrp3.tools;

import android.app.Application;
import android.content.Context;

/**
 * Application context provider.
 */
public class MyApplication extends Application {
    /**
     * Application context.
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    /**
     * Getter of the application context.
     *
     * @return Application context.
     */
    public static Context getAppContext() {
        return MyApplication.context;
    }
}
