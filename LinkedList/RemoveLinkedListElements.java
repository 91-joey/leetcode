//给你一个链表的头节点 <code>head</code> 和一个整数 <code>val</code> ，请你删除链表中所有满足 <code>Node.val == val</code> 的节点，并返回 <strong>新的头节点</strong> 。
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
//<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 1008</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

import org.example.leetcode.problems.common.ListNode;

//203.移除链表元素
//开题时间：2022-08-30 08:51:18
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new RemoveLinkedListElements().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)

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
        //1.迭代+哨兵节点
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head);

            ListNode pointer = dummy;
            while (pointer.next != null) {
                if (pointer.next.val == val) {
                    pointer.next = pointer.next.next;
                } else {
                    pointer = pointer.next;
                }
            }

            return dummy.next;
        }

        //GJ1:递归
        public ListNode removeElementsGJ1(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            head.next = removeElementsGJ1(head.next, val);
            return head.val == val ? head.next : head;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}