//<p>给你一个链表，删除链表的倒数第&nbsp;<code>n</code><em>&nbsp;</em>个结点，并且返回链表的头结点。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg" style="width: 542px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5], n = 2
//<strong>输出：</strong>[1,2,3,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [1], n = 1
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2], n = 1
//<strong>输出：</strong>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中结点的数目为 <code>sz</code></li> 
// <li><code>1 &lt;= sz &lt;= 30</code></li> 
// <li><code>0 &lt;= Node.val &lt;= 100</code></li> 
// <li><code>1 &lt;= n &lt;= sz</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你能尝试使用一趟扫描实现吗？</p>
//
//<div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 2269</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.common.linkedlist.ListNode;

//19.删除链表的倒数第 N 个结点
//开题时间：2022-10-21 17:45:09
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
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
        public ListNode removeNthFromEnd(ListNode head, int n) {

            //计算链表长度
            int sz = 1;
            for (ListNode p = head.next; p != null; p = p.next)
                sz++;

            //哑结点
            ListNode dummy = new ListNode(-1, head);
            //获得待删节点的前节点
            ListNode pre = dummy;
            for (int i = 0; i < sz - n; i++)
                pre = pre.next;

            //将「待删节点的前节点」指针指向「待删节点的后节点」
            pre.next = pre.next.next;
            return dummy.next;
        }

        public ListNode removeNthFromEnd2(ListNode head, int n) {
            //先行节点
            ListNode right = head;
            for (int i = 0; i < n; i++)
                right = right.next;

            //哑结点
            ListNode dummy = new ListNode(-1, head);
            //获得待删节点的前节点
            ListNode pre = dummy;
            while (right != null) {
                pre = pre.next;
                right = right.next;
            }

            //将「待删节点的前节点」指针指向「待删节点的后节点」
            pre.next = pre.next.next;
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}