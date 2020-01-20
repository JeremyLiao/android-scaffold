package com.jeremyliao.android.scaffold.news.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by liaohailiang on 2020-01-19.
 */
public class BaseFragment extends Fragment implements BaseView {

    private boolean isShowDialog = false;
    private ProgressDialog progressDialog;

    @Override
    public void showProgress(String message, boolean cancelable) {
        if (isShowDialog) {
            return;
        }
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(message);
        progressDialog.show();
        isShowDialog = true;
    }

    @Override
    public void hideProgress() {
        FragmentActivity activity = getActivity();
        if (activity != null && (activity.isDestroyed() || activity.isFinishing())) {
            return;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        isShowDialog = false;
    }
}
