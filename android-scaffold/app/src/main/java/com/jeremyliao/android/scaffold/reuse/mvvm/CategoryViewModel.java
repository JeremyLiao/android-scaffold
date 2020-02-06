package com.jeremyliao.android.scaffold.reuse.mvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.api.RxTransformers;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by liaohailiang on 2019-04-20.
 */
public class CategoryViewModel extends ViewModel {

    public final MutableLiveData<List<Category>> categories = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public void getCategories() {
        RetrofitService.getService(GankApi.class)
                .categories()
                .compose(RxTransformers.<List<Category>>businessErrorHandler())
                .compose(RxTransformers.<List<Category>>applySchedulers())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> result) throws Exception {
                        categories.setValue(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}
