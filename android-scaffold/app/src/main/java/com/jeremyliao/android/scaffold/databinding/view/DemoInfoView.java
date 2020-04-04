package com.jeremyliao.android.scaffold.databinding.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.DemoViewBinding;
import com.jeremyliao.android.scaffold.databinding.view.bean.DemoInfo;

/**
 * Created by liaohailiang on 2019-05-24.
 */
public class DemoInfoView extends FrameLayout {

    DemoViewBinding binding;

    public DemoInfoView(Context context) {
        super(context);
        init(context);
    }

    public DemoInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DemoInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.demo_view,
                this, true);
    }

    public void setInfoData(DemoInfo info) {
        if (info == null) {
            return;
        }
        binding.tvName.setText(info.memberName);
        binding.tvCardType.setText(info.cardTypeName);
        binding.tvCardGrade.setText(info.gradeName);
        binding.tvPhoneNumber.setText(info.mobile);
        binding.tvCardNumber.setText(info.cardNo);
    }
}
