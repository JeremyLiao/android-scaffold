package com.jeremyliao.android.scaffold.rxjava2;

import android.os.Looper;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by liaohailiang on 2020-05-26.
 */
public class CallableObservable extends Observable<Object> {

    private Caller caller;

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        caller = new Caller(observer);
        observer.onSubscribe(caller);
    }

    public void call() {
        caller.run();
    }


    public enum Notification {
        INSTANCE
    }

    static final class Caller extends MainThreadDisposable implements Runnable {
        private final Observer<? super Object> observer;

        Caller(Observer<? super Object> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            if (!isDisposed()) {
                observer.onNext(Notification.INSTANCE);
            }
        }

        @Override
        protected void onDispose() {

        }
    }
}
