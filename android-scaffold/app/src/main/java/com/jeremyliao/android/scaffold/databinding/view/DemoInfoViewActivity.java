package com.jeremyliao.android.scaffold.databinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDemoInfoBinding;
import com.jeremyliao.android.scaffold.databinding.view.bean.DemoInfo;

public class DemoInfoViewActivity extends AppCompatActivity {

    ActivityDemoInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_info);
        binding.setLifecycleOwner(this);
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.cardNo = "8888888888888888";
        demoInfo.cardTypeName = "会员卡";
        demoInfo.gradeName = "超级会员卡";
        demoInfo.memberName = "张先生";
        demoInfo.mobile = "13888888888";
        binding.demoInfoView.setInfoData(demoInfo);
    }
}
