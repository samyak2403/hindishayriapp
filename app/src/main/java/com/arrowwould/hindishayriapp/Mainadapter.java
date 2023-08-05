package com.arrowwould.hindishayriapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Mainadapter extends BaseAdapter {
    SecondActivity second_page;
    String[] title;
    Animation ani;

    public Mainadapter(SecondActivity second_page, String[] title) {
        this.second_page = second_page;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.length;
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

        view = LayoutInflater.from(second_page).inflate(R.layout.view, viewGroup, false);
        ani = AnimationUtils.loadAnimation(second_page, R.anim.animation1);
        TextView textView;
        textView = view.findViewById(R.id.textview);
        textView.setText(title[i]);
        textView.setAnimation(ani);

        return view;
    }
}

