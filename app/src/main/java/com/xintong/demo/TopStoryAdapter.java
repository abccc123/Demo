package com.xintong.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xintong on 2016/12/27.
 */

public class TopStoryAdapter extends FragmentStatePagerAdapter{
    private List<News.TopStoriesBean> topStoriesBeen=new ArrayList<>();
    public TopStoryAdapter(FragmentManager fm, List<News.TopStoriesBean> topStoriesBeen) {
        super(fm);
        this.topStoriesBeen = topStoriesBeen;
    }

    @Override
    public Fragment getItem(int position) {
        TopStoryFragment fragment = new TopStoryFragment();
        Bundle args = new Bundle();
        args.putString("title", topStoriesBeen.get(position).getTitle());
        args.putString("image", topStoriesBeen.get(position).getImage());
        Log.e("topAdapter",topStoriesBeen.get(position).getTitle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return topStoriesBeen.size();
    }
}
