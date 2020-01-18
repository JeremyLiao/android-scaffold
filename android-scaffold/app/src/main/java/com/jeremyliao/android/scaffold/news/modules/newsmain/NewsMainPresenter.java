package com.jeremyliao.android.scaffold.news.modules.newsmain;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.beans.gank.BaseBean;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsMainPresenter implements NewsMainContract.Presenter {

    private final NewsMainContract.View view;

    public NewsMainPresenter(NewsMainContract.View demoView) {
        this.view = demoView;
    }

    @Override
    public NewsMainContract.View getView() {
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSubCategories(String categoryId) {
        RetrofitService.getService(GankApi.class)
                .subCategories(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<SubCategory>>>() {
                    @Override
                    public void accept(BaseBean<List<SubCategory>> result) throws Exception {
                        getView().onLoadCategories(result.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
