package com.jeremyliao.android.scaffold.design.popup;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ViewComplicatedPopupBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaohailiang on 2020-03-19.
 */
public class ComplicatedPopupWindow extends RelativePopupWindow {

    ViewComplicatedPopupBinding binding;
    private ItemPagePagerAdapter pagerAdapter;

    public ComplicatedPopupWindow(Context context) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_complicated_popup,
                null, false);
        setContentView(binding.getRoot());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init();
        initData(context);
    }

    private void init() {
        pagerAdapter = new ItemPagePagerAdapter();
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    private void initData(Context context) {
        List<View> views = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("1");
        titles.add("2");
        views.add(new ItemShowView(context));
        views.add(new ItemShowView(context));
        pagerAdapter.setItems(views, titles);
    }
}
