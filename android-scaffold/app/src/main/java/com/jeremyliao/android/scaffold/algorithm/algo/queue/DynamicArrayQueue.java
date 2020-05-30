package com.jeremyliao.android.scaffold.algorithm.algo.queue;

/**
 * Created by liaohailiang on 2020-05-28.
 */
public class DynamicArrayQueue<T> {

    private T[] items;
    private int capacity = 0;
    private int head = 0;
    private int tail = 0;

    public DynamicArrayQueue(int capacity) {
        items = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public boolean enqueue(T item) {
        // tail == n表示队列末尾没有空间了
        if (tail == capacity) {
            // tail ==n && head==0，表示整个队列都占满了
            if (head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i - head] = items[i];
            }
            // 搬移完之后重新更新head和tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        tail++;
        return true;
    }

    public T dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) return null;
        T ret = items[head];
        ++head;
        return ret;
    }
}
