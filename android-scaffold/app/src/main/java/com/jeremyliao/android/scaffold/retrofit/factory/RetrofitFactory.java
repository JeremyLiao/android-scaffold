package com.jeremyliao.android.scaffold.retrofit.factory;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liaohailiang on 2019-07-31.
 */
public class RetrofitFactory {

    private static final Map<String, Retrofit> RETROFIT_MAP = new HashMap<>();

    private RetrofitFactory() {
    }

    public static Retrofit create(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit get(String host) {
        if (!RETROFIT_MAP.containsKey(host)) {
            RETROFIT_MAP.put(host, create(host));
        }
        return RETROFIT_MAP.get(host);
    }
}
