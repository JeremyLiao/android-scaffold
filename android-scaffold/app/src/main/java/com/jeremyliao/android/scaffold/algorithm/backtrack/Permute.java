package com.jeremyliao.android.scaffold.algorithm.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liaohailiang on 2020-05-12.
 * 全排列问题回溯算法
 */
public class Permute {

    public List<List<Integer>> permute(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(numbers, track, result);
        return result;
    }

    private void backTrack(int[] numbers, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == numbers.length) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int number : numbers) {
            if (track.contains(number)) {
                continue;
            }
            track.add(number);
            backTrack(numbers, track, result);
            track.removeLast();
        }
    }
}
