package com.jeremyliao.android.scaffold.algorithm.datastructure.node;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class DoubleLinkNode<T> {
    public DoubleLinkNode<T> previous;
    public DoubleLinkNode<T> next;
    public T value;

    public DoubleLinkNode(T value) {
        this.value = value;
    }
}
