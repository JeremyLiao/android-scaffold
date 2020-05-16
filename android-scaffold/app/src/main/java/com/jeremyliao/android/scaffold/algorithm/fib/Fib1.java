package com.jeremyliao.android.scaffold.algorithm.fib;

/**
 * Created by liaohailiang on 2020-05-11.
 * 斐波那契数列递归解法
 */
public class Fib1 implements IFib {

    @Override
    public int fib(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }
}
