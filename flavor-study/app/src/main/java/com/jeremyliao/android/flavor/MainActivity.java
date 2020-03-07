package com.jeremyliao.android.flavor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jeremyliao.android.flavor.base.recyclerview.GroupDecoration;
import com.jeremyliao.android.flavor.base.recyclerview.GroupDecorationAdapter;
import com.jeremyliao.android.flavor.base.recyclerview.QuickAdapter;
import com.jeremyliao.android.flavor.bean.DemoData;
import com.jeremyliao.android.flavor.bean.GroupData;
import com.jeremyliao.android.flavor.databinding.ActivityMainBinding;
import com.jeremyliao.android.flavor.datasource.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        DemoAdapter adapter = new DemoAdapter(getDemoDatas());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new GroupDecoration(new DemoGroupAdapter(this)));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<DemoData> getDemoDatas() {
        List<DemoData> demoDataList = new ArrayList<>();
        for (int i = 0; i < DataSource.DATA.length; i++) {
            demoDataList.addAll(Arrays.asList(DataSource.DATA[i].datas));
        }
        return demoDataList;
    }

    private class DemoAdapter extends QuickAdapter<DemoData> {

        public DemoAdapter(List<DemoData> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_demo;
        }

        @Override
        public void convert(ViewHolder holder, DemoData data, int position) {
            holder.getView(R.id.tv_title, TextView.class).setText(data.title);
        }
    }

    private class DemoGroupAdapter implements GroupDecorationAdapter {

        private final Context context;
        private Map<Integer, Integer> startPositionMap = new HashMap<>();

        public DemoGroupAdapter(Context context) {
            this.context = context;
            initStartPositionMap();
        }

        private void initStartPositionMap() {
            int size = 0;
            for (int i = 0; i < DataSource.DATA.length; i++) {
                startPositionMap.put(i, size);
                size += DataSource.DATA[i].datas.length;
            }
        }

        @Override
        public View onCreateGroupView() {
            return LayoutInflater.from(context).inflate(R.layout.item_3, null);
        }

        @Override
        public void onBindGroupView(View groupView, int groupPosition) {
            GroupData groupData = DataSource.DATA[groupPosition];
            ((TextView) (groupView.findViewById(R.id.tv_title))).setText(groupData.groupName);
        }

        @Override
        public int getGroupCount() {
            return DataSource.DATA.length;
        }

        @Override
        public int getStartPositionForGroup(int groupPosition) {
            return startPositionMap.get(groupPosition);
        }

        @Override
        public boolean isStickyHeader() {
            return true;
        }
    }
}
