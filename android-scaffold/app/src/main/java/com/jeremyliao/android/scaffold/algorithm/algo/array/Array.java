package com.jeremyliao.android.scaffold.algorithm.algo.array;

import java.util.Arrays;

/**
 * Created by liaohailiang on 2020-05-26.
 */
public class Array {
    private final int[] data;
    private final int capacity;
    private int count;

    public Array(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.count = 0;
    }

    //根据索引，找到数据中的元素并返回
    public int find(int index) {
        if (index < 0 || index >= capacity) {
            return -1;
        }
        return data[index];
    }

    //插入元素:头部插入，尾部插入
    public boolean insert(int index, int value) {
        if (count == capacity) {
            return false;
        }
        if (index < 0 || index > count) {
            return false;
        }
        if (index == count) {
            data[index] = value;
            count++;
            return true;
        }
        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        count++;
        return true;
    }

    //根据索引，删除数组中元素
    public boolean delete(int index) {
        if (index < 0 || index >= count) {
            return false;
        }
        if (index == count - 1) {
            count--;
            return true;
        }
        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public int[] toArray() {
        return data;
    }
}
