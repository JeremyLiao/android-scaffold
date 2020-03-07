package com.jeremyliao.android.flavor.bean;

/**
 * Created by liaohailiang on 2020-03-07.
 */
public class GroupData {
    public final String groupName;
    public final DemoData[] datas;

    public GroupData(String groupName, DemoData[] datas) {
        this.groupName = groupName;
        this.datas = datas;
    }
}
