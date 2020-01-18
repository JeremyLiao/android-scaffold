package com.jeremyliao.android.scaffold.news.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;
import com.jeremyliao.android.scaffold.recyclerview.quick.QuickAdapter;

import java.util.List;

/**
 * Created by liaohailiang on 2020-01-17.
 */
public class NewsContentAdapter extends QuickAdapter<Content> {

    private final Context context;

    public NewsContentAdapter(List<Content> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.view_news_info_item;
    }

    @Override
    public void convert(ViewHolder holder, Content content, int position) {
        holder.getView(R.id.tv_title, TextView.class).setText(content.getTitle());
        holder.getView(R.id.tv_author, TextView.class).setText(content.getSite().getName());
        holder.getView(R.id.tv_time, TextView.class).setText(content.getCreated_at());
        Glide.with(context)
                .load(content.getSite().getIcon())
                .into(holder.getView(R.id.img_sub_category, ImageView.class));
    }
}
