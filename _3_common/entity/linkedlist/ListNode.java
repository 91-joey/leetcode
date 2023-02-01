package org.example.leetcode.problems._3_common.entity.linkedlist;

public class ListNode {
  public int val;
  public ListNode next;
  
  public ListNode() {
  }
  
  public ListNode(int val) {
    this.val = val;
  }
  
  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
