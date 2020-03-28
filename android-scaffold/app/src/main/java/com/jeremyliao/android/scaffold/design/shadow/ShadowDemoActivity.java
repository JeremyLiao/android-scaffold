package com.jeremyliao.android.scaffold.design.shadow;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDesignDemoBinding;
import com.jeremyliao.android.scaffold.databinding.ActivityShadowDemoBinding;

public class ShadowDemoActivity extends AppCompatActivity {

    ActivityShadowDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shadow_demo);
        binding.setLifecycleOwner(this);
    }
}
