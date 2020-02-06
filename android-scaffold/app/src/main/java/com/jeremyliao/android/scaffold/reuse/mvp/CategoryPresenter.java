package com.jeremyliao.android.scaffold.reuse.mvp;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.api.RxTransformers;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class CategoryPresenter implements CategoryContract.Presenter {

    private final CategoryContract.View view;

    public CategoryPresenter(CategoryContract.View demoView) {
        this.view = demoView;
    }

    @Override
    public CategoryContract.View getView() {
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCategories() {
        RetrofitService.getService(GankApi.class)
                .categories()
                .compose(RxTransformers.<List<Category>>businessErrorHandler())
                .compose(RxTransformers.<List<Category>>applySchedulers())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> result) throws Exception {
                        getView().onLoadCategories(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
