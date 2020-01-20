package com.jeremyliao.android.scaffold.news.modules.home;

import com.jeremyliao.android.scaffold.news.base.BasePresenter;
import com.jeremyliao.android.scaffold.news.base.BaseView;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsHomeContract {

    public interface View extends BaseView {

        void onLoadCategories(List<Category> categories);
    }

    public interface Presenter extends BasePresenter<View> {

        void getCategories();
    }
}
