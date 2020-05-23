package com.jeremyliao.android.scaffold.algorithm.other;

/**
 * Created by liaohailiang on 2020-05-21.
 */
public class Subsequence {

    public static boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}
