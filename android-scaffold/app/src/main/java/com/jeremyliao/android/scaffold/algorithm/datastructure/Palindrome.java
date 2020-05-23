package com.jeremyliao.android.scaffold.algorithm.datastructure;

import com.jeremyliao.android.scaffold.algorithm.datastructure.node.LinkNode;

/**
 * Created by liaohailiang on 2020-05-20.
 */
public class Palindrome {

    public static boolean isPalindrome(LinkNode<Integer> head) {
        LinkNode<Integer> reverse = LinkList.reverse(LinkList.from(LinkList.to(head)));
        LinkNode<Integer> first = head;
        LinkNode<Integer> second = reverse;
        while (first != null && second != null) {
            if (first.value != second.value) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        if (first != null || second != null) {
            return false;
        }
        return true;
    }
}
