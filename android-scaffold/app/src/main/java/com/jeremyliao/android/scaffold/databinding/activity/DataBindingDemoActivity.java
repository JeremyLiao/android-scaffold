package com.jeremyliao.android.scaffold.databinding.activity;

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

import java.util.Random;

public class DataBindingDemoActivity extends AppCompatActivity {

    ActivityDatabindingDemoBinding binding;
    DemoViewModel viewModel;
    private Random random = new Random();
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo);
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(DemoViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.name.setValue("hello world");
        viewModel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                name = s;
            }
        });
        viewModel.set.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Toast.makeText(DataBindingDemoActivity.this, "value has set", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onChangeValue(View v) {
        String value = "Value: " + random.nextInt();
        viewModel.name.setValue(value);
    }

    public void onSetSameValue(View v) {
        viewModel.set.setValue(true);
    }
}
