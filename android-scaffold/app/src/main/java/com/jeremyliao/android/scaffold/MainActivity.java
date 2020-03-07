package com.jeremyliao.android.scaffold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.broadcast.LocalBroadcastDemoActivity;
import com.jeremyliao.android.scaffold.databinding.activity.DataBindingDemoActivity;
import com.jeremyliao.android.scaffold.databinding.doubleclick.DataBindingDoubleClickActivity;
import com.jeremyliao.android.scaffold.databinding.fragment.DataBindingFragmentDemo;
import com.jeremyliao.android.scaffold.databinding.view.DemoInfoViewActivity;
import com.jeremyliao.android.scaffold.databinding.vmshare.ViewModelShareDemo;
import com.jeremyliao.android.scaffold.design.DesignDemoActivity;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.activity.DialogDemoActivity;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.activity.DialogThemeActivity;
import com.jeremyliao.android.scaffold.news.modules.home.NewsHomeActivity;
import com.jeremyliao.android.scaffold.recyclerview.decoration.RecyclerViewDecorationDemo;
import com.jeremyliao.android.scaffold.recyclerview.group.GroupDecoration;
import com.jeremyliao.android.scaffold.recyclerview.group.GroupDecorationAdapter;
import com.jeremyliao.android.scaffold.recyclerview.group.RecyclerViewGroupDemo;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.LoadMoreRecyclerDemo;
import com.jeremyliao.android.scaffold.recyclerview.normal.RecyclerViewNormalDemo;
import com.jeremyliao.android.scaffold.recyclerview.order.OrderedListDemo;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;
import com.jeremyliao.android.scaffold.recyclerview.quick.RecyclerViewQuickDemo;
import com.jeremyliao.android.scaffold.reuse.mvp.MvpCategoryListActivity;
import com.jeremyliao.android.scaffold.reuse.mvp.MvpCategoryTextActivity;
import com.jeremyliao.android.scaffold.reuse.mvvm.MvvmCategoryListActivity;
import com.jeremyliao.android.scaffold.reuse.mvvm.MvvmCategoryTextActivity;
import com.jeremyliao.android.scaffold.robolectric.activity.UTMainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        DemoAdapter adapter = new DemoAdapter(getDemoDatas());
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<DemoData>() {
            @Override
            public void onItemClick(View view, int position, DemoData data) {
                startActivity(new Intent(MainActivity.this, data.activityClass));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GroupDecoration(new DemoGroupAdapter(this)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        startService();
    }

    private void startService() {
        Intent intent = new Intent("intent.action.INTER_PROCESS");
        intent.setPackage("com.jeremyliao.android.scaffold");
        startService(intent);
    }

    private List<DemoData> getDemoDatas() {
        List<DemoData> demoDataList = new ArrayList<>();
        for (int i = 0; i < DATA.length; i++) {
            demoDataList.addAll(Arrays.asList(DATA[i].datas));
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
            for (int i = 0; i < DATA.length; i++) {
                startPositionMap.put(i, size);
                size += DATA[i].datas.length;
            }
        }

        @Override
        public View onCreateGroupView() {
            return LayoutInflater.from(context).inflate(R.layout.item_3, null);
        }

        @Override
        public void onBindGroupView(View groupView, int groupPosition) {
            GroupData groupData = DATA[groupPosition];
            ((TextView) (groupView.findViewById(R.id.tv_title))).setText(groupData.groupName);
        }

        @Override
        public int getGroupCount() {
            return DATA.length;
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

    private static class DemoData {
        final String title;
        final Class<? extends Activity> activityClass;

        DemoData(String title, Class<? extends Activity> activityClass) {
            this.title = title;
            this.activityClass = activityClass;
        }
    }

    private static class GroupData {
        final String groupName;
        final DemoData[] datas;

        public GroupData(String groupName, DemoData[] datas) {
            this.groupName = groupName;
            this.datas = datas;
        }
    }

    private static final GroupData[] DATA = {
            new GroupData("Dialog",
                    new DemoData[]{
                            new DemoData("Dialog Themes", DialogThemeActivity.class),
                            new DemoData("Dialogs", DialogDemoActivity.class),
                    }),
            new GroupData("RecyclerView",
                    new DemoData[]{
                            new DemoData("Normal RecyclerView", RecyclerViewNormalDemo.class),
                            new DemoData("Quick RecyclerView", RecyclerViewQuickDemo.class),
                            new DemoData("LoadMore Recycler", LoadMoreRecyclerDemo.class),
                            new DemoData("RecyclerView Decoration", RecyclerViewDecorationDemo.class),
                            new DemoData("RecyclerView Group", RecyclerViewGroupDemo.class),
                            new DemoData("Ordered List", OrderedListDemo.class),
                    }),
            new GroupData("DataBinding",
                    new DemoData[]{
                            new DemoData("DataBinding", DataBindingDemoActivity.class),
                            new DemoData("DataBinding Fragment", DataBindingFragmentDemo.class),
                            new DemoData("DemoInfoView", DemoInfoViewActivity.class),
                            new DemoData("ViewModel Share", ViewModelShareDemo.class),
                            new DemoData("Double Click", DataBindingDoubleClickActivity.class),
                    }),
            new GroupData("Reuse",
                    new DemoData[]{
                            new DemoData("Reuse Mvp List", MvpCategoryListActivity.class),
                            new DemoData("Reuse Mvp Text", MvpCategoryTextActivity.class),
                            new DemoData("Reuse Mvvm List", MvvmCategoryListActivity.class),
                            new DemoData("Reuse Mvvm Text", MvvmCategoryTextActivity.class),
                    }),
            new GroupData("News",
                    new DemoData[]{
                            new DemoData("News Home", NewsHomeActivity.class),
                    }),
            new GroupData("Others",
                    new DemoData[]{
                            new DemoData("LocalBroadcast", LocalBroadcastDemoActivity.class),
                            new DemoData("Unit Test", UTMainActivity.class),
                            new DemoData("Android Design", DesignDemoActivity.class),
                    }),

    };
}
