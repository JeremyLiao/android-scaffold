package com.jeremyliao.android.scaffold.recyclerview.grid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityGridDemoBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerGridDemo extends AppCompatActivity {

    private ActivityGridDemoBinding binding;
    private IntegerAdapter adapter;
    private List<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid_demo);
        binding.setLifecycleOwner(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(layoutManager);
        GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(3, 20, false);
        binding.recyclerView.addItemDecoration(decoration);
        datas = getData(30, 100);
        adapter = new IntegerAdapter(datas);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<Integer> getData(int count, int bound) {
        Random rand = new Random();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(rand.nextInt(bound));
        }
        return data;
    }

    private class IntegerAdapter extends QuickAdapter<Integer> {
        public IntegerAdapter(List<Integer> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_4;
        }

        @Override
        public void convert(ViewHolder holder, Integer data, int position) {
            holder.setText(R.id.tv_title, String.valueOf(data));
        }
    }

}
