package com.jeremyliao.android.scaffold.databinding.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDatabindingDemoBinding;
import com.jeremyliao.android.scaffold.databinding.ActivityLivedataBinding;
import com.jeremyliao.android.scaffold.databinding.activity.DemoViewModel;

import java.util.Random;

public class LiveDataActivity extends AppCompatActivity {

    ActivityLivedataBinding binding;
    LiveDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_livedata);
        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        viewModel.booleanLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Toast.makeText(LiveDataActivity.this, "" + aBoolean, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.stringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(LiveDataActivity.this, "" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setBoolean() {
        viewModel.booleanLiveData.setValue(false);
    }

    public void setString() {
        viewModel.stringLiveData.setValue("hello world");
    }

}
