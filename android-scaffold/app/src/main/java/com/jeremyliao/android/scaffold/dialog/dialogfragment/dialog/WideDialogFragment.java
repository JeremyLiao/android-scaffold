package com.jeremyliao.android.scaffold.dialog.dialogfragment.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.DialogFragmentBasicBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.base.BaseDialogFragment;

/**
 * Created by liaohailiang on 2020-02-18.
 */
public class WideDialogFragment extends BaseDialogFragment {

    private DialogFragmentBasicBinding binding;

    public static WideDialogFragment newInstance() {
        WideDialogFragment f = new WideDialogFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_basic, container, false);
        binding.setLifecycleOwner(this);
        binding.btnLeft.setOnClickListener(dismissListener);
        binding.btnRight.setOnClickListener(dismissListener);
        return binding.getRoot();
    }

    @Override
    protected int width() {
        int width = getResources().getDisplayMetrics().widthPixels;
        return (int) (0.9 * width);
    }

    private View.OnClickListener dismissListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
