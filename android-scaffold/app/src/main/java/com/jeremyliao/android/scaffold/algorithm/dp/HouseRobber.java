package com.jeremyliao.android.scaffold.algorithm.dp;

/**
 * Created by liaohailiang on 2020-05-13.
 * 打家劫舍问题
 */
public class HouseRobber {

    public int rob(int[] houses) {
        int n = houses.length;
        int[] dp = new int[n + 2];
        dp[n + 1] = dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 2] + houses[i], dp[i + 1]);
        }
        return dp[0];
    }
}
