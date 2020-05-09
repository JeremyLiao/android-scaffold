package com.jeremyliao.android.scaffold.rxjava2;

import android.annotation.SuppressLint;
import android.util.Log;

import com.jeremyliao.android.scaffold.news.api.GankApi;
import com.jeremyliao.android.scaffold.news.api.RetrofitService;
import com.jeremyliao.android.scaffold.news.api.RxTransformers;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;
import com.jeremyliao.android.scaffold.utils.ToastUtils;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2019-04-27.
 */
public class RxJava2Demo {

    private static final String TAG = "RxJava2";

    public static void create() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("hello");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void fromCallable() {
        Observable
                .fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "hello";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void fromFuture() {
        Observable
                .fromFuture(new Future<String>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public String get() throws ExecutionException, InterruptedException {
                        return null;
                    }

                    @Override
                    public String get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void just() {
        Observable.just(2)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Log.d(TAG, "map: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Log.d(TAG, "map: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Log.d(TAG, "map: " + Thread.currentThread().getName());
                        return integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "subscribe: " + Thread.currentThread().getName());
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void testRetrofit() {
        final GankApi gankApi = RetrofitService.getService(GankApi.class);
        gankApi.categories().compose(RxTransformers.<List<Category>>businessErrorHandler())
                .flatMap(new Function<List<Category>, Observable<List<SubCategory>>>() {
                    @Override
                    public Observable<List<SubCategory>> apply(List<Category> categories) throws Exception {
                        return gankApi.subCategories(categories.get(0).getEn_name())
                                .compose(RxTransformers.<List<SubCategory>>businessErrorHandler());
                    }
                })
                .flatMap(new Function<List<SubCategory>, Observable<List<Content>>>() {
                    @Override
                    public Observable<List<Content>> apply(List<SubCategory> subCategories) throws Exception {
                        return gankApi.getContent(subCategories.get(0).getId(), 10, 1)
                                .compose(RxTransformers.<List<Content>>businessErrorHandler());
                    }
                })
                .compose(RxTransformers.<List<Content>>applySchedulers())
                .subscribe(new Consumer<List<Content>>() {
                    @Override
                    public void accept(List<Content> contents) throws Exception {
                        ToastUtils.showLong("" + contents);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showLong(throwable.toString());
                    }
                });
    }

    private static void printThreadInfo(String tag) {
        Log.d(TAG, tag + " | " + Thread.currentThread().getName());
    }

    private static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    @SuppressLint("CheckResult")
    public static void threadChange1() {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        printThreadInfo("1");
                        delay(200);
                        emitter.onNext("hello world");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        printThreadInfo("2");
                        delay(200);
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        printThreadInfo("3");
                        delay(200);
                        return s;
                    }
                })
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(final String s) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                printThreadInfo("4");
                                delay(200);
                                emitter.onNext(s + "aa");
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        printThreadInfo("consumer");
                    }
                });
    }
}
