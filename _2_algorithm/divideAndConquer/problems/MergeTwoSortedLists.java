//<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" /> 
//<pre>
//<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
//<strong>输出：</strong>[1,1,2,3,4,4]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [], l2 = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [], l2 = [0]
//<strong>输出：</strong>[0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>两个链表的节点数目范围是 <code>[0, 50]</code></li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
// <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li> 
//</ul>
//
//<div><li>👍 2781</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

// 21.合并两个有序链表
// 开题时间：2022-11-12 14:51:41
public class MergeTwoSortedLists {
  public static void main(String[] args) {
    Solution solution = new MergeTwoSortedLists().new Solution();
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
    //☆☆☆☆☆ 迭代（空间效率高，难上手）
    public ListNode mergeTwoLists9(ListNode A, ListNode B) {
      ListNode dummy = new ListNode();
      ListNode cur = dummy;
      while (A != null && B != null) {
        if (A.val <= B.val) {
          cur.next = A;
          A = A.next;
        } else {
          cur.next = B;
          B = B.next;
        }
        cur = cur.next;
      }
      cur.next = A == null ? B : A;
      return dummy.next;
    }
    
    // 递归（空间效率低，易上手）
    public ListNode mergeTwoLists(ListNode A, ListNode B) {
      if (A == null)
        return B;
      else if (B == null)
        return A;
      else if (A.val <= B.val) {
        A.next = mergeTwoLists(A.next, B);
        return A;
      } else {
        B.next = mergeTwoLists(B.next, A);
        return B;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}