package com.jeremyliao.android.scaffold.recyclerview.normal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewNormalDemo extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        List<String> data = initData();
        recyclerView.setAdapter(new NormalAdapter(data));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        return data;
    }

    public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
        public class VH extends RecyclerView.ViewHolder {
            public final TextView title;

            public VH(View v) {
                super(v);
                title = v.findViewById(R.id.tv_title);
            }
        }

        private List<String> mDatas;

        public NormalAdapter(List<String> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.title.setText(mDatas.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //item 点击事件
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            //LayoutInflater.from指定写法
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
            return new VH(v);
        }
    }
}
