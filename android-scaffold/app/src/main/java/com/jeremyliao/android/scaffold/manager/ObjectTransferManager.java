package com.jeremyliao.android.scaffold.manager;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 通过binder传输数据有可能面临数据过大抛异常的问题
 * 用ObjectTransferManager暂存数据，只传输生成的key
 * 减少发生异常的可能
 */
public class ObjectTransferManager {

    public static ObjectTransferManager getManager() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ObjectTransferManager INSTANCE = new ObjectTransferManager();
    }

    private ObjectTransferManager() {
    }

    private final Map<String, Object> objectMap = new HashMap<>();
    private final Random random = new Random();

    /**
     * @param object
     * @return key
     */
    public String putObject(Object object) {
        if (object == null) {
            return null;
        }
        String key = object.getClass().getName() + "-" + System.currentTimeMillis() + "-" + random.nextLong();
        objectMap.put(key, object);
        return key;
    }

    /**
     * @param key
     * @param <T>
     * @return T
     */
    public <T> T getObject(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        return (T) objectMap.remove(key);
    }
}
