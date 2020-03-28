package com.jeremyliao.android.scaffold.design.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDesignDemoBinding;

public class DesignDemoActivity extends AppCompatActivity {

    ActivityDesignDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_design_demo);
        binding.setLifecycleOwner(this);
    }
}
