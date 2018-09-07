package com.alpha.module_main.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewsPagerAdapter extends PagerAdapter{
    private ArrayList<View> pageViews;

    public ViewsPagerAdapter(ArrayList<View> pageViews) {
        this.pageViews = pageViews;
    }

    @Override
    public int getCount() {
        return pageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup arg0, int arg1, @NonNull Object arg2) {
        arg0.removeView(pageViews.get(arg1));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup arg0, int arg1) {
        arg0.addView(pageViews.get(arg1));
        return pageViews.get(arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup arg0) {

    }

    @Override
    public void finishUpdate(@NonNull ViewGroup arg0) {

    }
}
