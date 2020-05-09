package com.jeremyliao.android.scaffold.databinding.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentDatabindingDemoBinding;

import java.util.Random;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class DemoFragment extends Fragment {

    FragmentDatabindingDemoBinding binding;
    DemoViewModel viewModel;
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_databinding_demo, container, false);
        viewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        binding.setVm(viewModel);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public void onChangeValue() {
        String value = "Value: " + random.nextInt();
        viewModel.name.setValue(value);
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
