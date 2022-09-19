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
//<div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 2189</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.LinkedList;

import org.example.leetcode.problems.common.linkedlist.ListNode;

//19.删除链表的倒数第 N 个结点
//开题时间：2022-08-28 10:39:31
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
        //1.计算链表长度（单指针） n   1
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int size = getSize(head);

            if (n == size) {
                return head.next;
            } else {
                ListNode pointer = head;
                for (int i = 0; i < size - n - 1; i++) {
                    pointer = pointer.next;
                }
                pointer.next = pointer.next.next;

                return head;
            }
        }

        //2.双指针 n   1
        public ListNode removeNthFromEnd2(ListNode head, int n) {
            ListNode fast = head;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            if (fast == null) {
                return head.next;
            } else {
                ListNode slow = head;
                while (fast.next != null) {
                    slow = slow.next;
                    fast = fast.next;
                }
                slow.next = slow.next.next;
                return head;
            }
        }

        //GJ1.计算链表长度（单指针） n   1
        //要点：使用哨兵节点（哑结点dummy node)
        public ListNode removeNthFromEndGJ1(ListNode head, int n) {
            int size = getSize(head);

            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode pointer = dummy;
            for (int i = 0; i < size - n; i++) {
                pointer = pointer.next;
            }
            pointer.next = pointer.next.next;

            return dummy.next;
        }

        //GJ2.双指针 n   1
        //要点：使用哨兵节点（哑结点dummy node)
        public ListNode removeNthFromEndGJ2(ListNode head, int n) {
            ListNode fast = head;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode slow = dummy;
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
            return dummy.next;
        }

        private int getSize(ListNode head) {
            int size = 0;
            ListNode pointer = head;
            while (pointer != null) {
                size++;
                pointer = pointer.next;
            }
            return size;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}