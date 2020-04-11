package com.jeremyliao.android.scaffold.design.popup;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentItemGridBinding;
import com.jeremyliao.android.scaffold.recyclerview.decoration.DemoDecoration;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liaohailiang on 2019-05-24.
 */
public class ItemShowView extends FrameLayout {

    FragmentItemGridBinding binding;
    private ItemShowAdapter adapter;

    public ItemShowView(Context context) {
        super(context);
        init(context);
    }

    public ItemShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemShowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_item_grid,
                this, true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        binding.recyclerView.setLayoutManager(layoutManager);
        List<Integer> datas = getData(8, 100);
        adapter = new ItemShowAdapter(datas);
        binding.recyclerView.setAdapter(adapter);
    }

    private List<Integer> getData(int count, int bound) {
        Random rand = new Random();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(rand.nextInt(bound));
        }
        return data;
    }

    private static class ItemShowAdapter extends QuickAdapter<Integer> {
        public ItemShowAdapter(List<Integer> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_4;
        }

        @Override
        public void convert(QuickAdapter.ViewHolder holder, Integer data, int position) {
            holder.setText(R.id.tv_title, String.valueOf(data));
        }
    }
}
