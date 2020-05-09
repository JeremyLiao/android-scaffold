package com.jeremyliao.android.scaffold.dialog.dialogfragment.illegal;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityIllegalTestBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.utils.FragmentDialogUtils;

public class IllegalTestActivity extends AppCompatActivity {

    ActivityIllegalTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_illegal_test);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void animation1() {
        IllegalDialogFragment fragment = IllegalDialogFragment.newInstance();
        FragmentDialogUtils.showDialog(this, fragment);
    }
}
