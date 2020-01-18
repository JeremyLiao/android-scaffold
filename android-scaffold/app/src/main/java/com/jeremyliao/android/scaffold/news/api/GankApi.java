package com.jeremyliao.android.scaffold.news.api;

import com.jeremyliao.android.scaffold.news.beans.gank.BaseBean;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by liaohailiang on 2019-07-31.
 */
public interface GankApi {

    @GET("api/xiandu/categories")
    Observable<BaseBean<List<Category>>> categories();

    @GET("api/xiandu/category/{category}")
    Observable<BaseBean<List<SubCategory>>> subCategories(@Path("category") String category);

    @GET("api/xiandu/data/id/{subCategory}/count/{count}/page/{page}")
    Observable<BaseBean<List<Content>>> getContent(@Path("subCategory") String subCategory,
                                                   @Path("count") int count,
                                                   @Path("page") int page);
}
