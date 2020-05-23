package com.jeremyliao.android.scaffold.others.ashmem;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityAshmemTargetBinding;
import com.jeremyliao.android.scaffold.manager.SharedMemoryManager;

public class AshmemTargetActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "extra_id";

    ActivityAshmemTargetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ashmem_target);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
        Intent intent = getIntent();
        int descriptor = intent.getIntExtra(EXTRA_ID, -1);
        String content = (String) SharedMemoryManager.getManager().read(descriptor);
        binding.tvContent.setText(content);
    }
}
