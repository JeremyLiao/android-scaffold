package com.jeremyliao.android.scaffold.algorithm.datastructure.tree;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.DoubleLinkNode;
import com.jeremyliao.android.scaffold.algorithm.datastructure.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaohailiang on 2020-06-01.
 */
public class Bst2Dll {

    public static DoubleLinkNode<Integer> bst2Dll(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        DoubleLinkNode<Integer> node = new DoubleLinkNode<>(root.value);
        insertLeft(node, root.left);
        insertRight(node, root.right);
        DoubleLinkNode<Integer> head = node;
        while (head.previous != null) {
            head = head.previous;
        }
        return head;
    }

    private static void insertLeft(DoubleLinkNode<Integer> current, TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        DoubleLinkNode<Integer> newDllNode = new DoubleLinkNode<>(node.value);
        newDllNode.previous = current.previous;
        newDllNode.next = current;
        if (current.previous != null) {
            current.previous.next = newDllNode;
        }
        current.previous = newDllNode;
        insertLeft(newDllNode, node.left);
        insertRight(newDllNode, node.right);
    }

    private static void insertRight(DoubleLinkNode<Integer> current, TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        DoubleLinkNode<Integer> newDllNode = new DoubleLinkNode<>(node.value);
        newDllNode.next = current.next;
        newDllNode.previous = current;
        if (current.next != null) {
            current.next.previous = newDllNode;
        }
        current.next = newDllNode;
        insertLeft(newDllNode, node.left);
        insertRight(newDllNode, node.right);
    }

    public static int[] toArray(DoubleLinkNode<Integer> head) {
        List<Integer> list = new ArrayList<>();
        DoubleLinkNode<Integer> current = head;
        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
