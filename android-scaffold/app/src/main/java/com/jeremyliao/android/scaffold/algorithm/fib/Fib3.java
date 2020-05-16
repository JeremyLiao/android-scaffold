package com.jeremyliao.android.scaffold.algorithm.fib;

/**
 * Created by liaohailiang on 2020-05-11.
 * 斐波那契数列动态规划解法
 */
public class Fib3 implements IFib {

    @Override
    public int fib(int N) {
        int[] array = new int[N + 1];
        array[1] = 1;
        array[2] = 1;
        for (int i = 3; i <= N; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[N];
    }
}
