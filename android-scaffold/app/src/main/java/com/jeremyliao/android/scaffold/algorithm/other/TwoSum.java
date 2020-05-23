package com.jeremyliao.android.scaffold.algorithm.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class TwoSum {

    public static int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                return new int[]{index, i};
            }
            int left = target - nums[i];
            map.put(left, i);
        }
        return new int[]{-1, -1};
    }
}
