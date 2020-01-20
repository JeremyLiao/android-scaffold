package com.jeremyliao.android.scaffold.news.api;

import com.jeremyliao.android.scaffold.news.api.exception.BusinessException;
import com.jeremyliao.android.scaffold.news.beans.gank.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2020-01-19.
 */
public final class RxTransformers {

    public static <T> ObservableTransformer<BaseBean<T>, T> businessErrorHandler() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream
                        .map(new Function<BaseBean<T>, T>() {
                            @Override
                            public T apply(BaseBean<T> tBaseBean) throws Exception {
                                if (tBaseBean.isError()) {
                                    throw new BusinessException();
                                }
                                return tBaseBean.getResults();
                            }
                        });
            }
        };
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
