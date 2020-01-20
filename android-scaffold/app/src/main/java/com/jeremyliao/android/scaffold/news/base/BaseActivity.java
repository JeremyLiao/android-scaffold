package com.jeremyliao.android.scaffold.news.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liaohailiang on 2020-01-19.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    private boolean isShowDialog = false;
    private ProgressDialog progressDialog;

    @Override
    public void showProgress(String message, boolean cancelable) {
        if (isShowDialog) {
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(message);
        progressDialog.show();
        isShowDialog = true;
    }

    @Override
    public void hideProgress() {
        if (isDestroyed() || isFinishing()) {
            return;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        isShowDialog = false;
    }
}
