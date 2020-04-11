package com.jeremyliao.android.scaffold.reuse.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityReuseTextBinding;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class MvvmCategoryTextActivity extends AppCompatActivity {


    private ActivityReuseTextBinding binding;
    private CategoryViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reuse_text);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        viewModel.categories.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                if (categories != null && categories.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Category category : categories) {
                        sb.append(category.getName()).append("\n");
                    }
                    binding.tvContent.setText(sb.toString());
                }
            }
        });
        viewModel.getCategories();
    }
}
