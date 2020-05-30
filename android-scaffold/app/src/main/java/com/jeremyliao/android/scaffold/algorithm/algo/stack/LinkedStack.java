package com.jeremyliao.android.scaffold.algorithm.algo.stack;

/**
 * Created by liaohailiang on 2020-05-28.
 */
public class LinkedStack<T> {

    private Node<T> head;

    public void push(T value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public static class Node<T> {
        public Node<T> next;
        public T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
