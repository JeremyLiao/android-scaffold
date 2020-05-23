package com.jeremyliao.android.scaffold.algorithm.datastructure.node;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T value;

    public TreeNode(T value) {
        this.value = value;
    }
}
