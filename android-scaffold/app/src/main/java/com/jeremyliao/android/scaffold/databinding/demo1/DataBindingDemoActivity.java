package com.jeremyliao.android.scaffold.databinding.demo1;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDatabindingDemoBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.RecyclerViewQuickDemo;

public class DataBindingDemoActivity extends AppCompatActivity {

    ActivityDatabindingDemoBinding binding;
    DemoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo);
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(DemoViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.name.setValue("hello world");
    }
}
