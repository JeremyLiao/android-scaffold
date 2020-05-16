package com.jeremyliao.android.scaffold.algorithm.dp;

import java.util.Arrays;

/**
 * Created by liaohailiang on 2020-05-12.
 * 最长递增子序列动态规划算法
 * dp[i]:以number[i]结尾的最长递增子序列长度
 */
public class Lis {

    public int getLis(int[] input) {
        int[] dp = new int[input.length];
        //最长递增子序列长度至少为1
        Arrays.fill(dp, 1);
        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
