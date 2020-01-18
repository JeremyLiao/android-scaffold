package com.jeremyliao.android.scaffold.news.modules.home;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.beans.gank.BaseBean;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsHomePresenter implements NewsHomeContract.Presenter {

    private final NewsHomeContract.View view;

    public NewsHomePresenter(NewsHomeContract.View demoView) {
        this.view = demoView;
    }

    @Override
    public NewsHomeContract.View getView() {
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCategories() {
        RetrofitService.getService(GankApi.class)
                .categories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Category>>>() {
                    @Override
                    public void accept(BaseBean<List<Category>> result) throws Exception {
                        getView().onLoadCategories(result.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
