package com.jeremyliao.android.scaffold.recyclerview.loadmore.loadmore;

/**
 * Created by liaohailiang on 2019-06-19.
 */
public interface OnLoadMoreListener {

    /**
     * 加载更多的回调方法
     *
     */
    void onLoadMore();

    /**
     *
     * @return
     */
    boolean allowLoadMore();
}
