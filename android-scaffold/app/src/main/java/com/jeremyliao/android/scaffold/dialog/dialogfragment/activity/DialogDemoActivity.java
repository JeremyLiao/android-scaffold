package com.jeremyliao.android.scaffold.dialog.dialogfragment.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityDialogsBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.AlertDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.AlertVmDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.NarrowDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog.WideDialogFragment;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.utils.FragmentDialogUtils;

public class DialogDemoActivity extends AppCompatActivity {

    ActivityDialogsBinding binding;

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
        AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance();
        alertDialogFragment.setTitle("标题标题");
        alertDialogFragment.setContent("这里是文案文案文案文案");
//        alertDialogFragment.setLeftButtonText("取消");
        alertDialogFragment.setRightButtonText("确认");
        FragmentDialogUtils.showDialog(this, alertDialogFragment);
    }

    public void alertVmDialog() {
        AlertVmDialogFragment dialogFragment = AlertVmDialogFragment.newInstance();
        dialogFragment.setTitle("标题标题");
        dialogFragment.setContent("这里是文案文案文案文案");
        dialogFragment.setLeftButtonText("取消");
        dialogFragment.setRightButtonText("确认");
        FragmentDialogUtils.showDialog(this, dialogFragment);
    }

    public void checkAlertShow() {
        Toast.makeText(this, "Alert show: " + isAlertShow(), Toast.LENGTH_SHORT).show();
    }

    private boolean isAlertShow() {
        return true;
    }
}
