package com.jeremyliao.android.scaffold.algorithm.other;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class PathSum {

    public static int pathSum(Integer[] nodes, int root, int sum) {
        if (root < 1 || root >= nodes.length) {
            return 0;
        }
        if (nodes[root] == null) {
            return 0;
        }
        return count(nodes, root, sum)
                + pathSum(nodes, root * 2, sum)
                + pathSum(nodes, root * 2 + 1, sum);
    }

    private static int count(Integer[] nodes, int root, int sum) {
        if (root < 1 || root >= nodes.length) {
            return 0;
        }
        if (nodes[root] == null) {
            return 0;
        }
        if (nodes[root] == sum) {
            return 1;
        }
        return count(nodes, root * 2, sum - nodes[root])
                + count(nodes, root * 2 + 1, sum - nodes[root]);
    }
}
