package com.jeremyliao.android.scaffold.news.modules.newslist;

import android.annotation.SuppressLint;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.api.RxTransformers;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsListPresenter implements NewsListContract.Presenter {

    private static final int DEFAULT_PAGE_COUNT = 10;
    private final NewsListContract.View view;
    private final String categoryId;
    private int loadMorePageIndex = 1;

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
    public void getContent(final boolean isRefresh) {
        RetrofitService.getService(GankApi.class)
                .getContent(categoryId, DEFAULT_PAGE_COUNT, 1)
                .compose(RxTransformers.<List<Content>>businessErrorHandler())
                .compose(RxTransformers.<List<Content>>applySchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (!isRefresh && getView().getUserVisibleHint()) {
                            getView().showProgress("Loading", true);
                        }
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (!isRefresh && getView().getUserVisibleHint()) {
                            getView().hideProgress();
                        }
                    }
                })
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        getView().onLoadContent(contents);
                        loadMorePageIndex = 1;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMoreContent() {
        RetrofitService.getService(GankApi.class)
                .getContent(categoryId, DEFAULT_PAGE_COUNT, loadMorePageIndex++)
                .compose(RxTransformers.<List<Content>>businessErrorHandler())
                .compose(RxTransformers.<List<Content>>applySchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        getView().showProgress("Loading", true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        getView().hideProgress();
                    }
                })
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        getView().onLoadMoreContent(contents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
