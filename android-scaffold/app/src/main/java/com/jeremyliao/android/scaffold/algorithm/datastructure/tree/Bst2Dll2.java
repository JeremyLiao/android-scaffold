package com.jeremyliao.android.scaffold.algorithm.datastructure.tree;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaohailiang on 2020-06-02.
 */
public class Bst2Dll2 {

    public static TreeNode<Integer> bst2Dll(TreeNode<Integer> root) {
        processNode(root);
        TreeNode<Integer> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static void processNode(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        processNode(root.left);
        processNode(root.right);
        TreeNode<Integer> leftNode = root.left;
        if (leftNode != null) {
            while (leftNode.right != null) {
                leftNode = leftNode.right;
            }
            leftNode.right = root;
            root.left = leftNode;
        }
        TreeNode<Integer> rightNode = root.right;
        if (rightNode != null) {
            while (rightNode.left != null) {
                rightNode = rightNode.left;
            }
            rightNode.left = root;
            root.right = rightNode;
        }
    }

    public static int[] toArray(TreeNode<Integer> head) {
        List<Integer> list = new ArrayList<>();
        TreeNode<Integer> current = head;
        while (current != null) {
            list.add(current.value);
            current = current.right;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
