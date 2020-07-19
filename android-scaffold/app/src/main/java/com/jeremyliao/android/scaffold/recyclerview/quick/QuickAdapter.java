package com.jeremyliao.android.scaffold.recyclerview.quick;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liaohailiang on 2019-04-19.
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.ViewHolder> {

    protected List<T> datas;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public QuickAdapter(List<T> datas) {
        if (datas == null) {
            this.datas = Collections.emptyList();
        } else {
            this.datas = new ArrayList<>(datas);
        }
    }

    public abstract int getLayoutId(int viewType);

    public abstract void convert(ViewHolder holder, T data, int position);

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(parent, getLayoutId(viewType));
        viewHolder.itemView.setOnClickListener(new OnClickListener(viewHolder));
        viewHolder.itemView.setOnLongClickListener(new OnLongClickListener(viewHolder));
        onViewHolderCreated(viewHolder);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void onViewHolderCreated(ViewHolder viewHolder) {
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (datas == null) {
            return 0;
        }
        return datas.size();
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        if (datas == null) {
            this.datas = Collections.emptyList();
        } else {
            this.datas = new ArrayList<>(datas);
        }
        notifyDataSetChanged();
    }

    public void setDatasWithDiff(final List<T> newDatas) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                if (datas == null) {
                    return 0;
                }
                return datas.size();
            }

            @Override
            public int getNewListSize() {
                if (newDatas == null) {
                    return 0;
                }
                return newDatas.size();
            }

            @Override
            public boolean areItemsTheSame(int oip, int nip) {
                Class<?> oldItemClass = datas.get(oip).getClass();
                Class<?> newItemClass = newDatas.get(nip).getClass();
                return oldItemClass.equals(newItemClass);
            }

            @Override
            public boolean areContentsTheSame(int oip, int nip) {
                Object oldItem = datas.get(oip);
                Object newItem = newDatas.get(nip);
                return oldItem.equals(newItem);
            }
        });
        this.datas = new ArrayList<>(newDatas);
        diffResult.dispatchUpdatesTo(QuickAdapter.this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;
        private View convertView;

        private ViewHolder(View v) {
            super(v);
            convertView = v;
            views = new SparseArray<>();
        }

        public static ViewHolder get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new ViewHolder(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = views.get(id);
            if (v == null) {
                v = convertView.findViewById(id);
                if (v != null) {
                    views.put(id, v);
                }
            }
            return (T) v;
        }

        public <T extends View> T getView(int id, Class<T> type) {
            View v = getView(id);
            return (T) v;
        }

        public void setText(int id, String value) {
            View v = getView(id);
            if (v instanceof TextView) {
                ((TextView) v).setText(value);
            }
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, int position, T data);
    }

    private class OnClickListener implements View.OnClickListener {

        private final ViewHolder viewHolder;

        OnClickListener(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int pos = viewHolder.getLayoutPosition();
                onItemClickListener.onItemClick(viewHolder.itemView, pos, datas.get(pos));
            }
        }
    }

    private class OnLongClickListener implements View.OnLongClickListener {
        private final ViewHolder viewHolder;

        OnLongClickListener(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemClickListener != null) {
                int pos = viewHolder.getLayoutPosition();
                onItemLongClickListener.onItemLongClick(viewHolder.itemView, pos, datas.get(pos));
            }
            //表示此事件已经消费，不会触发单击事件
            return true;
        }
    }
}
