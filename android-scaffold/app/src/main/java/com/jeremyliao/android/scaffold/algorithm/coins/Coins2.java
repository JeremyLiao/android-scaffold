package com.jeremyliao.android.scaffold.algorithm.coins;

import java.util.Arrays;

/**
 * Created by liaohailiang on 2020-05-12.
 * 凑零钱问题的递归+备忘录解法
 */
public class Coins2 implements ICoins {
    @Override
    public int getCoins(int[] coins, int amount) {
        if (coins == null) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        return getCoins(coins, amount, dp);
    }

    private int getCoins(int[] coins, int amount, int[] dp) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int storeRet = dp[amount];
        if (storeRet != Integer.MIN_VALUE) {
            return storeRet;
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int coins1 = getCoins(coins, amount - coin, dp);
            if (coins1 < 0) {
                continue;
            }
            if (coins1 + 1 < result) {
                result = coins1 + 1;
            }
        }
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }
}
