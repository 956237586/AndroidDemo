package com.example.administrator.pager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ViewPager适配器
 */
public class MyPagerAdapter extends PagerAdapter {
    public List<View> myViews;

    public MyPagerAdapter(List<View> mListViews) {
        this.myViews = mListViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = myViews.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(myViews.get(position));
    }

    @Override
    public int getCount() {
        return myViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

}