package com.jeremyliao.android.scaffold.algorithm.coins;

import java.util.Arrays;

/**
 * Created by liaohailiang on 2020-05-12.
 * 凑零钱问题的动态规划解法
 */
public class Coins3 implements ICoins {
    @Override
    public int getCoins(int[] coins, int amount) {
        if (coins == null) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int target = 1; target <= amount; target++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int left = target - coin;
                if (left < 0) {
                    continue;
                }
                if (dp[left] < 0) {
                    continue;
                }
                if (dp[left] + 1 < min) {
                    min = dp[left] + 1;
                }
            }
            if (min < Integer.MAX_VALUE) {
                dp[target] = min;
            }
        }
        return dp[amount];
    }
}
