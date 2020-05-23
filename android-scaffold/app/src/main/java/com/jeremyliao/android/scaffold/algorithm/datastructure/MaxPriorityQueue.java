package com.jeremyliao.android.scaffold.algorithm.datastructure;

/**
 * Created by liaohailiang on 2020-05-18.
 */
public class MaxPriorityQueue<Value extends Comparable<Value>> {

    private Value[] pq;
    private int num = 0;
    private final int capability;

    public MaxPriorityQueue(int capability) {
        this.capability = capability;
        pq = (Value[]) new Comparable[capability + 1];
    }

    // 父节点的索引
    private int parent(int root) {
        return root / 2;
    }

    // 左孩子的索引
    private int left(int root) {
        return root * 2;
    }

    // 右孩子的索引
    private int right(int root) {
        return root * 2 + 1;
    }

    /* 返回当前队列中最大元素 */
    public Value max() {
        return pq[1];
    }

    /* 交换数组的两个元素 */
    private void exch(int i, int j) {
        Value temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /* 上浮第 k 个元素，以维护最大堆性质 */
    private void swim(int k) {
        while (k > 1 && less(parent(k), k)) {
            exch(parent(k), k);
            k = parent(k);
        }
    }

    /* 下沉第 k 个元素，以维护最大堆性质 */
    private void sink(int k) {
        while (left(k) <= num) {
            int bigger = left(k);
            if (right(k) <= num) {
                if (less(bigger, right(k))) {
                    bigger = right(k);
                }
            }
            if (less(k, bigger)) {
                exch(k, bigger);
                k = bigger;
            }
        }
    }

    public void insert(Value value) {
        num++;
        // 先把新元素加到最后
        pq[num] = value;
        // 然后让它上浮到正确的位置
        swim(num);
    }

    public Value delMax() {
        // 最大堆的堆顶就是最大元素
        Value max = pq[1];
        // 把这个最大元素换到最后，删除之
        exch(1, num);
        pq[num] = null;
        num--;
        // 让 pq[1] 下沉到正确位置
        sink(1);
        return max;
    }

}
