package com.jeremyliao.android.scaffold.base.helper;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liaohailiang on 2019-08-01.
 */
public class RxSchedulersHelper {

    private RxSchedulersHelper() {
    }

    public static void initRxSchedulers() {
        Function<Scheduler, Scheduler> function = new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        };
        RxJavaPlugins.setIoSchedulerHandler(function);
        RxJavaPlugins.setComputationSchedulerHandler(function);
        RxJavaPlugins.setNewThreadSchedulerHandler(function);
        RxJavaPlugins.setSingleSchedulerHandler(function);
        RxAndroidPlugins.setMainThreadSchedulerHandler(function);
    }

    public static void initRxInitSchedulers() {
        Function<Callable<Scheduler>, Scheduler> function = new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(function);
        RxJavaPlugins.setInitComputationSchedulerHandler(function);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(function);
        RxJavaPlugins.setInitSingleSchedulerHandler(function);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(function);
    }
}
