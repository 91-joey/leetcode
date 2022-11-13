//<p>给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg" style="width: 422px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,3,4]
//<strong>输出：</strong>[2,1,4,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [1]
//<strong>输出：</strong>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点的数目在范围 <code>[0, 100]</code> 内</li> 
// <li><code>0 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 1624</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;

//24.两两交换链表中的节点
//开题时间：2022-11-12 18:16:31
public class SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode swapPairs9(ListNode head) {
            for (ListNode p = head; p != null && p.next != null; p = p.next.next) {
                int tmp = p.val;
                p.val = p.next.val;
                p.next.val = tmp;
            }
            return head;
        }

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode nextNext = head.next.next;
            ListNode next = head.next;
            next.next = head;
            head.next = swapPairs(nextNext);
            return next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}