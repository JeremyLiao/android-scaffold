package com.jeremyliao.android.scaffold.databinding.vmshare;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentVmShareDemoBinding;

import java.util.Random;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class VmShareFragment extends Fragment {

    FragmentVmShareDemoBinding binding;
    DataShareViewModel viewModel;
    private Random random = new Random();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vm_share_demo, container, false);
        viewModel = ViewModelProviders.of(getActivity()).get(DataShareViewModel.class);
        binding.setVm(viewModel);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public void onChangeValue() {
        String value = "Value: " + random.nextInt();
        FragmentActivity activity = getActivity();
        if (activity instanceof ViewModelShareDemo) {
            ViewModelShareDemo shareDemo = (ViewModelShareDemo) activity;
            shareDemo.viewModel.name.setValue(value);
        }
    }
}
