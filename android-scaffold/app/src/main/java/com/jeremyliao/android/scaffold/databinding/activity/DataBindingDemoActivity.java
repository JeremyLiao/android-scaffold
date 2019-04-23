package com.jeremyliao.android.scaffold.databinding.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDatabindingDemoBinding;

import java.util.Random;

public class DataBindingDemoActivity extends AppCompatActivity {

    ActivityDatabindingDemoBinding binding;
    DemoViewModel viewModel;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo);
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(DemoViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.name.setValue("hello world");
    }

    public void onChangeValue(View v) {
        String value = "Value: " + random.nextInt();
        viewModel.name.setValue(value);
    }
}
