package org.example.leetcode.problems.LinkedList.common;

public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
        next.prev = this;
    }

    public Node(int val, Node next,Node child) {
        this.val = val;
        this.next = next;
        next.prev = this;
        this.child=child;
    }
};