package com.jeremyliao.android.scaffold.mvp.demo;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class DemoPresenter implements DemoContract.Presenter {

    private final DemoContract.View view;

    public DemoPresenter(DemoContract.View demoView) {
        this.view = demoView;
    }

    @Override
    public DemoContract.View getView() {
        return view;
    }

    @Override
    public void onStart() {
        if (view != null) {
            view.showProgress();
        }
    }

    @Override
    public void onItemClicked(String item) {
        if (view != null) {
            view.showMessage(String.format("%s clicked", item));
        }
    }

    @Override
    public void onFinished(List<String> items) {
        if (view != null) {
            view.setItems(items);
            view.hideProgress();
        }
    }
}
