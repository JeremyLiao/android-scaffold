package com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.DialogAlertBinding;
import com.jeremyliao.android.scaffold.databinding.DialogFragmentBasicBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.base.BaseDialogFragment;

/**
 * Created by liaohailiang on 2020-02-18.
 */
public class AlertDialogFragment extends BaseDialogFragment {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> leftText = new ObservableField<>();
    public ObservableField<String> rightText = new ObservableField<>();

    private DialogAlertBinding binding;
    private View.OnClickListener onLeftListener, onRightListener;

    public static AlertDialogFragment newInstance() {
        AlertDialogFragment f = new AlertDialogFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_alert, container, false);
        binding.setModel(this);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int width() {
        int width = getResources().getDisplayMetrics().widthPixels;
        return (int) (0.8 * width);
    }

    public void setContent(String str) {
        content.set(str);
    }

    public void setTitle(String str) {

        title.set(str);
    }

    public void setContent(int resId) {
        setContent(getString(resId));
    }

    public void setTitle(int resId) {
        setTitle(getString(resId));
    }

    public void setLeftButtonText(String txt) {
        leftText.set(txt);
    }

    public void setLeftButtonText(int resId) {
        setLeftButtonText(getString(resId));
    }

    public void setRightButtonText(String txt) {
        rightText.set(txt);
    }

    public void setRightButtonText(int resId) {
        setRightButtonText(getString(resId));
    }

    public void setLeftButtonListener(View.OnClickListener listener) {
        onLeftListener = listener;
    }

    public void setRightButtonListener(View.OnClickListener listener) {
        onRightListener = listener;
    }

    public void onLeftClick() {
        if (onLeftListener != null) {
            onLeftListener.onClick(binding.btnLeft);
        }
        dismiss();
    }

    public void onRightClick() {
        if (onRightListener != null) {
            onRightListener.onClick(binding.btnRight);
        }
        dismiss();
    }
}
