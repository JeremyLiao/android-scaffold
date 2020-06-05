package com.jeremyliao.android.scaffold.others.sl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityLoaderDemoBinding;
import com.jeremyliao.android.scaffold.services.LoaderDemoService;
import com.jeremyliao.android.scaffold.services.loader.InterfaceLoader;
import com.jeremyliao.android.scaffold.utils.ToastUtils;

public class LoaderDemoActivity extends AppCompatActivity {

    ActivityLoaderDemoBinding binding;
    IBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        bindService();
    }

    public void remotePlus() {
        int plusResult = InterfaceLoader.getService(ILoaderDemo.class, binder)
                .plus(10, 20);
        ToastUtils.showShort("result: " + plusResult);
    }

    public void remoteMinus() {
        int plusResult = InterfaceLoader.getService(ILoaderDemo.class, binder)
                .minus(10, 20);
        ToastUtils.showShort("result: " + plusResult);
    }

    public void remoteMulti() {
        int plusResult = InterfaceLoader.getService(ILoaderDemo.class, binder)
                .multi(10, 20);
        ToastUtils.showShort("result: " + plusResult);
    }

    private void bindService() {
        Intent intent = new Intent(LoaderDemoService.ACTION);
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
