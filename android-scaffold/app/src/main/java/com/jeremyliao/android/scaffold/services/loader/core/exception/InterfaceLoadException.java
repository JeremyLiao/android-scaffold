package com.jeremyliao.android.scaffold.services.loader.core.exception;

/**
 * Created by liaohailiang on 2020-06-04.
 */
public class InterfaceLoadException extends RuntimeException {

    public InterfaceLoadException() {
    }

    public InterfaceLoadException(String message) {
        super(message);
    }

    public InterfaceLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterfaceLoadException(Throwable cause) {
        super(cause);
    }
}
