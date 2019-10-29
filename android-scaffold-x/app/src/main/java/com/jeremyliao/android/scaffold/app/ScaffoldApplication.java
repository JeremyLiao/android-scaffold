package com.jeremyliao.android.scaffold.app;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by liaohailiang on 2019-10-29.
 */
public class ScaffoldApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, false);
    }
}
