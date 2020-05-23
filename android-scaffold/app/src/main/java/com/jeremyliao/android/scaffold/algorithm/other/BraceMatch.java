package com.jeremyliao.android.scaffold.algorithm.other;

import java.util.LinkedList;

/**
 * Created by liaohailiang on 2020-05-20.
 */
public class BraceMatch {

    public static boolean isValid(String str) {
        char[] chars = str.toCharArray();
        LinkedList<Integer> list = new LinkedList<>();
        for (char c : chars) {
            if (c == '(') {
                list.push(1);
            } else if (c == '[') {
                list.push(2);
            } else if (c == '{') {
                list.push(3);
            } else if (c == ')') {
                boolean valid = list.pop() == 1;
                if (!valid) {
                    return false;
                }
            } else if (c == ']') {
                boolean valid = list.pop() == 2;
                if (!valid) {
                    return false;
                }
            } else if (c == '}') {
                boolean valid = list.pop() == 3;
                if (!valid) {
                    return false;
                }
            }
        }
        return list.isEmpty();
    }
}
