package com.arrowwould.hindishayriapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SecondAdapter extends BaseAdapter {

    ThirdActivity third_page;
    String[] str;
    Animation animation;

    public SecondAdapter(ThirdActivity third_page, String[] str) {
        this.third_page=third_page;
        this.str=str;

    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view= LayoutInflater.from(third_page).inflate(R.layout.shayri_view,viewGroup,false);
        animation= AnimationUtils.loadAnimation(third_page,R.anim.animation1);
        TextView textView;
        textView=view.findViewById(R.id.last_view);

        textView.setText(str[i]);
        textView.setAnimation(animation);

        return view;
    }
}

