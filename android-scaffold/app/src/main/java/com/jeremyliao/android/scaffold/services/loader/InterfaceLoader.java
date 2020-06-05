package com.jeremyliao.android.scaffold.services.loader;

import android.os.IBinder;
import android.support.annotation.NonNull;

import com.jeremyliao.android.scaffold.services.loader.core.InterfaceHandler;
import com.jeremyliao.android.scaffold.services.loader.core.Utils;

import java.lang.reflect.Proxy;

/**
 * Created by liaohailiang on 2020-06-04.
 */
public final class InterfaceLoader {

    private InterfaceLoader() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(@NonNull final Class<T> service, @NonNull final IBinder binder) {
        Utils.validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(),
                new Class[]{service},
                new InterfaceHandler(service, binder));
    }
}
