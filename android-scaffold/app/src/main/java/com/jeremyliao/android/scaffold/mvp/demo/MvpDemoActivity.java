package com.jeremyliao.android.scaffold.mvp.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MvpDemoActivity extends AppCompatActivity implements DemoContract.View {

    private DemoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DemoPresenter(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List<String> items) {

    }

    @Override
    public void showMessage(String message) {

    }
}
