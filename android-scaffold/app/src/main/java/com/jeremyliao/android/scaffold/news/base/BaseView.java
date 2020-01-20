package com.jeremyliao.android.scaffold.news.base;

import com.jeremyliao.android.scaffold.mvp.IView;

/**
 * Created by liaohailiang on 2020-01-19.
 */
public interface BaseView extends IView {

    void showProgress(String message, boolean cancelable);

    void hideProgress();
}
