package com.jeremyliao.android.scaffold.algorithm;

import com.jeremyliao.android.scaffold.algorithm.backtrack.NQueens;
import com.jeremyliao.android.scaffold.algorithm.backtrack.Permute;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins1;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins2;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins3;
import com.jeremyliao.android.scaffold.algorithm.coins.ICoins;
import com.jeremyliao.android.scaffold.algorithm.dp.ChooseStones;
import com.jeremyliao.android.scaffold.algorithm.dp.EditDistance;
import com.jeremyliao.android.scaffold.algorithm.dp.HouseRobber;
import com.jeremyliao.android.scaffold.algorithm.dp.Lcs;
import com.jeremyliao.android.scaffold.algorithm.dp.Lis;
import com.jeremyliao.android.scaffold.algorithm.dp.StockTrade;
import com.jeremyliao.android.scaffold.algorithm.fib.Fib1;
import com.jeremyliao.android.scaffold.algorithm.fib.Fib2;
import com.jeremyliao.android.scaffold.algorithm.fib.Fib3;
import com.jeremyliao.android.scaffold.algorithm.fib.Fib4;
import com.jeremyliao.android.scaffold.algorithm.fib.IFib;
import com.jeremyliao.android.scaffold.algorithm.greedy.IntervalScheduling;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liaohailiang on 2020-05-11.
 */
public class AlgorithmTest {

    @Test
    public void testFib1() {
        IFib fib = new Fib1();
        int result = fib.fib(20);
        System.out.println(result);
        Assert.assertEquals(result, 6765);
    }

    @Test
    public void testFib2() {
        IFib fib = new Fib2();
        int result = fib.fib(20);
        System.out.println(result);
        Assert.assertEquals(result, 6765);
    }

    @Test
    public void testFib3() {
        IFib fib = new Fib3();
        int result = fib.fib(20);
        System.out.println(result);
        Assert.assertEquals(result, 6765);
    }

    @Test
    public void testFib4() {
        IFib fib = new Fib4();
        int result = fib.fib(20);
        System.out.println(result);
        Assert.assertEquals(result, 6765);
    }

    @Test
    public void testCoins1() {
        ICoins coins = new Coins1();
        int result = coins.getCoins(new int[]{1, 2, 5}, 11);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testCoins2() {
        ICoins coins = new Coins2();
        int result = coins.getCoins(new int[]{1, 2, 5}, 11);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testCoins3() {
        ICoins coins = new Coins3();
        int result = coins.getCoins(new int[]{1, 2, 5}, 11);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testPermute() {
        Permute permute = new Permute();
        List<List<Integer>> result = permute.permute(new int[]{1, 2, 3});
        System.out.println(result);
        Assert.assertEquals(result.size(), 6);
    }

    @Test
    public void testNQueens() {
        NQueens queens = new NQueens();
        List<List<Integer>> result = queens.solveNQueens(8);
        System.out.println(result);
        Assert.assertEquals(result.size(), 92);
    }

    @Test
    public void testLis() {
        Lis lis = new Lis();
        int result = lis.getLis(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        Assert.assertEquals(result, 4);
    }

    @Test
    public void testEditDistance() {
        EditDistance editDistance = new EditDistance();
        int result = editDistance.ed("horse", "ros");
        Assert.assertEquals(result, 3);
        result = editDistance.ed("intention", "execution");
        Assert.assertEquals(result, 5);
    }

    @Test
    public void testLcs() {
        Lcs lcs = new Lcs();
        int result = lcs.lcs("abcde", "ace");
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testChooseStone() {
        ChooseStones chooseStones = new ChooseStones();
        int result = chooseStones.chooseStones(new int[]{1, 100, 3});
        Assert.assertEquals(result, -96);
    }

    @Test
    public void testIntervalScheduling() {
        IntervalScheduling intervalScheduling = new IntervalScheduling();
        int result = intervalScheduling.intervalSchedule(new int[][]{{1, 3}, {2, 4}, {3, 6}});
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testStockTrade() {
        StockTrade stockTrade = new StockTrade();
        int result = stockTrade.stockTrade(new int[]{2, 4, 1}, 2);
        Assert.assertEquals(result, 2);
        result = stockTrade.stockTrade(new int[]{3, 2, 6, 5, 0, 3}, 2);
        Assert.assertEquals(result, 7);
    }

    @Test
    public void testHouseRobber() {
        HouseRobber houseRobber = new HouseRobber();
        int result = houseRobber.rob(new int[]{1, 2, 3, 1});
        Assert.assertEquals(result, 4);
        result = houseRobber.rob(new int[]{2, 7, 9, 3, 1});
        Assert.assertEquals(result, 12);
    }
}