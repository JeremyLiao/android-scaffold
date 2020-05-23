package com.jeremyliao.android.scaffold.algorithm.other;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.TreeNode;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class PathSum2 {

    public static int pathSum(TreeNode<Integer> root, int sum) {
        if (root == null) {
            return 0;
        }
        return count(root, sum)
                + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }

    private static int count(TreeNode<Integer> root, int sum) {
        if (root == null) {
            return 0;
        }
        if (root.value == sum) {
            return 1;
        }
        return count(root.left, sum - root.value)
                + count(root.right, sum - root.value);
    }

    public static TreeNode<Integer> makeTree(Integer[] nodes) {
        return makeTree(nodes, 1);
    }

    private static TreeNode<Integer> makeTree(Integer[] nodes, int root) {
        if (root < 1 || root >= nodes.length) {
            return null;
        }
        if (nodes[root] == null) {
            return null;
        }
        TreeNode<Integer> treeNode = new TreeNode<>(nodes[root]);
        treeNode.left = makeTree(nodes, root * 2);
        treeNode.right = makeTree(nodes, root * 2 + 1);
        return treeNode;
    }
}
