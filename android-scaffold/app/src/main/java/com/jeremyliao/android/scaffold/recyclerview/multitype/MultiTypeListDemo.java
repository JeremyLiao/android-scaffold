package com.jeremyliao.android.scaffold.recyclerview.multitype;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityRecyclerMultiDemoBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeListDemo extends AppCompatActivity {

    ActivityRecyclerMultiDemoBinding binding;
    NormalAdapter adapter;
    private int specialIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_multi_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new NormalAdapter(getStrData());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void update() {
        specialIndex = (specialIndex + 1) % 30;
        adapter.notifyDataSetChanged();
    }

    private List<String> getStrData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add(String.valueOf(i));
        }
        return datas;
    }

    private class NormalAdapter extends QuickAdapter<String> {
        public NormalAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            if (viewType == 1) {
                return R.layout.item_1_1;
            }
            return R.layout.item_1;
        }


        @Override
        public void convert(ViewHolder holder, String data, int position) {
            if (position == specialIndex) {
                holder.setText(R.id.tv_main_title, data);
            } else {
                holder.setText(R.id.tv_title, data);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == specialIndex) {
                return 1;
            }
            return 0;
        }
    }
}
