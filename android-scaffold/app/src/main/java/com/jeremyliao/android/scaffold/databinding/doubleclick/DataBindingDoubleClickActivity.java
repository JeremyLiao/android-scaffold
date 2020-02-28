package com.jeremyliao.android.scaffold.databinding.doubleclick;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDatabindingDoubleClickBinding;

public class DataBindingDoubleClickActivity extends AppCompatActivity {

    ActivityDatabindingDoubleClickBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_double_click);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void onClickTimes() {
        Log.d("DataBindingDoubleClick", "onClickTimes");
    }
}
