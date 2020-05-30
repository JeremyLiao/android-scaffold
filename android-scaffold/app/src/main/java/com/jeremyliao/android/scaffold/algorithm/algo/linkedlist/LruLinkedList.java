package com.jeremyliao.android.scaffold.algorithm.algo.linkedlist;


/**
 * Created by liaohailiang on 2020-05-28.
 */
public class LruLinkedList<T> {

    /**
     * 默认链表容量
     */
    private final static int DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 链表长度
     */
    private int length = 0;

    /**
     * 链表容量
     */
    private int capacity = DEFAULT_CAPACITY;

    public LruLinkedList(int capacity) {
        this.capacity = capacity;
    }

    public LruLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }
        Node preNode = findPreNode(data);
        if (preNode != null) {
            preNode.next = preNode.next.next;
            length--;
            insertAtHead(data);
        } else {
            if (length > capacity) {
                deleteAtTail();
            }
            insertAtHead(data);
        }
    }

    private void insertAtHead(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
        length++;
    }

    private void deleteAtTail() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            length--;
            return;
        }
        Node p = head;
        Node q = head;
        while (p.next != null) {
            q = p;
            p = p.next;
        }
        q.next = null;
        length--;

    }

    private Node findPreNode(T data) {
        if (head == null) {
            return null;
        }
        Node p = head;
        Node q = null;
        while (p != null && p.value != data) {
            q = p;
            p = p.next;
        }
        if (p == null) {
            return null;
        }
        return q;
    }

    public static class Node<T> {
        public Node<T> next;
        public T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
