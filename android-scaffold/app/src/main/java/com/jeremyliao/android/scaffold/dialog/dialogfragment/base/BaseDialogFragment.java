package com.jeremyliao.android.scaffold.dialog.dialogfragment.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by liaohailiang on 2020-02-18.
 */
public class BaseDialogFragment extends DialogFragment {

    private OnDismissListener onDismissListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        dialog.setCancelable(cancelable());
        dialog.setCanceledOnTouchOutside(cancelable());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        dialog.getWindow().setLayout(width(), height());
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (onDismissListener != null) {
            onDismissListener.onDismiss(this);
            onDismissListener = null;
        }
        super.onDismiss(dialog);
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    protected boolean cancelable() {
        return true;
    }

    protected int width() {
        int width = getResources().getDisplayMetrics().widthPixels;
        return (int) (0.8 * width);
    }


    protected int height() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    public interface OnDismissListener {
        void onDismiss(BaseDialogFragment dialog);
    }
}
