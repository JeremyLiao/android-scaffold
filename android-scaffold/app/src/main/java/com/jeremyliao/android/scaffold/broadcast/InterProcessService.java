package com.jeremyliao.android.scaffold.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by liaohailiang on 2019-06-01.
 */
public class InterProcessService extends Service {

    LocalBroadcastManager broadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(receiver, new IntentFilter("test_inter_process_msg"));
        registerReceiver(receiver, new IntentFilter("test_inter_process_msg"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(receiver);
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("test_inter_process_msg".equals(intent.getAction())) {
                String data = intent.getStringExtra("data");
                Toast.makeText(InterProcessService.this, data, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
