package com.jeremyliao.android.scaffold.rxjava2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityRxjavaDemoBinding;

public class RxJavaDemoActivity extends AppCompatActivity {

    ActivityRxjavaDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rxjava_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void testRetrofit() {
        RxJava2Demo.testRetrofit();
    }

}
