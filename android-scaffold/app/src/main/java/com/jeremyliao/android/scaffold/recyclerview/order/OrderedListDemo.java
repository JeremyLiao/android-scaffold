package com.jeremyliao.android.scaffold.recyclerview.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityOrderedListDemoBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class OrderedListDemo extends AppCompatActivity {

    private ActivityOrderedListDemoBinding binding;
    private IntegerAdapter adapter;
    private List<Integer> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ordered_list_demo);
        binding.setLifecycleOwner(this);
        binding.setHandler(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        datas = getData(30, 100);
        adapter = new IntegerAdapter(datas);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void onAsc() {
        Collections.sort(datas, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        adapter.setDatas(datas);
    }

    public void onDes() {
        Collections.sort(datas, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        adapter.setDatas(datas);
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
            return R.layout.item_1;
        }

        @Override
        public void convert(ViewHolder holder, Integer data, int position) {
            holder.setText(R.id.tv_title, String.valueOf(data));
        }
    }

}
