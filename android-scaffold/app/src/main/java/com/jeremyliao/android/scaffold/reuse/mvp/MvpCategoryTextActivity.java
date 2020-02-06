package com.jeremyliao.android.scaffold.reuse.mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityReuseTextBinding;
import com.jeremyliao.android.scaffold.news.beans.gank.Category;

import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class MvpCategoryTextActivity extends AppCompatActivity implements CategoryContract.View {


    private ActivityReuseTextBinding binding;
    private CategoryPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reuse_text);
        binding.setLifecycleOwner(this);
        presenter = new CategoryPresenter(this);
        presenter.getCategories();
    }

    @Override
    public void onLoadCategories(List<Category> categories) {
        if (categories != null && categories.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Category category : categories) {
                sb.append(category.getName()).append("\n");
            }
            binding.tvContent.setText(sb.toString());
        }
    }
}
