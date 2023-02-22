// 给你一个链表的头节点 <code>head</code> 和一个整数 <code>val</code> ，请你删除链表中所有满足 <code>Node.val == val</code> 的节点，并返回 <strong>新的头节点</strong> 。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg" style="width: 500px; height: 142px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,6,3,4,5,6], val = 6
//<strong>输出：</strong>[1,2,3,4,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [], val = 1
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [7,7,7,7], val = 7
//<strong>输出：</strong>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>列表中的节点数目在范围 <code>[0, 10<sup>4</sup>]</code> 内</li> 
// <li><code>1 &lt;= Node.val &lt;= 50</code></li> 
// <li><code>0 &lt;= val &lt;= 50</code></li> 
//</ul>
//
//<div><li>👍 1080</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import _3_common.entity.linkedlist.ListNode;

// 203.移除链表元素
// 开题时间：2022-11-12 17:50:25
public class RemoveLinkedListElements {
  public static void main(String[] args) {
    Solution solution = new RemoveLinkedListElements().new Solution();
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
    // 迭代（易懂）
    public ListNode removeElements9(ListNode head, int val) {
      ListNode dummy = new ListNode(0, head);
      for (ListNode p = dummy; p.next != null; )
        if (p.next.val == val)
          p.next = p.next.next;
        else
          p = p.next;
      return dummy.next;
    }
    
    // 递归（难懂）
    public ListNode removeElements8(ListNode head, int val) {
      if (head == null)
        return null;
      else if (head.val == val)
        return removeElements8(head.next, val);
      
      head.next = removeElements8(head.next, val);
      
      return head;
    }
    
    public ListNode removeElements(ListNode head, int val) {
      if (head == null)
        return null;
      
      head.next = removeElements(head.next, val);
      
      return head.val == val ? head.next : head;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}