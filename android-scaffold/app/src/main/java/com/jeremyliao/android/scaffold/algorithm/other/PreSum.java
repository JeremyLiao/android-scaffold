package com.jeremyliao.android.scaffold.algorithm.other;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class PreSum {

    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        //sum[i...j]=presum[j+1]-presum[i]
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (presum[j + 1] - presum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        //sum[i...j]=presum[j+1]-presum[i]
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (set.contains(presum[i])) {
                count++;
            }
            set.add(presum[i] + k);
        }
        return count;
    }
}
