package com.jeremyliao.android.scaffold.news.modules.newslist;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentNewsListBinding;
import com.jeremyliao.android.scaffold.news.adapter.NewsContentAdapter;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;

import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class NewsListFragment extends Fragment implements NewsListContract.View {

    private static final String KEY_CATEGORY_ID = "KEY_CATEGORY_ID";

    FragmentNewsListBinding binding;
    NewsListViewModel viewModel;

    private NewsListPresenter presenter;
    private String categoryId;
    private NewsContentAdapter adapter;

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
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(NewsListViewModel.class);
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
        binding.rvNewsList.setAdapter(adapter);
        presenter = new NewsListPresenter(this, categoryId);
        presenter.getContent(true);
    }

    @Override
    public void onLoadContent(List<Content> contents) {
        adapter.setDatas(contents);
    }

    @Override
    public NewsListContract.Presenter getPresenter() {
        return presenter;
    }
}
