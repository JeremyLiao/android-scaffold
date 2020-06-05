package com.jeremyliao.android.scaffold.algorithm.datastructure.list;

import android.util.Pair;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.LinkNode;

/**
 * Created by liaohailiang on 2020-05-20.
 */
public class LinkList {

    public static LinkNode<Integer> from(int[] nums) {
        int n = nums.length;
        LinkNode<Integer> root = new LinkNode<>(nums[0]);
        LinkNode<Integer> current = root;
        for (int i = 1; i < n; i++) {
            current.next = new LinkNode<>(nums[i]);
            current = current.next;
        }
        return root;
    }

    public static int[] to(LinkNode<Integer> root) {
        if (root == null) {
            return new int[]{};
        }
        int[] nums = new int[length(root)];
        LinkNode<Integer> current = root;
        int index = 0;
        while (current != null) {
            nums[index] = current.value;
            current = current.next;
            index++;
        }
        return nums;
    }

    public static int length(LinkNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        LinkNode<Integer> current = root;
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public static LinkNode<Integer> reverse(LinkNode<Integer> root) {
        if (root == null) {
            return null;
        }
        if (root.next == null) {
            return root;
        }
        LinkNode<Integer> pre = null;
        LinkNode<Integer> current = root;
        LinkNode<Integer> next = null;
        while (current != null) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static LinkNode<Integer> kreverse(LinkNode<Integer> root, int k) {
        if (root == null) {
            return null;
        }
        if (root.next == null) {
            return root;
        }
        if (length(root) < k) {
            return root;
        }
        LinkNode<Integer> pre = null;
        LinkNode<Integer> current = root;
        LinkNode<Integer> next = null;
        int count = 0;
        while (current != null && count < k) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
            count++;
        }
        root.next = current;
        return pre;
    }

    public static LinkNode<Integer> loopkreverse(LinkNode<Integer> root, int k) {
        if (root == null) {
            return null;
        }
        if (root.next == null) {
            return root;
        }
        if (length(root) < k) {
            return root;
        }
        LinkNode<Integer> kreverse = kreverse(root, k);
        root.next = loopkreverse(root.next, k);
        return kreverse;
    }
}
