package com.jeremyliao.android.scaffold.mvp.demo;

import com.jeremyliao.android.scaffold.mvp.IPresenter;
import com.jeremyliao.android.scaffold.mvp.IView;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class DemoContract {

    public interface View extends IView {

        void showProgress();

        void hideProgress();

        void setItems(List<String> items);

        void showMessage(String message);
    }

    public interface Presenter extends IPresenter<View> {

        void onStart();

        void onItemClicked(String item);

        void onFinished(List<String> items);
    }
}
