package com.jeremyliao.android.scaffold.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;

import com.jeremyliao.android.scaffold.others.ashmem.IFileDescriptor;
import com.jeremyliao.android.scaffold.services.loader.InterfaceService;

/**
 * Created by liaohailiang on 2019-06-01.
 */
public class AshmemService extends Service {

    public static final String ACTION = "intent.action.ashmem";

    ParcelFileDescriptor parcelFileDescriptor;

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
        return InterfaceService.newService(IFileDescriptor.class, fdService);
    }

    IFileDescriptor fdService = new IFileDescriptor() {
        @Override
        public void put(ParcelFileDescriptor descriptor) {
            parcelFileDescriptor = descriptor;
        }

        @Override
        public ParcelFileDescriptor get() {
            return parcelFileDescriptor;
        }
    };
}
