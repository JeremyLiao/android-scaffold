package com.jeremyliao.android.scaffold.news.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;


public class GsonUtil {
    private static final Gson sGson;

    static {
        sGson = new Gson();
    }

    public static String toJson(Object o) {
        return sGson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> cls) throws JsonSyntaxException {
        return sGson.fromJson(json, cls);
    }

    public static Gson gson() {
        return sGson;
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }
}
