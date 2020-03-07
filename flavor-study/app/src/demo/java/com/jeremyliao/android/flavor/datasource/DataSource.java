package com.jeremyliao.android.flavor.datasource;

import com.jeremyliao.android.flavor.bean.DemoData;
import com.jeremyliao.android.flavor.bean.GroupData;

/**
 * Created by liaohailiang on 2020-03-07.
 */
public class DataSource {

    public static final GroupData[] DATA = {
            new GroupData("1",
                    new DemoData[]{
                            new DemoData("1111"),
                            new DemoData("1111"),
                            new DemoData("1111"),
                            new DemoData("1111"),
                            new DemoData("1111"),
                    }),
            new GroupData("2",
                    new DemoData[]{
                            new DemoData("22"),
                            new DemoData("22"),
                            new DemoData("22"),
                            new DemoData("22"),
                            new DemoData("22"),
                    }),
            new GroupData("3",
                    new DemoData[]{
                            new DemoData("3333"),
                            new DemoData("3333"),
                            new DemoData("3333"),
                            new DemoData("3333"),
                            new DemoData("3333"),
                    }),
            new GroupData("4",
                    new DemoData[]{
                            new DemoData("44"),
                            new DemoData("44"),
                            new DemoData("22"),
                            new DemoData("22"),
                            new DemoData("22"),
                    }),
    };
}
