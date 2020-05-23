package com.jeremyliao.android.scaffold.algorithm.other;

/**
 * Created by liaohailiang on 2020-05-20.
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        String ls = "";
        for (int i = 0; i < n; i++) {
            String s1 = longestPalindrome(s, i);
            if (s1.length() > ls.length()) {
                ls = s1;
            }
        }
        return ls;
    }

    private static String longestPalindrome(String s, int index) {
        int n = s.length();
        int i = index;
        int j = index;
        while (true) {
            if (i - 1 < 0) {
                break;
            }
            if (j + 1 >= n) {
                break;
            }
            if (s.charAt(i - 1) != s.charAt(j + 1)) {
                break;
            }
            i--;
            j++;
        }
        String ls = s.substring(i, j + 1);
        i = index;
        j = index + 1;
        if (j >= n) {
            return ls;
        }
        if (s.charAt(i) != s.charAt(j)) {
            return ls;
        }
        while (true) {
            if (i - 1 < 0) {
                break;
            }
            if (j + 1 >= n) {
                break;
            }
            if (s.charAt(i - 1) != s.charAt(j + 1)) {
                break;
            }
            i--;
            j++;
        }
        String lls = s.substring(i, j + 1);
        return ls.length() >= lls.length() ? ls : lls;
    }
}
