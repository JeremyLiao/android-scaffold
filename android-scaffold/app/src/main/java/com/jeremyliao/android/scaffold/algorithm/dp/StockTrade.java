package com.jeremyliao.android.scaffold.algorithm.dp;

/**
 * Created by liaohailiang on 2020-05-13.
 * 股票买卖问题
 */
public class StockTrade {

    public int stockTrade(int[] prices, int k) {
        int n = prices.length;
        //0:没有股票，1：持有股票
        int[][][] dp = new int[n + 1][k + 1][2];
        //init
        for (int j = 0; j < k + 1; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                //dp[i][j]
                /*
                 * 解释：今天我没有持有股票，有两种可能：
                 * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
                 * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
                 */
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);

                /*
                 * 解释：今天我持有着股票，有两种可能：
                 * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
                 * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
                 */
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
            }
        }
        return dp[n][k][0];
    }
}
