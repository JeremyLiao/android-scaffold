package com.jeremyliao.android.scaffold.databinding.vmshare;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;

public class ViewModelShareDemo extends AppCompatActivity {

    VmShareFragment fragment;
    DataShareViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databinding_fragment_demo);
        viewModel = ViewModelProviders.of(this).get(DataShareViewModel.class);
        fragment = new VmShareFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}
