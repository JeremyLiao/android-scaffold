package com.jeremyliao.android.scaffold.recyclerview.loadmore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.loadmore.LoadMoreAdapter;
import com.jeremyliao.android.scaffold.recyclerview.loadmore.loadmore.OnLoadMoreListener;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoadMoreRecyclerDemo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NormalAdapter adapter;
    private boolean allowLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new NormalAdapter(null);
        adapter.setOnItemClickListener(new QuickAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(LoadMoreRecyclerDemo.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }

            @Override
            public boolean allowLoadMore() {
                return allowLoadMore;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        Observable
                .fromCallable(new Callable<List<String>>() {
                    @Override
                    public List<String> call() throws Exception {
                        Thread.sleep(1000);
                        return getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        adapter.setDatas(strings);
                        allowLoadMore = strings.size() < 50;
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void loadMoreData() {
        Observable
                .fromCallable(new Callable<List<String>>() {
                    @Override
                    public List<String> call() throws Exception {
                        Thread.sleep(1000);
                        return getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        List<String> datas = new ArrayList<>(adapter.getDatas());
                        datas.addAll(strings);
                        adapter.setDatas(datas);
                        adapter.notifyLoadFinished();
                        allowLoadMore = datas.size() < 50;
                    }
                });
    }

    private List<String> getData() {
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

    private class NormalAdapter extends LoadMoreAdapter<String> {
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
