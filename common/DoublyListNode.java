package org.example.leetcode.problems.common;

public class DoublyListNode {
    public int val;
    public DoublyListNode prev;
    public DoublyListNode next;

    public DoublyListNode() {
    }

    public DoublyListNode(int val) {
        this.val = val;
    }

    public DoublyListNode(int val, DoublyListNode prev, DoublyListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
