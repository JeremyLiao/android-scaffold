package com.jeremyliao.android.scaffold.algorithm.fib;

/**
 * Created by liaohailiang on 2020-05-11.
 * 斐波那契数列动态规划优化解法
 */
public class Fib4 implements IFib {

    @Override
    public int fib(int N) {
        if (N == 1 || N == 2) {
            return 1;
        }
        int current = 1;
        int previous = 1;
        int sum = 0;
        for (int i = 3; i <= N; i++) {
            sum = current + previous;
            previous = current;
            current = sum;
        }
        return current;
    }
}
