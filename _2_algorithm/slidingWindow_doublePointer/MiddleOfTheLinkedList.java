//<p>给定一个头结点为 <code>head</code>&nbsp;的非空单链表，返回链表的中间结点。</p>
//
//<p>如果有两个中间结点，则返回第二个中间结点。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>[1,2,3,4,5]
//<strong>输出：</strong>此列表中的结点 3 (序列化形式：[3,4,5])
// 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
// 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
// ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>[1,2,3,4,5,6]
//<strong>输出：</strong>此列表中的结点 4 (序列化形式：[4,5,6])
// 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>给定链表的结点数介于&nbsp;<code>1</code>&nbsp;和&nbsp;<code>100</code>&nbsp;之间。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 716</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import _3_common.entity.linkedlist.ListNode;

// 876.链表的中间结点
// 开题时间：2022-10-21 18:18:24
public class MiddleOfTheLinkedList {
  public static void main(String[] args) {
    Solution solution = new MiddleOfTheLinkedList().new Solution();
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  class Solution {
    public ListNode middleNode(ListNode head) {
      ListNode mid = head, fast = head;
      while (fast != null && fast.next != null) {
        mid = mid.next;
        fast = fast.next.next;
      }
      return mid;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}