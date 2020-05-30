package com.jeremyliao.android.scaffold.algorithm.algo.queue;

/**
 * Created by liaohailiang on 2020-05-28.
 */
public class ArrayQueue<T> {

    private T[] items;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        items = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public boolean enqueue(T item) {
        if (tail == capacity) {
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            return null;
        }
        T ret = items[head];
        head++;
        return ret;
    }
}
