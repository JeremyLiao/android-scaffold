package com.jeremyliao.android.scaffold.paint.paintbase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityPaintBaseBinding;

public class PaintBaseActivity extends AppCompatActivity {

    ActivityPaintBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_paint_base);
        binding.setLifecycleOwner(this);
    }
}
