package com.jeremyliao.android.scaffold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.animation.base.AnimationBaseActivity;
import com.jeremyliao.android.scaffold.broadcast.LocalBroadcastDemoActivity;
import com.jeremyliao.android.scaffold.databinding.ActivityMainBinding;
import com.jeremyliao.android.scaffold.databinding.activity.DataBindingDemoActivity;
import com.jeremyliao.android.scaffold.databinding.doubleclick.DataBindingDoubleClickActivity;
import com.jeremyliao.android.scaffold.databinding.fragment.DataBindingFragmentDemo;
import com.jeremyliao.android.scaffold.databinding.observable.ObservableDemoActivity;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.illegal.IllegalTestActivity;
import com.jeremyliao.android.scaffold.databinding.livedata.LiveDataActivity;
import com.jeremyliao.android.scaffold.databinding.view.DemoInfoViewActivity;
import com.jeremyliao.android.scaffold.databinding.vmshare.ViewModelShareDemo;
import com.jeremyliao.android.scaffold.design.demo.DesignDemoActivity;
import com.jeremyliao.android.scaffold.design.popup.PopupWindowActivity;
import com.jeremyliao.android.scaffold.design.shadow.ShadowDemoActivity;
import com.jeremyliao.android.scaffold.design.toolbar.simpleuse.SimpleToolBarActivity;
import com.jeremyliao.android.scaffold.design.toolbar.styles.StylesToolBarActivity;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.activity.DialogDemoActivity;
import com.jeremyliao.android.scaffold.dialog.dialogfragment.activity.DialogThemeActivity;
import com.jeremyliao.android.scaffold.kt.activity.KotlinMainActivity;
import com.jeremyliao.android.scaffold.news.modules.home.NewsHomeActivity;
import com.jeremyliao.android.scaffold.others.ashmem.AshmemActivity;
import com.jeremyliao.android.scaffold.others.sl.LoaderDemoActivity;
import com.jeremyliao.android.scaffold.paint.crhf.CameraRotateHittingFaceActivity;
import com.jeremyliao.android.scaffold.paint.crhf.CameraRotateHittingFaceView;
import com.jeremyliao.android.scaffold.paint.flipboard.FlipBoardActivity;
import com.jeremyliao.android.scaffold.paint.paintbase.PaintBaseActivity;
import com.jeremyliao.android.scaffold.permission.PermissionFragmentDemo;
import com.jeremyliao.android.scaffold.permission.PermissionRequestActivity;
import com.jeremyliao.android.scaffold.recyclerview.decoration.RecyclerViewDecorationDemo;
import com.jeremyliao.android.scaffold.recyclerview.grid.RecyclerGridDemo;
import com.jeremyliao.android.scaffold.recyclerview.group.GroupDecoration;
import com.jeremyliao.android.scaffold.recyclerview.group.GroupDecorationAdapter;
import com.jeremyliao.android.scaffold.recyclerview.group.RecyclerViewGroupDemo;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.LoadMoreRecyclerDemo;
import com.jeremyliao.android.scaffold.recyclerview.multitype.MultiTypeListDemo;
import com.jeremyliao.android.scaffold.recyclerview.normal.RecyclerViewNormalDemo;
import com.jeremyliao.android.scaffold.recyclerview.order.OrderedListDemo;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;
import com.jeremyliao.android.scaffold.recyclerview.quick.RecyclerViewDiffDemo;
import com.jeremyliao.android.scaffold.recyclerview.quick.RecyclerViewQuickDemo;
import com.jeremyliao.android.scaffold.recyclerview.refresh.RefreshDemo;
import com.jeremyliao.android.scaffold.reuse.mvp.MvpCategoryListActivity;
import com.jeremyliao.android.scaffold.reuse.mvp.MvpCategoryTextActivity;
import com.jeremyliao.android.scaffold.reuse.mvvm.MvvmCategoryListActivity;
import com.jeremyliao.android.scaffold.reuse.mvvm.MvvmCategoryTextActivity;
import com.jeremyliao.android.scaffold.robolectric.activity.UTMainActivity;
import com.jeremyliao.android.scaffold.rxjava2.RxJavaDemoActivity;

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
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<DemoData>() {
            @Override
            public void onItemClick(View view, int position, DemoData data) {
                startActivity(new Intent(MainActivity.this, data.activityClass));
            }
        });
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new GroupDecoration(new DemoGroupAdapter(this)));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
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
            new GroupData("Animation",
                    new DemoData[]{
                            new DemoData("Animation Base", AnimationBaseActivity.class),
                    }),
            new GroupData("Paint",
                    new DemoData[]{
                            new DemoData("Paint Base", PaintBaseActivity.class),
                            new DemoData("FlipBoard", FlipBoardActivity.class),
                            new DemoData("CameraRotateHittingFace", CameraRotateHittingFaceActivity.class),
                    }),
            new GroupData("Dialog",
                    new DemoData[]{
                            new DemoData("Dialog Themes", DialogThemeActivity.class),
                            new DemoData("Dialogs", DialogDemoActivity.class),
                            new DemoData("IllegalStateException", IllegalTestActivity.class),
                    }),
            new GroupData("RecyclerView",
                    new DemoData[]{
                            new DemoData("Normal RecyclerView", RecyclerViewNormalDemo.class),
                            new DemoData("Quick RecyclerView", RecyclerViewQuickDemo.class),
                            new DemoData("LoadMore Recycler", LoadMoreRecyclerDemo.class),
                            new DemoData("RecyclerView Decoration", RecyclerViewDecorationDemo.class),
                            new DemoData("RecyclerView Group", RecyclerViewGroupDemo.class),
                            new DemoData("Ordered List", OrderedListDemo.class),
                            new DemoData("Grid Demo", RecyclerGridDemo.class),
                            new DemoData("Recycler Diff Demo", RecyclerViewDiffDemo.class),
                            new DemoData("Recycler Refresh Demo", RefreshDemo.class),
                            new DemoData("MultiType List Demo", MultiTypeListDemo.class),
                    }),
            new GroupData("DataBinding",
                    new DemoData[]{
                            new DemoData("DataBinding", DataBindingDemoActivity.class),
                            new DemoData("DataBinding Fragment", DataBindingFragmentDemo.class),
                            new DemoData("DemoInfoView", DemoInfoViewActivity.class),
                            new DemoData("ViewModel Share", ViewModelShareDemo.class),
                            new DemoData("Double Click", DataBindingDoubleClickActivity.class),
                            new DemoData("LiveData", LiveDataActivity.class),
                            new DemoData("Observable", ObservableDemoActivity.class),
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
            new GroupData("Permission",
                    new DemoData[]{
                            new DemoData("Permission Request by Activity", PermissionRequestActivity.class),
                            new DemoData("Permission Request by Fragment", PermissionFragmentDemo.class),
                    }),
            new GroupData("Design",
                    new DemoData[]{
                            new DemoData("Simple ToolBar", SimpleToolBarActivity.class),
                            new DemoData("Styles ToolBar", StylesToolBarActivity.class),
                            new DemoData("Popup Window", PopupWindowActivity.class),
                            new DemoData("Shadow Demo", ShadowDemoActivity.class),
                    }),
            new GroupData("Kotlin",
                    new DemoData[]{
                            new DemoData("Kotlin Main", KotlinMainActivity.class),
                    }),
            new GroupData("Others",
                    new DemoData[]{
                            new DemoData("LocalBroadcast", LocalBroadcastDemoActivity.class),
                            new DemoData("Unit Test", UTMainActivity.class),
                            new DemoData("Android Design", DesignDemoActivity.class),
                            new DemoData("RxJava", RxJavaDemoActivity.class),
                            new DemoData("Shared Memory", AshmemActivity.class),
                            new DemoData("Service Loader", LoaderDemoActivity.class),
                    }),

    };
}
