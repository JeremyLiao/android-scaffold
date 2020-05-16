package com.jeremyliao.android.scaffold.algorithm.coins;

/**
 * Created by liaohailiang on 2020-05-12.
 * 凑零钱问题的递归解法
 */
public class Coins1 implements ICoins {
    @Override
    public int getCoins(int[] coins, int amount) {
        if (coins == null) {
            return -1;
        }
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int coins1 = getCoins(coins, amount - coin);
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
