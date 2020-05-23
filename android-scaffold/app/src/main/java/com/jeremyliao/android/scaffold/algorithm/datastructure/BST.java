package com.jeremyliao.android.scaffold.algorithm.datastructure;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.TreeNode;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class BST {

    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        traverse(root.left);
        traverse(root.right);
    }

    public static boolean isValidBST(TreeNode<Integer> root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode<Integer> root, TreeNode<Integer> min, TreeNode<Integer> max) {
        if (root == null) return true;
        if (min != null && root.value <= min.value) return false;
        if (max != null && root.value >= max.value) return false;
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

    public static TreeNode<Integer> insert(TreeNode<Integer> root, int value) {
        if (root == null) {
            return new TreeNode<>(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public static TreeNode<Integer> delete(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            //delete
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                TreeNode<Integer> min = getMin(root.right);
                root.value = min.value;
                root.right = delete(root.right, min.value);
            }
        } else if (value < root.value) {
            root.left = delete(root.left, value);
        } else {
            root.right = delete(root.right, value);
        }
        return root;
    }

    private static TreeNode<Integer> getMin(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
