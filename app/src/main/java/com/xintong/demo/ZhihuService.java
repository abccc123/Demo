package com.xintong.demo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by laucherish on 16/3/15.
 */
public interface ZhihuService {


    @GET("stories/latest")
    Observable<News> getLatestNews();

    @GET("stories/before/{date}")
    Observable<News> getBeforeNews(@Path("date") String date);

//    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
//    @GET("story/{id}")
//    Observable<NewsDetail> getNewsDetail(@Path("id") int id);
}
