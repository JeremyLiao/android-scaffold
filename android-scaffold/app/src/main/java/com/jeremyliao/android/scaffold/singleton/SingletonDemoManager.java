package com.jeremyliao.android.scaffold.singleton;

/**
 * Created by liaohailiang on 2019-07-10.
 */
public class SingletonDemoManager {

    private SingletonDemoManager() {
    }

    private static class SingletonHolder {
        private static final SingletonDemoManager INSTANCE = new SingletonDemoManager();
    }

    public static SingletonDemoManager get() {
        return SingletonHolder.INSTANCE;
    }
}
