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
import com.jeremyliao.android.scaffold.services.fetcher.ServiceFetcher;
import com.jeremyliao.android.scaffold.services.loader.InterfaceLoader;
import com.jeremyliao.android.scaffold.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoaderDemoActivity extends AppCompatActivity {

    ActivityLoaderDemoBinding binding;
    ServiceFetcher serviceFetcher;
    final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        Intent intent = new Intent(LoaderDemoService.ACTION);
        intent.setPackage(getPackageName());
        serviceFetcher = new ServiceFetcher(this, intent);
        serviceFetcher.bindService();
        remotePlus();
        remoteMinus();
        remoteMulti();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serviceFetcher.unbindService();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void remotePlus() {
        disposable.add(serviceFetcher.getService()
                .map(new Function<IBinder, Integer>() {
                    @Override
                    public Integer apply(IBinder binder) throws Exception {
                        return InterfaceLoader.getService(ILoaderDemo.class, binder)
                                .plus(10, 20);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer result) throws Exception {
                        return "result: " + result;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        binding.tvContent1.setText(result);
                        ToastUtils.showShort(result);
                    }
                }));
    }

    public void remoteMinus() {
        disposable.add(serviceFetcher.getService()
                .map(new Function<IBinder, Integer>() {
                    @Override
                    public Integer apply(IBinder binder) throws Exception {
                        return InterfaceLoader.getService(ILoaderDemo.class, binder)
                                .minus(10, 20);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer result) throws Exception {
                        return "result: " + result;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        binding.tvContent2.setText(result);
                        ToastUtils.showShort(result);
                    }
                }));
    }

    public void remoteMulti() {
        disposable.add(serviceFetcher.getService()
                .map(new Function<IBinder, Integer>() {
                    @Override
                    public Integer apply(IBinder binder) throws Exception {
                        return InterfaceLoader.getService(ILoaderDemo.class, binder)
                                .multi(10, 20);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer result) throws Exception {
                        return "result: " + result;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        binding.tvContent3.setText(result);
                        ToastUtils.showShort(result);
                    }
                }));
    }
}
