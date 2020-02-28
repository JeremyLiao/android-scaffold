package com.jeremyliao.android.scaffold.dialog.dialogfragment.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDialogThemeBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.theme.ThemeDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.utils.FragmentDialogUtils;

public class DialogThemeActivity extends AppCompatActivity {

    ActivityDialogThemeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialog_theme);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void showBasicDialog(int num) {
        FragmentDialogUtils.showDialog(this, ThemeDialogFragment.newInstance(num));
    }
}
