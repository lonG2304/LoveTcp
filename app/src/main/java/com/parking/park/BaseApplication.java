package com.parking.park;

import android.app.Application;

public class BaseApplication extends Application {

    public static BaseApplication context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
