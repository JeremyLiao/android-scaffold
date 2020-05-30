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

    static abstract class MainThreadDisposable implements Disposable {
        /**
         * Verify that the calling thread is the Android main thread.
         * <p>
         * Calls to this method are usually preconditions for subscription behavior which instances of
         * this class later undo. See the class documentation for an example.
         *
         * @throws IllegalStateException when called from any other thread.
         */
        public static void verifyMainThread() {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException(
                        "Expected to be called on the main thread but was " + Thread.currentThread().getName());
            }
        }

        private final AtomicBoolean unsubscribed = new AtomicBoolean();

        @Override
        public final boolean isDisposed() {
            return unsubscribed.get();
        }

        @Override
        public final void dispose() {
            if (unsubscribed.compareAndSet(false, true)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    onDispose();
                } else {
                    AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                        @Override
                        public void run() {
                            onDispose();
                        }
                    });
                }
            }
        }

        protected abstract void onDispose();
    }
}
