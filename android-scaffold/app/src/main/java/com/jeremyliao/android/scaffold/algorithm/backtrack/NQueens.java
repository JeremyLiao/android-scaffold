package com.jeremyliao.android.scaffold.algorithm.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liaohailiang on 2020-05-12.
 * N皇后问题回溯算法
 */
public class NQueens {

    public List<List<Integer>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(n, track, result);
        return result;
    }

    private void backTrack(int n, LinkedList<Integer> track, List<List<Integer>> result) {
        if (track.size() == n) {
            result.add(new ArrayList<>(track));
            return;
        }
        for (int column = 0; column < n; column++) {
            if (!isValid(track, column)) {
                continue;
            }
            track.add(column);
            backTrack(n, track, result);
            track.removeLast();
        }
    }

    private boolean isValid(LinkedList<Integer> track, int column) {
        int line = track.size();
        for (int i = 0; i < track.size(); i++) {
            Integer c = track.get(i);
            if (c == column) {
                return false;
            }
            if (Math.abs(line - i) == Math.abs(column - c)) {
                return false;
            }
        }
        return true;
    }
}
