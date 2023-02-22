//<p>给定一个链表的头节点 &nbsp;<code>head</code>&nbsp;，返回链表开始入环的第一个节点。&nbsp;<em>如果链表无环，则返回&nbsp;<code>null</code>。</em></p>
//
//<p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（<strong>索引从 0 开始</strong>）。如果 <code>pos</code> 是 <code>-1</code>，则在该链表中没有环。<strong>注意：<code>pos</code> 不作为参数进行传递</strong>，仅仅是为了标识链表的实际情况。</p>
//
//<p><strong>不允许修改 </strong>链表。</p>
//
//<ul> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" /></p>
//
//<pre>
//<strong>输入：</strong>head = [3,2,0,-4], pos = 1
//<strong>输出：</strong>返回索引为 1 的链表节点
//<strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" /></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2], pos = 0
//<strong>输出：</strong>返回索引为 0 的链表节点
//<strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" /></p>
//
//<pre>
//<strong>输入：</strong>head = [1], pos = -1
//<strong>输出：</strong>返回 null
//<strong>解释：</strong>链表中没有环。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点的数目范围在范围 <code>[0, 10<sup>4</sup>]</code> 内</li> 
// <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
// <li><code>pos</code> 的值为 <code>-1</code> 或者链表中的一个有效索引</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你是否可以使用 <code>O(1)</code> 空间解决此题？</p>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1821</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import _3_common.entity.linkedlist.ListNode;

import java.util.HashSet;

// 142.环形链表 II
// 开题时间：2022-10-22 08:29:14
public class LinkedListCycleIi {
  public static void main(String[] args) {
    Solution solution = new LinkedListCycleIi().new Solution();
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  /**
   * Definition for singly-linked list.
   * class ListNode {
   * int val;
   * ListNode next;
   * ListNode(int x) {
   * val = x;
   * next = null;
   * }
   * }
   */
  public class Solution {
    // hash
    public ListNode detectCycle2(ListNode head) {
      HashSet<ListNode> seen = new HashSet<>();
      for (ListNode p = head; p != null; p = p.next)
        if (!seen.add(p))
          return p;
      return null;
    }
    
    // a: 起点 -> 入环点，
    // b：入环点 -> 第一次相遇点,
    // c: 第一次相遇点 -> 入环点
    // a=c+(n-1)*(b+c)
    public ListNode detectCycle(ListNode head) {
      for (ListNode slow = head, fast = head; fast != null && fast.next != null; )
        if ((slow = slow.next) == (fast = fast.next.next))
          for (slow = head; ; slow = slow.next, fast = fast.next)
            if (slow == fast)
              return slow;
      return null;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}