package com.jeremyliao.android.scaffold;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jeremyliao.android.scaffold.litho.ComponentLithoDemo;
import com.jeremyliao.android.scaffold.litho.ListLithoDemo;
import com.jeremyliao.android.scaffold.litho.SimpleLithoDemo;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DemoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter = new DemoAdapter(Arrays.asList(DEMO_DATA));
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<DemoData>() {
            @Override
            public void onItemClick(View view, int position, DemoData data) {
                startActivity(new Intent(MainActivity.this, data.activityClass));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    static class DemoData {
        final String title;
        final Class<? extends Activity> activityClass;

        DemoData(String title, Class<? extends Activity> activityClass) {
            this.title = title;
            this.activityClass = activityClass;
        }
    }

    static class DemoAdapter extends QuickAdapter<DemoData> {

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

    final DemoData[] DEMO_DATA = {
            new DemoData("SimpleLithoDemo", SimpleLithoDemo.class),
            new DemoData("ComponentLithoDemo", ComponentLithoDemo.class),
            new DemoData("ListLithoDemo", ListLithoDemo.class),
    };
}
