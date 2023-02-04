// 给你单链表的头指针 <code>head</code> 和两个整数 <code>left</code> 和 <code>right</code> ，其中 <code>left <= right</code> 。请你反转从位置 <code>left</code> 到位置 <code>right</code> 的链表节点，返回 <strong>反转后的链表</strong> 。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg" style="width: 542px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5], left = 2, right = 4
//<strong>输出：</strong>[1,4,3,2,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [5], left = 1, right = 1
//<strong>输出：</strong>[5]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点数目为 <code>n</code></li> 
// <li><code>1 &lt;= n &lt;= 500</code></li> 
// <li><code>-500 &lt;= Node.val &lt;= 500</code></li> 
// <li><code>1 &lt;= left &lt;= right &lt;= n</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong> 你可以使用一趟扫描完成反转吗？</p>
//
//<div><li>👍 1438</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

// 92.反转链表 II
// 开题时间：2022-11-15 12:02:24
public class ReverseLinkedListIi {
  public static void main(String[] args) {
    Solution solution = new ReverseLinkedListIi().new Solution();
    System.out.println(solution.reverseBetween(new ListNode(3, new ListNode(5)), 1, 2));
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   * int val;
   * ListNode next;
   * ListNode() {}
   * ListNode(int val) { this.val = val; }
   * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */
  class Solution {
    // 一次遍历
    public ListNode reverseBetween9(ListNode head, int left, int right) {
      ListNode dummy = new ListNode(0, head);
      ListNode l = dummy;
      for (int i = 1; i < left; i++)
        l = l.next;
      ListNode newRight = l.next;
      
      ListNode pre = null;
      ListNode cur = newRight;
      for (int i = left; i <= right; i++) {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
      }
      
      l.next = pre;
      newRight.next = cur;
      
      return dummy.next;
    }
    
    // 二次遍历
    public ListNode reverseBetween(ListNode head, int left, int right) {
      // 1.设置哑结点
      ListNode dummy = new ListNode(0, head);
      ListNode pre = dummy;
      
      // 2.找到反转链表的前驱节点pre和头结点newRight
      for (int i = 1; i < left; i++)
        pre = pre.next;
      ListNode newRight = pre.next;
      
      // 3.找到反转链表的尾结点newLeft
      ListNode newLeft = newRight;
      for (int i = left; i < right; i++)
        newLeft = newLeft.next;
      
      // 4.断开反转链表的尾结点newLeft 与 后继节点 的连接
      ListNode suc = newLeft.next;
      newLeft.next = null;
      
      // 5.反转链表
      reverseListGJ1(newRight);
      
      // 6.拼接
      pre.next = newLeft;
      newRight.next = suc;
      
      return dummy.next;
    }
    
    public static ListNode reverseListGJ1(ListNode head) {
      ListNode pre = null;
      ListNode cur = head;
      while (cur != null) {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
      }
      
      return pre;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}