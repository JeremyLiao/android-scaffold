package com.jeremyliao.android.scaffold.databinding.adapter;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by liaohailiang on 2020-02-28.
 */
public class DataBindingAdapter {

    public static final int THROTTLE_TIME = 1000;

    @SuppressLint("CheckResult")
    @BindingAdapter("android:onClick")
    public static void onClick(final View view, final View.OnClickListener listener) {
        RxView.clicks(view)
                //防抖操作
                .throttleFirst(THROTTLE_TIME, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        listener.onClick(view);
                    }
                });
    }
}
