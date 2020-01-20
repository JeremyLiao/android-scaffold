package com.jeremyliao.android.scaffold.news.modules.newsmain;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.api.RxTransformers;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;

import java.util.List;

import io.reactivex.functions.Consumer;

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
                .compose(RxTransformers.<List<SubCategory>>businessErrorHandler())
                .compose(RxTransformers.<List<SubCategory>>applySchedulers())
                .subscribe(new Consumer<List<SubCategory>>() {
                    @Override
                    public void accept(List<SubCategory> subCategories) throws Exception {
                        getView().onLoadCategories(subCategories);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
