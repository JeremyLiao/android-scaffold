package com.jeremyliao.android.scaffold.recyclerview.quick;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityRecyclerDiffDemoBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDiffDemo extends AppCompatActivity {

    ActivityRecyclerDiffDemoBinding binding;
    NormalAdapter adapter;
    List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_diff_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new NormalAdapter(null);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void addOne() {
        stringList.add(getNewString());
        adapter.setDatasWithDiff(stringList);
    }

    public void deleteOne() {
        if (stringList.isEmpty()) {
            return;
        }
        stringList.remove(0);
        adapter.setDatasWithDiff(stringList);
    }

    private String getNewString() {
        double d = Math.random();
        int i = (int) (d * 100);
        return String.valueOf(i);
    }

    private class NormalAdapter extends QuickAdapter<String> {
        public NormalAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_1;
        }

        @Override
        public void convert(ViewHolder holder, String data, int position) {
            holder.setText(R.id.tv_title, data);
        }
    }
}
