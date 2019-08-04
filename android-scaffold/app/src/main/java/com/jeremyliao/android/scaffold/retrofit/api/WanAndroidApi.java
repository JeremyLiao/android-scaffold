package com.jeremyliao.android.scaffold.retrofit.api;

import com.jeremyliao.android.scaffold.retrofit.bean.ChaptersResp;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by liaohailiang on 2019-07-31.
 */
public interface WanAndroidApi {
    @GET("/wxarticle/chapters/json")
    Observable<ChaptersResp> chapters();
}
