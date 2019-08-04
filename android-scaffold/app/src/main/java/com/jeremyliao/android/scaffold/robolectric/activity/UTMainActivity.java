package com.jeremyliao.android.scaffold.robolectric.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.retrofit.api.WanAndroidApi;
import com.jeremyliao.android.scaffold.retrofit.bean.ChaptersResp;
import com.jeremyliao.android.scaffold.retrofit.common.Hosts;
import com.jeremyliao.android.scaffold.retrofit.factory.RetrofitFactory;
import com.jeremyliao.android.scaffold.robolectric.helper.InfoHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2019-07-29.
 */
public class UTMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ut_main_activity);
    }

    public void login(View v) {
        startActivity(new Intent(UTMainActivity.this, LoginActivity.class));
    }

    public void showToast(View v) {
        Toast.makeText(this, InfoHelper.getToastInfo(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("CheckResult")
    public void getChapters(View v) {
        WanAndroidApi api = RetrofitFactory.get(Hosts.HOST_WANANDROID).create(WanAndroidApi.class);
        api.chapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChaptersResp>() {
                    @Override
                    public void accept(ChaptersResp chaptersResp) throws Exception {
                        Toast.makeText(UTMainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(UTMainActivity.this, "onFailure: " + throwable, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
