package com.jeremyliao.android.scaffold.mvp;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public interface IPresenter<V extends IView> {

    V getView();
}
