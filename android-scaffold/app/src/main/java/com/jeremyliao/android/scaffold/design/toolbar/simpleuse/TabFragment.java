package com.jeremyliao.android.scaffold.design.toolbar.simpleuse;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.FragmentTabBinding;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liaohailiang on 2019-04-23.
 */
public class TabFragment extends Fragment {

    FragmentTabBinding binding;
    private String[] nameDatas = new String[]{"智能", "红润", "日系", "自然", "艺术黑白", "甜美", "蜜粉", "清新", "夏日阳光", "唯美", "蜜粉",};
    private CommonAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab, container, false);
        binding.setLifecycleOwner(this);
        init();
        return binding.getRoot();
    }

    private void init() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter(Arrays.asList(nameDatas));
        binding.recyclerView.setAdapter(adapter);
    }

    private class CommonAdapter extends QuickAdapter<String> {
        public CommonAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_1;
        }

        @Override
        public void convert(QuickAdapter.ViewHolder holder, String data, int position) {
            holder.setText(R.id.tv_title, data);
        }
    }
}
