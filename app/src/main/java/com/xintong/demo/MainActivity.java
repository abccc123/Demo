package com.xintong.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TopStoryAdapter topStoryAdapter;
    private List<News.TopStoriesBean> topStoriesBeen=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            loadLatestNews();

        //定义一个iewpager的adaper
        topStoryAdapter = new TopStoryAdapter(getSupportFragmentManager(),topStoriesBeen);
        //定义个Pager，即布局中定义的那个pagerview
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(topStoryAdapter);
        //定义一个指示变量，即布局中定义的那个
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }

    private void loadLatestNews() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                //String
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                //    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器
                .build();
        ZhihuService apiManager = retrofit.create(ZhihuService.class);//这里采用的是Java的动态代理模式
        apiManager.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onNext(News newsList) {
                        topStoriesBeen.addAll(newsList.getTop_stories());
                        topStoryAdapter.notifyDataSetChanged();
                        Log.e("title",topStoriesBeen.get(1).getTitle());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}
