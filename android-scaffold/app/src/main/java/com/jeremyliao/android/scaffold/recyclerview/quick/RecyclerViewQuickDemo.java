package com.jeremyliao.android.scaffold.recyclerview.quick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewQuickDemo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NormalAdapter adapter;

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
                Toast.makeText(RecyclerViewQuickDemo.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position, String data) {

            }
        });
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
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
        return data;
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
