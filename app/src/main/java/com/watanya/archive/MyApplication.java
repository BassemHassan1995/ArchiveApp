package com.watanya.archive;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context = null;
    private static MyApplication application = null;

    public static Context getContext() {
        return context;
    }

    static public MyApplication getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        application = this;
    }
}
