package _1_dataStructure.LinkedList;

import _3_common.entity.linkedlist.ListNode;

/**
 * 1669.合并两个链表 <br>
 * 开题时间：2023-01-30 10:46:54
 */
public class MergeInBetweenLinkedLists {
  public static void main(String[] args) {
    Solution solution = new MergeInBetweenLinkedLists().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public ListNode mergeInBetween9(ListNode list1, int a, int b, ListNode list2) {
      ListNode p1 = list1;
      for (int i = 0; i < a - 1; i++) {
        p1 = p1.next;
      }
      ListNode p2 = p1.next;
      p1.next = list2;
      
      for (int i = 0; i < b - a + 1; i++) {
        p2 = p2.next;
      }
      while (list2.next != null) {
        list2 = list2.next;
      }
      list2.next = p2;
      
      return list1;
    }
    
    public ListNode mergeInBetween8(ListNode list1, int a, int b, ListNode list2) {
      ListNode reer = list1;
      for (int i = 0; i < b - a + 2; i++) {
        reer = reer.next;
      }
      
      ListNode front = list1;
      for (int i = 0; i < a - 1; i++) {
        front = front.next;
        reer = reer.next;
      }
      
      front.next = list2;
      while (front.next != null) {
        front = front.next;
      }
      front.next = reer;
      return list1;
    }
    
    // ☆☆☆☆☆ 双指针模拟
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
      ListNode preA = list1;
      for (int i = 0; i < a - 1; i++) {
        preA = preA.next;
      }
      
      ListNode aftB = preA;
      for (int i = 0; i < b - a + 2; i++) {
        aftB = aftB.next;
      }
      
      preA.next = list2;
      while (list2.next != null) {
        list2 = list2.next;
      }
      list2.next = aftB;
      
      return list1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}