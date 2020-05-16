package com.jeremyliao.android.scaffold.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by liaohailiang on 2020-05-13.
 * 区间调度问题
 */
public class IntervalScheduling {

    public int intervalSchedule(int[][] intvs) {
        if (intvs.length == 0) {
            return 0;
        }
        Arrays.sort(intvs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1;
        int end = intvs[0][1];
        for (int[] intv : intvs) {
            if (intv[0] >= end) {
                count++;
                end = intv[1];
            }
        }
        return count;
    }
}
