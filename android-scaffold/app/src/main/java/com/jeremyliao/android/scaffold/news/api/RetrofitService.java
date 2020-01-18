package com.jeremyliao.android.scaffold.news.api;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liaohailiang on 2020-01-16.
 */
public class RetrofitService {

    private static final String TAG = RetrofitService.class.getSimpleName();
    private static final String HOST = "http://gank.io/";

    private static final Retrofit DEFAULT_RETROFIT;

    private static final Map<Class<?>, Object> SERVICE_CACHE = new ConcurrentHashMap<>();

    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Log.d(TAG, request.toString());
            final Response response = chain.proceed(request);
            Log.d(TAG, response.toString());
            return response;
        }
    };

    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        DEFAULT_RETROFIT = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HOST)
                .build();
    }

    public static <T> T getService(Class<T> service) {
        if (!SERVICE_CACHE.containsKey(service)) {
            SERVICE_CACHE.put(service, DEFAULT_RETROFIT.create(service));
        }
        return (T) SERVICE_CACHE.get(service);
    }
}
