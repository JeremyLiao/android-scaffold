package com.jeremyliao.android.scaffold.paint.flipboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityFlipBoardBinding;

public class FlipBoardActivity extends AppCompatActivity {

    ActivityFlipBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flip_board);
        binding.setLifecycleOwner(this);
    }
}
