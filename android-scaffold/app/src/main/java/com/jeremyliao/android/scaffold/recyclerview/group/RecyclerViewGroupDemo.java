package com.jeremyliao.android.scaffold.recyclerview.group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewGroupDemo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NormalAdapter adapter;
    private DemoGroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List<String> data = initData();
        adapter = new NormalAdapter(data);
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(RecyclerViewGroupDemo.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        groupAdapter = new DemoGroupAdapter();
        recyclerView.addItemDecoration(new GroupDecoration(groupAdapter));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("f");
        data.add("g");
        data.add("h");
        data.add("i");
        data.add("j");
        data.add("k");
        data.add("l");
        data.add("m");
        data.add("n");
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("f");
        data.add("g");
        data.add("h");
        data.add("i");
        data.add("j");
        data.add("k");
        data.add("l");
        data.add("m");
        data.add("n");
        return data;
    }

    private class DemoGroupAdapter implements GroupDecorationAdapter {

        private Map<Integer, Integer> startPositionMap = new HashMap<>();
        private List<String> groupDatas = new ArrayList<>();

        public DemoGroupAdapter() {
            groupDatas.add("Group1");
            groupDatas.add("Group2");
            groupDatas.add("Group3");
            groupDatas.add("Group4");
            startPositionMap.put(0, 0);
            startPositionMap.put(1, 5);
            startPositionMap.put(2, 8);
            startPositionMap.put(3, 15);
        }

        @Override
        public View onCreateGroupView() {
            return LayoutInflater.from(RecyclerViewGroupDemo.this).inflate(R.layout.item_3, null);
        }

        @Override
        public void onBindGroupView(View groupView, int groupPosition) {
            String s = groupDatas.get(groupPosition);
            ((TextView) (groupView.findViewById(R.id.tv_title))).setText(s);
        }

        @Override
        public int getGroupCount() {
            return startPositionMap.size();
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
