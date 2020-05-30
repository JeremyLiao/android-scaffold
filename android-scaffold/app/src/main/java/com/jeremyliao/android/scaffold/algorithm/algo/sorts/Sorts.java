package com.jeremyliao.android.scaffold.algorithm.algo.sorts;

/**
 * Created by liaohailiang on 2020-05-28.
 */
public class Sorts {

    public static void bubbleSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void bubbleSort2(int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }
        int n = a.length;
        int sortBorder = n - 1;
        while (sortBorder > 0) {
            boolean flag = false;
            int lastExchangePosition = -1;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                    lastExchangePosition = j;
                }
            }
            sortBorder = lastExchangePosition;
            if (!flag) {
                break;
            }
        }
    }

    public static void insertionSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int target = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (target < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = target;
        }
    }

    public static void selectionSort(int[] a) {
        if (a == null || a.length == 0 || a.length == 1) {
            return;
        }
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }
}
