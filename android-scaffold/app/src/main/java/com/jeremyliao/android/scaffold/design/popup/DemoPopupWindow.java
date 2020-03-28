package com.jeremyliao.android.scaffold.design.popup;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ViewDemoPopupBinding;

/**
 * Created by liaohailiang on 2020-03-19.
 */
public class DemoPopupWindow extends RelativePopupWindow {

    ViewDemoPopupBinding binding;

    public DemoPopupWindow(Context context) {
        super(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_demo_popup,
                null, false);
        setContentView(binding.getRoot());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
