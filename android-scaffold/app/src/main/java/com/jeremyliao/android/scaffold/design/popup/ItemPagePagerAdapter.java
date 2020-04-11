package com.jeremyliao.android.scaffold.design.popup;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ItemPagePagerAdapter extends PagerAdapter {

    List<String> titles = new ArrayList<>();
    List<View> views = new ArrayList<>();

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        if (object == null) {
            return PagerAdapter.POSITION_NONE;
        }
        return views.indexOf(object);
    }

    public void setItems(List<View> views, List<String> titles) {
        this.views = views;
        this.titles = titles;
        notifyDataSetChanged();
    }
}
