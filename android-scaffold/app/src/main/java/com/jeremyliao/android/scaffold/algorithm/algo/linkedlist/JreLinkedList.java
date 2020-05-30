package com.jeremyliao.android.scaffold.algorithm.algo.linkedlist;

/**
 * Created by liaohailiang on 2020-05-26.
 */
public class JreLinkedList {

    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int currentIndex = 0;
        while (p != null) {
            if (currentIndex == index) {
                return p;
            }
            currentIndex++;
            p = p.next;
        }
        return null;
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertToHead(int value) {
        insertToHead(new Node(value));
    }

    public void insertTail(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = newNode;
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertAfter(Node p, int value) {
        insertAfter(p, new Node(value));
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) {
            return;
        }
        if (p == head) {
            insertToHead(newNode);
        }
        Node node = head;
        while (node != null && node.next != p) {
            node = node.next;
        }
        if (node == null) {
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        insertBefore(p, new Node(value));
    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        Node node = head;
        while (node != null && node.next != p) {
            node = node.next;
        }
        if (node == null) {
            return;
        }
        node.next = p.next;
    }

    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        Node p = head;
        Node q = head;
        while (p != null && p.value != value) {
            q = p;
            p = p.next;
        }
        if (p == null) {
            return;
        }
        q.next = p.next;
    }

    public static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
