package com.jeremyliao.android.scaffold.services.loader.core;

/**
 * Created by liaohailiang on 2020-06-04.
 */
public final class Utils {

    private Utils() {
    }

    public static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }
}
