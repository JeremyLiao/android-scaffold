package com.jeremyliao.android.scaffold.design.toolbar.simpleuse;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivitySimpleToolbarBinding;

import java.util.ArrayList;
import java.util.List;

public class SimpleToolBarActivity extends AppCompatActivity {

    ActivitySimpleToolbarBinding binding;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"最新", "热门", "我的"};
    private FmPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_toolbar);
        binding.setLifecycleOwner(this);
        init();
    }

    private void init() {
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new TabFragment());
            binding.tabLayout.addTab(binding.tabLayout.newTab());
        }

        binding.tabLayout.setupWithViewPager(binding.viewPager, false);
        pagerAdapter = new FmPagerAdapter(fragments, getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < titles.length; i++) {
            binding.tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    static class FmPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public FmPagerAdapter(List<Fragment> fragmentList, FragmentManager fm) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList != null && !fragmentList.isEmpty() ? fragmentList.size() : 0;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
