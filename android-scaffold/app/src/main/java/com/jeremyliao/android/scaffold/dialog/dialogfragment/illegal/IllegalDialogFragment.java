package com.jeremyliao.android.scaffold.dialog.dialogfragment.illegal;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.DialogIllegalBinding;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.base.BaseDialogFragment;
import com.jeremyliao.android.scaffold.utils.ToastUtils;

/**
 * Created by liaohailiang on 2020-02-18.
 */
public class IllegalDialogFragment extends BaseDialogFragment {

    public ObservableField<String> title = new ObservableField<>();
    private DialogIllegalBinding binding;

    public static IllegalDialogFragment newInstance() {
        IllegalDialogFragment f = new IllegalDialogFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_illegal, container, false);
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
        return (int) (0.6 * width);
    }

    public void onLeftClick() {
        new AnimationActor1(10, 1000).start();
    }

    public void onRightClick() {
        dismiss();
    }

    class AnimationActor1 extends AnimationActor {
        public AnimationActor1(int limit, long delay) {
            super(limit, delay);
        }

        @Override
        protected boolean shouldStop() {
            return !isAdded();
        }

        @Override
        protected void onAnimation(int currentTimes) {
            if (isAdded()) {
                binding.tvTitle.setText("" + currentTimes);
                binding.tvTitle.setTextColor(getResources().getColor(R.color.card_color));
            }
            Log.d("Illegal", "" + currentTimes);
        }

        @Override
        protected void onFinished() {
            ToastUtils.showLong("Animation finished!");
        }
    }
}
