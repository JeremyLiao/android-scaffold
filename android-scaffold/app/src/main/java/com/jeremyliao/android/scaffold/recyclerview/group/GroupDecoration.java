package com.jeremyliao.android.scaffold.recyclerview.group;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaohailiang on 2020-02-06.
 */
public class GroupDecoration extends RecyclerView.ItemDecoration {

    private static final int MAX_SIZE = 1024 * 4;
    private static final int DEFAULT_HEIGHT = 100;

    private final GroupDecorationAdapter adapter;
    private final View groupView;
    private int decoHeight = 0;
    private final Map<Integer, Integer> startPositionMap = new HashMap<>();

    public GroupDecoration(@NonNull GroupDecorationAdapter adapter) {
        this.adapter = adapter;
        groupView = adapter.onCreateGroupView();
        init();
    }

    private void init() {
        measureOnStart();
        initStartPosition();
    }

    private void initStartPosition() {
        if (adapter.getGroupCount() <= 0) {
            return;
        }
        for (int groupIndex = 0; groupIndex < adapter.getGroupCount(); groupIndex++) {
            int startPositionForGroup = adapter.getStartPositionForGroup(groupIndex);
            startPositionMap.put(startPositionForGroup, groupIndex);
        }
    }

    private void measureOnStart() {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(MAX_SIZE, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(MAX_SIZE, View.MeasureSpec.AT_MOST);
        groupView.measure(widthSpec, heightSpec);
        decoHeight = groupView.getMeasuredHeight();
        if (decoHeight >= MAX_SIZE || decoHeight <= 0) {
            decoHeight = DEFAULT_HEIGHT;
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (!isLinearAndVertical(parent)) {
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        if (startPositionMap.containsKey(position)) {
            outRect.top = decoHeight;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (adapter.getGroupCount() <= 0 || !isLinearAndVertical(parent)) {
            return;
        }

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int position = parent.getChildAdapterPosition(child);
            if (startPositionMap.containsKey(position)) {
                int left = child.getLeft();
                int top = child.getTop() - decoHeight;

                c.save();
                c.translate(left, top);
                int groupPosition = startPositionMap.get(position);
                adapter.onBindGroupView(groupView, groupPosition);
                measureOnDraw(parent);
                groupView.draw(c);
                c.restore();
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (!adapter.isStickyHeader() || adapter.getGroupCount() <= 0 || !isLinearAndVertical(parent)) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
        int firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();
        int groupPosition = findGroupPosition(firstVisibleItemPosition);
        if (groupPosition < 0 || groupPosition >= adapter.getGroupCount()) {
            return;
        }
        c.save();
        adapter.onBindGroupView(groupView, groupPosition);
        measureOnDraw(parent);
        groupView.draw(c);
        c.restore();
    }

    private void measureOnDraw(View parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(MAX_SIZE, View.MeasureSpec.AT_MOST);
        groupView.measure(widthSpec, heightSpec);
        groupView.layout(0, 0, groupView.getMeasuredWidth(), groupView.getMeasuredHeight());
    }

    private boolean isLinearAndVertical(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return false;
        } else {
            if (((LinearLayoutManager) layoutManager).getOrientation()
                    != LinearLayoutManager.VERTICAL) {
                return false;
            }
        }
        return true;
    }

    private int findGroupPosition(int itemPosition) {
        if (itemPosition < adapter.getStartPositionForGroup(0)) {
            return -1;
        }
        if (adapter.getGroupCount() == 1) {
            return 0;
        }
        for (int i = 0; i < adapter.getGroupCount() - 1; i++) {
            int start = adapter.getStartPositionForGroup(i);
            int end = adapter.getStartPositionForGroup(i + 1);
            if (itemPosition >= start && itemPosition < end) {
                return i;
            }
        }
        return adapter.getGroupCount() - 1;
    }
}
