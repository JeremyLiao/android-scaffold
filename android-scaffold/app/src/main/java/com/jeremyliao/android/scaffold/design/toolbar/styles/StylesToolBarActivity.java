package com.jeremyliao.android.scaffold.design.toolbar.styles;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityStylesToolbarBinding;

public class StylesToolBarActivity extends AppCompatActivity {

    ActivityStylesToolbarBinding binding;
    private String[] titles = new String[]{"最新", "热门", "我的"};
    private String[] longTitles = new String[]{"推荐", "热点", "北京", "视频", "社会", "图片", "娱乐", "科技", "汽车"};
    private int[] pics = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_styles_toolbar);
        binding.setLifecycleOwner(this);
        init();
    }

    private void init() {
        setTab1();
        setTab2();
        setTab3();
        setDefaultIcon();
        setCustomIcon();
        setScroll();
    }

    private void setTab1() {
        for (int i = 0; i < titles.length; i++) {
            binding.tab1.addTab(binding.tab1.newTab());
            binding.tab1.getTabAt(i).setText(titles[i]);
        }
    }

    private void setTab2() {
        for (int i = 0; i < titles.length; i++) {
            binding.tab2.addTab(binding.tab2.newTab());
            binding.tab2.getTabAt(i).setText(titles[i]);
        }
    }

    private void setTab3() {
        for (int i = 0; i < titles.length; i++) {
            binding.tab3.addTab(binding.tab3.newTab());
            binding.tab3.getTabAt(i).setText(titles[i]);
        }

        binding.tab3.post(new Runnable() {
            @Override
            public void run() {
                IndicatorLineUtil.setIndicator(binding.tab3, 40, 40);
            }
        });
    }

    /**
     * 设置默认图标
     */
    private void setDefaultIcon() {

        for (int i = 0; i < titles.length; i++) {
            binding.tablayout.addTab(binding.tablayout.newTab());
        }

        for (int i = 0; i < titles.length; i++) {
            binding.tablayout.getTabAt(i).setText(titles[i]).setIcon(pics[i]);
        }
    }

    /**
     * 设置自定义位置图标
     */
    private void setCustomIcon() {

        for (int i = 0; i < titles.length; i++) {
            binding.tablayout2.addTab(binding.tablayout2.newTab());
        }

        for (int i = 0; i < titles.length; i++) {
            binding.tablayout2.getTabAt(i).setCustomView(makeTabView(i));
        }
    }

    private void setScroll() {
        for (int i = 0; i < longTitles.length; i++) {
            binding.tablayout3.addTab(binding.tablayout3.newTab());
        }

        for (int i = 0; i < longTitles.length; i++) {
            binding.tablayout3.getTabAt(i).setText(longTitles[i]);
        }
    }

    /**
     * 引入布局设置图标和标题
     *
     * @param position
     * @return
     */
    private View makeTabView(int position) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.tab_text_icon, null);
        TextView textView = tabView.findViewById(R.id.text_view);
        ImageView imageView = tabView.findViewById(R.id.image_view);
        textView.setText(titles[position]);
        imageView.setImageResource(pics[position]);

        return tabView;
    }
}
