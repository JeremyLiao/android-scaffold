package com.jeremyliao.android.scaffold.algorithm.algo.queue;

/**
 * Created by liaohailiang on 2020-05-28.
 */
public class LinkedQueue<T> {

    // 队列的队首和队尾
    private Node<T> head = null;
    private Node<T> tail = null;

    public void enqueue(T value) {
        if (tail == null) {
            tail = new Node<>(value);
            head = tail;
            return;
        }
        tail.next = new Node<>(value);
        tail = tail.next;
    }

    public T dequeue() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
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
