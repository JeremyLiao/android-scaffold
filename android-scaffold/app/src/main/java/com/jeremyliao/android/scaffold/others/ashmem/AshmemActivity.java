package com.jeremyliao.android.scaffold.others.ashmem;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityAshmemBinding;
import com.jeremyliao.android.scaffold.manager.SharedMemoryManager;

public class AshmemActivity extends AppCompatActivity {

    ActivityAshmemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ashmem);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void startNewPage() {
        int descriptor = SharedMemoryManager.getManager().put("hello world");
        Intent intent = new Intent(this, AshmemTargetActivity.class);
        intent.putExtra(AshmemTargetActivity.EXTRA_ID, descriptor);
        startActivity(intent);
    }
}
