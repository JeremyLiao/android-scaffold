package com.jeremyliao.android.scaffold.reuse.mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityReuseListBinding;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class MvpCategoryListActivity extends AppCompatActivity implements CategoryContract.View {


    private ActivityReuseListBinding binding;
    private CategoryPresenter presenter;
    private CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reuse_list);
        binding.setLifecycleOwner(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvCategoryList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new CategoryAdapter(null);
        binding.rvCategoryList.setAdapter(adapter);
        binding.rvCategoryList.setItemAnimator(new DefaultItemAnimator());
        presenter = new CategoryPresenter(this);
        presenter.getCategories();
    }

    @Override
    public void onLoadCategories(List<Category> categories) {
        adapter.setDatas(categories);
    }

    private static class CategoryAdapter extends QuickAdapter<Category> {

        public CategoryAdapter(List<Category> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_demo;
        }


        @Override
        public void convert(ViewHolder holder, Category data, int position) {
            holder.getView(R.id.tv_title, TextView.class).setText(data.getName());
        }
    }
}
