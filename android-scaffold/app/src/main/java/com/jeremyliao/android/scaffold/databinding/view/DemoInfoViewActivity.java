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
        demoInfo.cardNo = "8888********8888";
        demoInfo.cardTypeName = "电子卡";
        demoInfo.gradeName = "白金卡";
        demoInfo.memberName = "张先生";
        demoInfo.mobile = "138****8888";
        binding.demoInfoView.setSimpleCardDTO(demoInfo);
    }
}
