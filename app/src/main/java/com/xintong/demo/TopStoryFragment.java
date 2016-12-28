package com.xintong.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by xintong on 2016/12/27.
 */
public class TopStoryFragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout. fragment_main, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.textview);
        tv.setText(getArguments().getString("title"));
        Log.e("top",getArguments().getString("title"));
        ImageView imageView= (ImageView) rootView.findViewById(R.id.imageview);
        Glide.with(getActivity())
                .load(getArguments().getString("image"))
                .into(imageView);
        return rootView;
    }
}
