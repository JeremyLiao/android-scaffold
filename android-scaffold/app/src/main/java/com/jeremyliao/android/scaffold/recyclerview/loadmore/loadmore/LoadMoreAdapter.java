package com.jeremyliao.android.scaffold.recyclerview.loadmore.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.List;

/**
 * Created by liaohailiang on 2019-06-19.
 */
public abstract class LoadMoreAdapter<T> extends QuickAdapter<T> {

    private boolean isAllowLoadMore = true;//是否开启加载更多

    private boolean isLoading;//是否正在加载更多

    private OnLoadMoreListener loadMoreListener;

    public LoadMoreAdapter(List<T> datas) {
        super(datas);
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        }
        startLoadMore(recyclerView, layoutManager);
    }

    private void startLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        if (!isAllowLoadMore || loadMoreListener == null) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("LoadMoreAdapter", "onScrollStateChanged: " + newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isLoading) {
                        return;
                    }
                    if (!loadMoreListener.allowLoadMore()) {
                        return;
                    }
                    if (findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                        scrollLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("LoadMoreAdapter", "onScrolled");
                if (findLastVisibleItemPosition(layoutManager) + 1 == getItemCount()) {
                    if (datas.isEmpty()) {
                        return;
                    }
                    if (isLoading) {
                        return;
                    }
                    if (!loadMoreListener.allowLoadMore()) {
                        return;
                    }
                    scrollLoadMore();
                }
            }
        });
    }

    /**
     * 到达底部开始刷新
     */
    private void scrollLoadMore() {
        Log.d("LoadMoreAdapter", "scrollLoadMore");
        if (isLoading) {
            return;
        }
        isLoading = true;
        if (loadMoreListener != null) {
            loadMoreListener.onLoadMore();
        }
    }

    public void notifyLoadFinished() {
        isLoading = false;
    }

    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return Util.findMax(lastVisibleItemPositions);
        }
        return -1;
    }
}
