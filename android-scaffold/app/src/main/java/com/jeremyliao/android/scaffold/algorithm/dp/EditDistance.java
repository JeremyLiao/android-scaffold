package com.jeremyliao.android.scaffold.algorithm.dp;

/**
 * Created by liaohailiang on 2020-05-12.
 * 编辑距离动态规划算法
 */
public class EditDistance {

    public int ed(String str1, String str2) {
        int[][] dp = new int[str2.length()][str1.length()];
        //一个字符转化成另一个字符，相同距离为0，不同距离为1
        if (str1.charAt(0) == str2.charAt(0)) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }
        //输入转化成只有一个字符的输出，只需要增加一步删除
        for (int i = 1; i < str1.length(); i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        //一个字符的输入转化成输出，比之前的操作增加一步删除
        for (int j = 1; j < str2.length(); j++) {
            dp[j][0] = dp[j - 1][0] + 1;
        }
        for (int i = 1; i < str2.length(); i++) {
            for (int j = 1; j < str1.length(); j++) {
                //插入
                int step1 = dp[i - 1][j] + 1;
                //删除
                int step2 = dp[i][j - 1] + 1;
                //替换，相同+0，不同+1
                int step3 = str1.charAt(j) == str2.charAt(i) ?
                        dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(Math.min(step1, step2), step3);
            }
        }
        return dp[str2.length() - 1][str1.length() - 1];
    }
}
