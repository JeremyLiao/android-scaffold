package com.jeremyliao.android.scaffold.news.modules.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityNewsHomeBinding;
import com.jeremyliao.android.scaffold.news.base.BaseActivity;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;
import com.jeremyliao.android.scaffold.news.modules.newsmain.NewsMainFragment;

import java.util.List;

public class NewsHomeActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener, NewsHomeContract.View {

    private final int GROUP_ID = 0;

    private ActivityNewsHomeBinding binding;
    private NewsMainFragment newsMainFragment;

    private NewsHomePresenter presenter;
    private List<Category> categories;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewsHomePresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_home);
        binding.setLifecycleOwner(this);
        initDrawerLayout();
        presenter.getCategories();
    }

    private void initDrawerLayout() {
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
            }
        });
        binding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        int index = menuItem.getItemId();
        if (index != currentIndex) {
            replaceFragment(index);
            currentIndex = index;
        }
        return true;
    }

    private void replaceFragment(int index) {
        newsMainFragment = NewsMainFragment.newInstance(categories.get(index));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, newsMainFragment)
                .commitNow();
    }

    @Override
    public void onLoadCategories(List<Category> categories) {
        if (categories != null && categories.size() > 0) {
            this.categories = categories;
            binding.navView.getMenu().clear();
            for (int i = 0; i < this.categories.size(); i++) {
                Category category = categories.get(i);
                binding.navView.getMenu().add(GROUP_ID, i, i, category.getName());
            }
            replaceFragment(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
