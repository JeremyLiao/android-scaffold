package com.jeremyliao.android.scaffold.recyclerview.refresh;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.algorithm.dp.Lis;
import com.jeremyliao.android.scaffold.databinding.ActivityRecyclerDiffDemoBinding;
import com.jeremyliao.android.scaffold.databinding.ActivityRecyclerRefreshDemoBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class RefreshDemo extends AppCompatActivity {

    ActivityRecyclerRefreshDemoBinding binding;
    NormalAdapter adapter;
    boolean isUseFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_refresh_demo);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new NormalAdapter(getStrData());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void wholeUpdate() {
        adapter.setDatas(getStrData());
    }

    public void diffUpdate() {
        adapter.setDatasWithDiff(getStrData());
    }

    private List<String> getStrData() {
        isUseFirst = !isUseFirst;
        List<String> datas = new ArrayList<>();
        int count = isUseFirst ? 30 : 32;
        for (int i = 0; i < count; i++) {
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
            return R.layout.item_1;
        }

        @Override
        public void convert(ViewHolder holder, String data, int position) {
            holder.setText(R.id.tv_title, data);
        }
    }
}
