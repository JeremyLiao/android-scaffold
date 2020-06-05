package com.jeremyliao.android.scaffold.others.ashmem;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityAshmemBinding;
import com.jeremyliao.android.scaffold.manager.SharedMemoryManager;
import com.jeremyliao.android.scaffold.services.AshmemService;
import com.jeremyliao.android.scaffold.services.loader.InterfaceLoader;

public class AshmemActivity extends AppCompatActivity {

    ActivityAshmemBinding binding;
    IBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ashmem);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        bindService();
    }

    public void startNewPage() {
        ParcelFileDescriptor descriptor = SharedMemoryManager.getManager().put("hello world");
        put(descriptor);
        Intent intent = new Intent(this, AshmemTargetActivity.class);
        startActivity(intent);
    }

    private void put(ParcelFileDescriptor descriptor) {
        InterfaceLoader.getService(IFileDescriptor.class, binder)
                .put(descriptor);
    }

    private void bindService() {
        Intent intent = new Intent(AshmemService.ACTION);
        intent.setPackage(getPackageName());
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
