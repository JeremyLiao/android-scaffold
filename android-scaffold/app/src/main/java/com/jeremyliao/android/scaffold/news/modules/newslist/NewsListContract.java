package com.jeremyliao.android.scaffold.news.modules.newslist;

import com.jeremyliao.android.scaffold.news.base.BasePresenter;
import com.jeremyliao.android.scaffold.news.base.BaseView;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-10.
 */
public class NewsListContract {

    public interface View extends BaseView {

        void onLoadContent(List<Content> contents);

        void onLoadMoreContent(List<Content> contents);

        boolean getUserVisibleHint();
    }

    public interface Presenter extends BasePresenter<View> {

        void getContent(boolean isRefresh);

        void getMoreContent();
    }
}
