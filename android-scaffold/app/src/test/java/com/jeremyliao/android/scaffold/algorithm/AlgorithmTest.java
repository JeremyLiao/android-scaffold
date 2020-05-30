package com.jeremyliao.android.scaffold.algorithm;

import com.jeremyliao.android.scaffold.algorithm.algo.array.Array;
import com.jeremyliao.android.scaffold.algorithm.algo.sorts.Sorts;
import com.jeremyliao.android.scaffold.algorithm.backtrack.NQueens;
import com.jeremyliao.android.scaffold.algorithm.backtrack.Permute;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins1;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins2;
import com.jeremyliao.android.scaffold.algorithm.coins.Coins3;
import com.jeremyliao.android.scaffold.algorithm.coins.ICoins;
import com.jeremyliao.android.scaffold.algorithm.datastructure.BST;
import com.jeremyliao.android.scaffold.algorithm.datastructure.LinkList;
import com.jeremyliao.android.scaffold.algorithm.datastructure.MaxPriorityQueue;
import com.jeremyliao.android.scaffold.algorithm.datastructure.Palindrome;
import com.jeremyliao.android.scaffold.algorithm.datastructure.node.LinkNode;
import com.jeremyliao.android.scaffold.algorithm.datastructure.node.TreeNode;
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
import com.jeremyliao.android.scaffold.algorithm.other.BraceMatch;
import com.jeremyliao.android.scaffold.algorithm.other.FloodFill;
import com.jeremyliao.android.scaffold.algorithm.other.LongestPalindrome;
import com.jeremyliao.android.scaffold.algorithm.other.PathSum;
import com.jeremyliao.android.scaffold.algorithm.other.PathSum2;
import com.jeremyliao.android.scaffold.algorithm.other.PreSum;
import com.jeremyliao.android.scaffold.algorithm.other.RemoveDuplicates;
import com.jeremyliao.android.scaffold.algorithm.other.Subsequence;
import com.jeremyliao.android.scaffold.algorithm.other.TwoSum;

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

    @Test
    public void testPriorityQueue() {
        MaxPriorityQueue<Integer> priorityQueue = new MaxPriorityQueue(10);
        int[] values = {2, 7, 15, 9, 21, 3, 1};
        for (int value : values) {
            priorityQueue.insert(value);
        }
        Assert.assertEquals((int) priorityQueue.max(), 21);
        priorityQueue.delMax();
        Assert.assertEquals((int) priorityQueue.max(), 15);
    }

    @Test
    public void testBST() {
        int[] values = {10, 2, 7, 15, 9, 21, 3, 1};
        TreeNode<Integer> root = null;
        for (int value : values) {
            root = BST.insert(root, value);
        }
        Assert.assertTrue(BST.isValidBST(root));
        root = BST.delete(root, 7);
        Assert.assertTrue(BST.isValidBST(root));
        root = BST.delete(root, 3);
        Assert.assertTrue(BST.isValidBST(root));
    }

    @Test
    public void testTwoSum() {
        int[] nums = {10, 2, 7, 15, 9, 21, 3, 1};
        int[] result = TwoSum.twoSum1(nums, 9);
        Assert.assertArrayEquals(result, new int[]{1, 2});
        result = TwoSum.twoSum2(nums, 9);
        Assert.assertArrayEquals(result, new int[]{1, 2});
        result = TwoSum.twoSum2(nums, 50);
        Assert.assertArrayEquals(result, new int[]{-1, -1});
    }

    @Test
    public void testPreSum() {
        int[] nums = {1, 1, 1};
        int result = PreSum.subarraySum(nums, 2);
        Assert.assertEquals(result, 2);
        result = PreSum.subarraySum1(nums, 2);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testFloodFill() {
        int[][] images = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] rets = FloodFill.floodFill(images, 1, 1, 2);
        Assert.assertArrayEquals(rets, new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
    }

    @Test
    public void testPathSum() {
        Integer[] nodes = {null, 10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        int result = PathSum.pathSum(nodes, 1, 8);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testPathSum2() {
        Integer[] nodes = {null, 10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode<Integer> root = PathSum2.makeTree(nodes);
        int result = PathSum2.pathSum(root, 8);
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = {1, 2, 3, 3, 3, 4, 5, 5, 6};
        int result = RemoveDuplicates.removeDuplicates(nums);
        Assert.assertEquals(result, 6);
    }

    @Test
    public void testLongestPalindrome() {
        String result = LongestPalindrome.longestPalindrome("abacd");
        Assert.assertEquals(result, "aba");
    }

    @Test
    public void testLinkList() {
        int[] list = {1, 2, 3, 4, 5, 6};
        LinkNode<Integer> root = LinkList.from(list);
        Assert.assertEquals(LinkList.length(root), 6);
        Assert.assertArrayEquals(LinkList.to(root), list);
        LinkNode<Integer> reverse = LinkList.reverse(LinkList.from(list));
        Assert.assertArrayEquals(LinkList.to(reverse), new int[]{6, 5, 4, 3, 2, 1});
        LinkNode<Integer> kreverse = LinkList.kreverse(LinkList.from(list), 2);
        Assert.assertArrayEquals(LinkList.to(kreverse), new int[]{2, 1, 3, 4, 5, 6});
        LinkNode<Integer> loopkreverse = LinkList.loopkreverse(LinkList.from(list), 2);
        Assert.assertArrayEquals(LinkList.to(loopkreverse), new int[]{2, 1, 4, 3, 6, 5});
        int[] list1 = {1, 2, 3, 4, 5, 6, 7};
        LinkNode<Integer> loopkreverse1 = LinkList.loopkreverse(LinkList.from(list1), 2);
        Assert.assertArrayEquals(LinkList.to(loopkreverse1), new int[]{2, 1, 4, 3, 6, 5, 7});
    }

    @Test
    public void testBraceMatch() {
        assertTrue(BraceMatch.isValid("()[]{}"));
        assertFalse(BraceMatch.isValid("([)]"));
        assertTrue(BraceMatch.isValid("{[]}"));
    }

    @Test
    public void testLinkPalindrome() {
        assertTrue(Palindrome.isPalindrome(LinkList.from(new int[]{1, 2, 2, 1})));
        assertFalse(Palindrome.isPalindrome(LinkList.from(new int[]{1, 2, 3})));
    }

    @Test
    public void testSubsequence() {
        assertTrue(Subsequence.isSubsequence("abc", "ahbgdc"));
        assertFalse(Subsequence.isSubsequence("axc", "ahbgdc"));
    }

    @Test
    public void testArray() {
        Array array = new Array(5);
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        assertArrayEquals(array.toArray(), new int[]{4, 5, 3, 10, 9});
    }

    @Test
    public void testBubbleSort() {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        Sorts.bubbleSort(array);
        assertArrayEquals(array, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testBubbleSort2() {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        Sorts.bubbleSort2(array);
        assertArrayEquals(array, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testInsertionSort() {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        Sorts.insertionSort(array);
        assertArrayEquals(array, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    @Test
    public void testSelectionSort() {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        Sorts.selectionSort(array);
        assertArrayEquals(array, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }
}