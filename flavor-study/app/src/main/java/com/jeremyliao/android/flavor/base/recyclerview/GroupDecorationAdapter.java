package com.jeremyliao.android.flavor.base.recyclerview;


import android.view.View;

/**
 * Created by liaohailiang on 2020-02-07.
 */
public interface GroupDecorationAdapter {

    View onCreateGroupView();

    void onBindGroupView(View groupView, int groupPosition);

    int getGroupCount();

    int getStartPositionForGroup(int groupPosition);

    boolean isStickyHeader();
}
