package com.jeremyliao.android.scaffold.algorithm.datastructure.node;

/**
 * Created by liaohailiang on 2020-05-19.
 */
public class LinkNode<T> {
    public LinkNode<T> next;
    public T value;

    public LinkNode(T value) {
        this.value = value;
    }
}
