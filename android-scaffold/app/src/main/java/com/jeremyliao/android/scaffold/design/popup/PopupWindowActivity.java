package com.jeremyliao.android.scaffold.design.popup;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityPopupBinding;

public class PopupWindowActivity extends AppCompatActivity {

    ActivityPopupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popup);
        binding.setLifecycleOwner(this);
        binding.setHandler(this);
    }

    public void showPopup() {
        DemoPopupWindow popupWindow = new DemoPopupWindow(this);
        popupWindow.showOnAnchor(binding.btnPop1,
                RelativePopupWindow.VerticalPosition.ABOVE,
                RelativePopupWindow.HorizontalPosition.CENTER,
                false);
    }

    public void showComplicatedPopup() {
        ComplicatedPopupWindow popupWindow = new ComplicatedPopupWindow(this);
        popupWindow.showOnAnchor(binding.btnPop2,
                RelativePopupWindow.VerticalPosition.ABOVE,
                RelativePopupWindow.HorizontalPosition.CENTER,
                false);
    }

}
