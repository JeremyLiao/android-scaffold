package com.jeremyliao.android.scaffold.news.modules.newslist;

import com.jeremyliao.android.scaffold.mvp.IPresenter;
import com.jeremyliao.android.scaffold.mvp.IView;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsListContract {

    public interface View extends IView<Presenter> {

        void onLoadContent(List<Content> contents);
    }

    public interface Presenter extends IPresenter<View> {

        void getContent(boolean isRefresh);

        void getMoreContent();
    }
}
