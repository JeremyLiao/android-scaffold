package com.jeremyliao.android.scaffold.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.jeremyliao.android.scaffold.others.sl.ILoaderDemo;
import com.jeremyliao.android.scaffold.services.loader.InterfaceService;

/**
 * Created by liaohailiang on 2019-06-01.
 */
public class LoaderDemoService extends Service {

    public static final String ACTION = "intent.action.loader";


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return InterfaceService.newService(ILoaderDemo.class, new ILoaderDemo() {
            @Override
            public int plus(int a, int b) {
                return a + b;
            }

            @Override
            public int minus(int a, int b) {
                return a - b;
            }

            @Override
            public int multi(int a, int b) {
                return a * b;
            }
        });
    }
}
