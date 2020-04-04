package com.jeremyliao.android.scaffold.dialog.dialogfragment.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDialogsBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.AlertDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.NarrowDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.WideDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.utils.FragmentDialogUtils;

public class DialogDemoActivity extends AppCompatActivity {

    ActivityDialogsBinding binding;
    private AlertDialogFragment alertDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialogs);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void wideDialog() {
        FragmentDialogUtils.showDialog(this, WideDialogFragment.newInstance());
    }

    public void narrowDialog() {
        FragmentDialogUtils.showDialog(this, NarrowDialogFragment.newInstance());
    }

    public void alertDialog() {
        alertDialogFragment = AlertDialogFragment.newInstance();
        alertDialogFragment.setTitle("标题标题");
        alertDialogFragment.setContent("这里是文案文案文案文案");
        alertDialogFragment.setLeftButtonText("取消");
        alertDialogFragment.setRightButtonText("确认");
        FragmentDialogUtils.showDialog(this, alertDialogFragment);
    }

    public void checkAlertShow() {
        Toast.makeText(this, "Alert show: " + isAlertShow(), Toast.LENGTH_SHORT).show();
    }

    private boolean isAlertShow() {
        if (alertDialogFragment == null) {
            return false;
        }
        return alertDialogFragment.isShowing();
    }
}
