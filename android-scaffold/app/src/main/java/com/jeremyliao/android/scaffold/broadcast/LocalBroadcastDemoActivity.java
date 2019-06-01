package com.jeremyliao.android.scaffold.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityLocalBroadcastDemoBinding;

public class LocalBroadcastDemoActivity extends AppCompatActivity {

    ActivityLocalBroadcastDemoBinding binding;
    LocalBroadcastManager broadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_local_broadcast_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(receiver, new IntentFilter("test_msg"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(receiver);
    }

    public void sendMessage() {
        Intent intent = new Intent("test_msg");
        intent.putExtra("data", "hello world");
        broadcastManager.sendBroadcast(intent);
    }

    public void sendInterProcessMessageByLocalBroadcast() {
        Intent intent = new Intent("test_inter_process_msg");
        intent.putExtra("data", "hello world");
        broadcastManager.sendBroadcast(intent);
    }

    public void sendInterProcessMessage() {
        Intent intent = new Intent("test_inter_process_msg");
        intent.putExtra("data", "hello world");
        sendBroadcast(intent);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("test_msg".equals(intent.getAction())) {
                String data = intent.getStringExtra("data");
                Toast.makeText(LocalBroadcastDemoActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
