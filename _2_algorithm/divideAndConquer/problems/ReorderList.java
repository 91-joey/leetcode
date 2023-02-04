//<p>给定一个单链表 <code>L</code><em> </em>的头节点 <code>head</code> ，单链表 <code>L</code> 表示为：</p>
//
//<pre>
// L<sub>0</sub> → L<sub>1</sub> → … → L<sub>n - 1</sub> → L<sub>n</sub>
//</pre>
//
//<p>请将其重新排列后变为：</p>
//
//<pre>
// L<sub>0</sub> → L<sub>n</sub> → L<sub>1</sub> → L<sub>n - 1</sub> → L<sub>2</sub> → L<sub>n - 2</sub> → …</pre>
//
//<p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png" style="width: 240px; " /></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2,3,4]
//<strong>输出：</strong>[1,4,2,3]</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://pic.leetcode-cn.com/1626420320-YUiulT-image.png" style="width: 320px; " /></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5]
//<strong>输出：</strong>[1,5,2,4,3]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表的长度范围为 <code>[1, 5 * 10<sup>4</sup>]</code></li> 
// <li><code>1 &lt;= node.val &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 1077</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

// 143.重排链表
// 开题时间：2022-11-13 15:15:18
public class ReorderList {
  public static void main(String[] args) {
    Solution solution = new ReorderList().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 迭代 + 栈
    public void reorderList9(ListNode head) {
      // 寻找链表中点
      ListNode slow = head, fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      
      // 栈存储右半部分节点
      Deque<ListNode> stack = new LinkedList<>();
      for (ListNode p = slow; p != null; p = p.next)
        stack.push(p);
      
      // 重新排列
      ListNode p = head;
      while (p != slow) {
        ListNode pop = stack.pop();
        pop.next = p.next;
        p.next = pop;
        p = pop.next;
      }
      p.next = null;
    }
    
    Deque<ListNode> stack;
    
    // 递归 + 栈
    public void reorderList8(ListNode head) {
      stack = new LinkedList<>();
      ListNode slow = head, fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      for (ListNode p = slow; p != null; p = p.next)
        stack.push(p);
      
      reorder(head);
    }
    
    private ListNode reorder(ListNode head) {
      if (head == null)
        return null;
      ListNode pop = stack.pop();
      if (pop == head || pop == head.next) {
        pop.next = null;
        return head;
      }
      pop.next = reorder(head.next);
      head.next = pop;
      return head;
    }
    
    // GJ1 线性表（集合）
    public void reorderList7(ListNode head) {
      ArrayList<ListNode> nodes = new ArrayList<>();
      for (ListNode p = head; p != null; p = p.next)
        nodes.add(p);
      
      int l = 0, r = nodes.size() - 1;
      while (l + 1 < r) {
        nodes.get(l).next = nodes.get(r);
        nodes.get(r--).next = nodes.get(++l);
      }
      nodes.get(r).next = null;
    }
    
    //☆☆☆☆☆ 寻找链表中点 + 反转右半链表 + 合并左右链表
    public void reorderList(ListNode head) {
      // 寻找链表中点
      ListNode slow = head, fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      
      // 反转右半链表
      ListNode pre = null, cur = slow;
      while (cur != null) {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
      }
      
      // 合并左右链表
      ListNode l = head;
      while (pre.next != null) {
        ListNode next = pre.next;
        pre.next = l.next;
        l.next = pre;
        l = l.next.next;
        pre = next;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}