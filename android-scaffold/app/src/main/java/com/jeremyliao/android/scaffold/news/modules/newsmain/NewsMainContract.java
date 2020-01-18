package com.jeremyliao.android.scaffold.news.modules.newsmain;

import com.jeremyliao.android.scaffold.mvp.IPresenter;
import com.jeremyliao.android.scaffold.mvp.IView;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsMainContract {

    public interface View extends IView<Presenter> {

        void onLoadCategories(List<SubCategory> subCategories);
    }

    public interface Presenter extends IPresenter<View> {

        void getSubCategories(String categoryId);
    }
}
