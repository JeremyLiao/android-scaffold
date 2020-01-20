package com.jeremyliao.android.scaffold.news.modules.newsmain;

import com.jeremyliao.android.scaffold.news.base.BasePresenter;
import com.jeremyliao.android.scaffold.news.base.BaseView;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsMainContract {

    public interface View extends BaseView {

        void onLoadCategories(List<SubCategory> subCategories);
    }

    public interface Presenter extends BasePresenter<View> {

        void getSubCategories(String categoryId);
    }
}
