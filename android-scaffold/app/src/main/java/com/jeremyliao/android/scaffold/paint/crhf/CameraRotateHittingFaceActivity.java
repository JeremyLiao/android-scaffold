package com.jeremyliao.android.scaffold.paint.crhf;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityPaintCrhfBinding;

public class CameraRotateHittingFaceActivity extends AppCompatActivity {

    ActivityPaintCrhfBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paint_crhf);
        binding.setLifecycleOwner(this);
    }
}
