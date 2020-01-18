package com.jeremyliao.android.scaffold.news.modules.newsmain;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentNewsMainBinding;
import com.jeremyliao.android.scaffold.news.adapter.NewsPagerAdapter;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;
import com.jeremyliao.android.scaffold.news.beans.gank.SubCategory;
import com.jeremyliao.android.scaffold.news.modules.newslist.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class NewsMainFragment extends Fragment implements NewsMainContract.View {

    private static final String KEY_CATEGORY = "KEY_CATEGORY";

    FragmentNewsMainBinding binding;
    NewsMainViewModel viewModel;

    private NewsPagerAdapter pagerAdapter;
    private NewsMainPresenter presenter;
    private Category category;

    public static NewsMainFragment newInstance(Category category) {
        NewsMainFragment fragment = new NewsMainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = (Category) getArguments().getSerializable(KEY_CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_main, container, false);
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory()).get(NewsMainViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.toolBar.setTitle(category.getName());
        FragmentActivity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).setSupportActionBar(binding.toolBar);
        }
        pagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        presenter = new NewsMainPresenter(this);
        presenter.getSubCategories(category.getEn_name());
    }

    @Override
    public void onLoadCategories(List<SubCategory> subCategories) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (SubCategory subCategory : subCategories) {
            titles.add(subCategory.getTitle());
            fragments.add(NewsListFragment.newInstance(subCategory.getId()));
        }
        pagerAdapter.setItems(fragments, titles);
    }

    @Override
    public NewsMainContract.Presenter getPresenter() {
        return presenter;
    }
}
