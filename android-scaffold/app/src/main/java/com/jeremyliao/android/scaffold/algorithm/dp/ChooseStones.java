package com.jeremyliao.android.scaffold.algorithm.dp;

/**
 * Created by liaohailiang on 2020-05-13.
 * 博弈问题「石头游戏」
 */
public class ChooseStones {

    public int chooseStones(int[] piles) {
        int n = piles.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return piles[0];
        }
        //0:先手；1：后手
        int[][][] dp = new int[n][n][2];
        //init
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }
        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                //[i,i+k]
                //先手选择左边
                int leftFirst = piles[i] + dp[i + 1][i + k][1];
                int leftSecond = dp[i + 1][i + k][0];
                //先手选择右边
                int rightFirst = piles[i + k] + dp[i][i + k - 1][1];
                int rightSecond = dp[i][i + k - 1][0];
                if (leftFirst > rightFirst) {
                    dp[i][i + k][0] = leftFirst;
                    dp[i][i + k][1] = leftSecond;
                } else {
                    dp[i][i + k][0] = rightFirst;
                    dp[i][i + k][1] = rightSecond;
                }
            }
        }
        return dp[0][n - 1][0] - dp[0][n - 1][1];
    }
}
