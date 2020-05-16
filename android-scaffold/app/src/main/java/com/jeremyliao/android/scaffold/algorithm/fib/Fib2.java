package com.jeremyliao.android.scaffold.algorithm.fib;

/**
 * Created by liaohailiang on 2020-05-11.
 * 斐波那契数列递归+备忘录解法
 */
public class Fib2 implements IFib {

    @Override
    public int fib(int N) {
        int[] lookup = new int[N + 1];

        return fib(N, lookup);
    }

    private int fib(int N, int[] lookup) {
        if (N == 1 || N == 2) {
            return 1;
        }
        if (lookup[N] > 0) {
            return lookup[N];
        }
        int result = fib(N - 1, lookup) + fib(N - 2, lookup);
        lookup[N] = result;
        return result;
    }
}
