package com.jeremyliao.android.scaffold.algorithm.other;

/**
 * Created by liaohailiang on 2020-05-20.
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1;
        while (j < n) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }
}
