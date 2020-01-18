package com.jeremyliao.android.scaffold.news.modules.newslist;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.beans.gank.BaseBean;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsListPresenter implements NewsListContract.Presenter {

    private static final int DEFAULT_PAGE_COUNT = 10;
    private final NewsListContract.View view;
    private final String categoryId;

    public NewsListPresenter(NewsListContract.View view, String categoryId) {
        this.view = view;
        this.categoryId = categoryId;
    }

    @Override
    public NewsListContract.View getView() {
        return view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getContent(boolean isRefresh) {
        RetrofitService.getService(GankApi.class)
                .getContent(categoryId, DEFAULT_PAGE_COUNT, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Content>>>() {
                    @Override
                    public void accept(BaseBean<List<Content>> listBaseBean) throws Exception {
                        getView().onLoadContent(listBaseBean.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getMoreContent() {

    }
}
