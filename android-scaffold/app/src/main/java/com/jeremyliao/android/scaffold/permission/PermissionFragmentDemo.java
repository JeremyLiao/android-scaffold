package com.jeremyliao.android.scaffold.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;

public class PermissionFragmentDemo extends AppCompatActivity {

    PermissionRequestFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databinding_fragment_demo);
        fragment = new PermissionRequestFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}
