package com.jeremyliao.android.scaffold.news.modules.newslist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentNewsListBinding;
import com.jeremyliao.android.scaffold.news.adapter.NewsContentAdapter;
import com.jeremyliao.android.scaffold.news.base.BaseFragment;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;
import com.jeremyliao.android.scaffold.news.modules.detail.NewsDetailActivity;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.loadmore.OnLoadMoreListener;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class NewsListFragment extends BaseFragment implements NewsListContract.View {

    private static final String KEY_CATEGORY_ID = "KEY_CATEGORY_ID";

    private FragmentNewsListBinding binding;
    private NewsListViewModel viewModel;

    private NewsListPresenter presenter;
    private String categoryId;
    private NewsContentAdapter adapter;
    private boolean allowLoadMore = false;

    public static NewsListFragment newInstance(String id) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CATEGORY_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryId = getArguments().getString(KEY_CATEGORY_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false);
        viewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        init();
        return binding.getRoot();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        binding.rvNewsList.setLayoutManager(layoutManager);
        adapter = new NewsContentAdapter(null, getContext());
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<Content>() {
            @Override
            public void onItemClick(View view, int position, Content data) {
                NewsDetailActivity.launch(getContext(), data);
            }
        });
        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.getMoreContent();
            }

            @Override
            public boolean allowLoadMore() {
                return allowLoadMore;
            }
        });
        binding.rvNewsList.setAdapter(adapter);
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getContent(true);
            }
        });
        presenter = new NewsListPresenter(this, categoryId);
        presenter.getContent(false);
    }

    @Override
    public void onLoadContent(List<Content> contents) {
        adapter.setDatas(contents);
        binding.swipeRefresh.setRefreshing(false);
        allowLoadMore = true;
    }

    @Override
    public void onLoadMoreContent(List<Content> contents) {
        if (contents != null && contents.size() > 0) {
            List<Content> datas = adapter.getDatas();
            if (datas == null) {
                datas = new ArrayList<>();
            }
            datas.addAll(contents);
            adapter.setDatas(datas);
            allowLoadMore = true;
        } else {
            allowLoadMore = false;
        }
        adapter.notifyLoadFinished();
    }
}
