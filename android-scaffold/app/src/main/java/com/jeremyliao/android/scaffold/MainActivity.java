package com.jeremyliao.android.scaffold;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.broadcast.LocalBroadcastDemoActivity;
import com.jeremyliao.android.scaffold.databinding.activity.DataBindingDemoActivity;
import com.jeremyliao.android.scaffold.databinding.fragment.DataBindingFragmentDemo;
import com.jeremyliao.android.scaffold.databinding.view.DemoInfoViewActivity;
import com.jeremyliao.android.scaffold.databinding.vmshare.ViewModelShareDemo;
import com.jeremyliao.android.scaffold.design.DesignDemoActivity;
import com.jeremyliao.android.scaffold.news.modules.home.NewsHomeActivity;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.LoadMoreRecyclerDemo;
import com.jeremyliao.android.scaffold.recyclerview.normal.RecyclerViewNormalDemo;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;
import com.jeremyliao.android.scaffold.recyclerview.quick.RecyclerViewQuickDemo;
import com.jeremyliao.android.scaffold.robolectric.activity.UTMainActivity;

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
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new DemoAdapter(Arrays.asList(DEMO_DATA));
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<DemoData>() {
            @Override
            public void onItemClick(View view, int position, DemoData data) {
                startActivity(new Intent(MainActivity.this, data.activityClass));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        startService();
    }

    private void startService() {
        Intent intent = new Intent("intent.action.INTER_PROCESS");
        intent.setPackage("com.jeremyliao.android.scaffold");
        startService(intent);
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
            new DemoData("Normal RecyclerView", RecyclerViewNormalDemo.class),
            new DemoData("Quick RecyclerView", RecyclerViewQuickDemo.class),
            new DemoData("LoadMore Recycler", LoadMoreRecyclerDemo.class),
            new DemoData("DataBinding", DataBindingDemoActivity.class),
            new DemoData("DataBinding Fragment", DataBindingFragmentDemo.class),
            new DemoData("DemoInfoView", DemoInfoViewActivity.class),
            new DemoData("LocalBroadcast", LocalBroadcastDemoActivity.class),
            new DemoData("ViewModel Share", ViewModelShareDemo.class),
            new DemoData("Unit Test", UTMainActivity.class),
            new DemoData("Android Design", DesignDemoActivity.class),
            new DemoData("News Home", NewsHomeActivity.class),
    };
}
