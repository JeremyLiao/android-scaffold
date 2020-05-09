package com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.DialogAlertVmBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.base.BaseDialogFragment;

/**
 * Created by liaohailiang on 2020-02-18.
 */
@Deprecated
public class AlertVmDialogFragment extends BaseDialogFragment {

    public static class AlertViewModel extends ViewModel {
        public MutableLiveData<String> title = new MutableLiveData<>();
        public MutableLiveData<String> content = new MutableLiveData<>();
        public MutableLiveData<String> leftText = new MutableLiveData<>();
        public MutableLiveData<String> rightText = new MutableLiveData<>();
    }

    private DialogAlertVmBinding binding;
    private AlertViewModel viewModel;
    private View.OnClickListener onLeftListener, onRightListener;

    public static AlertVmDialogFragment newInstance() {
        AlertVmDialogFragment f = new AlertVmDialogFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_alert_vm, container, false);
        viewModel = ViewModelProviders.of(this).get(AlertViewModel.class);
        binding.setVm(viewModel);
        binding.setHandler(this);
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
        viewModel.content.setValue(str);
    }

    public void setTitle(String str) {

        viewModel.title.setValue(str);
    }

    public void setContent(int resId) {
        setContent(getString(resId));
    }

    public void setTitle(int resId) {
        setTitle(getString(resId));
    }

    public void setLeftButtonText(String txt) {
        viewModel.leftText.setValue(txt);
    }

    public void setLeftButtonText(int resId) {
        setLeftButtonText(getString(resId));
    }

    public void setRightButtonText(String txt) {
        viewModel.rightText.setValue(txt);
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
