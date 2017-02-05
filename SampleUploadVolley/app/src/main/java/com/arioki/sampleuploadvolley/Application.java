package com.arioki.sampleuploadvolley;

import android.app.Application;
import android.content.Context;

/**
 * Created by -Andevindo- on 10/8/2015.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return myApplication.getApplicationContext();
    }
}