package com.jeremyliao.android.scaffold.databinding.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo);
        viewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        binding.setVm(viewModel);
        binding.setHandler(this);
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

    public void onChangeValue() {
        String value = "Value: " + random.nextInt();
        viewModel.name.setValue(value);
    }

    public void onSetSameValue() {
        viewModel.set.setValue(true);
    }


    public void onChangeValueDelay() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String value = "Value: " + random.nextInt();
                viewModel.name.setValue(value);
            }
        }, 2000);
    }
}
