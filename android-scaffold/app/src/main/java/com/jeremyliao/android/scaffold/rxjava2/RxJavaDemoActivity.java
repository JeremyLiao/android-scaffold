package com.jeremyliao.android.scaffold.rxjava2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityRxjavaDemoBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemoActivity extends AppCompatActivity {

    ActivityRxjavaDemoBinding binding;
    CallableObservable callableObservable = new CallableObservable();
    protected final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rxjava_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        disposable.add(callableObservable
                .throttleLast(5000, TimeUnit.MILLISECONDS)
                .flatMap(new Function<Object, ObservableSource<Object>>() {
                    @Override
                    public ObservableSource<Object> apply(Object o) throws Exception {
                        //随机生成一个时间
                        int delayTime = (int) (1 + Math.random() * 1000);
                        return Observable.just(o).delay(delayTime, TimeUnit.MILLISECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(RxJavaDemoActivity.this, "method called", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void testRetrofit() {
        RxJava2Demo.testRetrofit();
    }

    public void threadChange1() {
        RxJava2Demo.threadChange1();
    }

    public void testThrottleLast() {
        callableObservable.call();
    }

}
