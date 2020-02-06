package com.jeremyliao.android.scaffold.reuse.mvp;

import com.jeremyliao.android.scaffold.mvp.IPresenter;
import com.jeremyliao.android.scaffold.mvp.IView;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class CategoryContract {

    public interface View extends IView {

        void onLoadCategories(List<Category> categories);
    }

    public interface Presenter extends IPresenter<View> {

        void getCategories();
    }
}
