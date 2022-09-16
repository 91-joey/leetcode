//给你单链表的头节点 <code>head</code> ，请你反转链表，并返回反转后的链表。
//
//<div class="original__bRMd"> 
// <div> 
//  <p>&nbsp;</p> 
// </div>
//</div>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" style="width: 542px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5]
//<strong>输出：</strong>[5,4,3,2,1]
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" style="width: 182px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2]
//<strong>输出：</strong>[2,1]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = []
//<strong>输出：</strong>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点的数目范围是 <code>[0, 5000]</code></li> 
// <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？</p>
//
//<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2725</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

import org.example.leetcode.problems.common.linkedlist.ListNode;

//206.反转链表
//开题时间：2022-08-29 11:33:22
public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.reverseList3(listNode1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public static class Solution {
        //1.迭代+哨兵节点 n   1
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode dummy = new ListNode(0, head);
            while (head.next != null) {
                ListNode nextNext = head.next.next;
                head.next.next = dummy.next;
                dummy.next = head.next;
                head.next = nextNext;
            }

            return dummy.next;
        }

        //2.迭代+无哨兵节点    n   1
        public ListNode reverseList2(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode newHead = head;
            while (head.next != null) {
                ListNode nextNext = head.next.next;
                head.next.next = newHead;
                newHead = head.next;
                head.next = nextNext;
            }

            return newHead;
        }

        ListNode newHead3;

        //3.递归  n   n
        public ListNode reverseList3(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            nextToPrev(null, head);
            return newHead3;
        }

        private void nextToPrev(ListNode prev, ListNode next) {
            if (next.next != null) {
                nextToPrev(next, next.next);
            } else {
                newHead3 = next;
            }
            next.next = prev;
        }

        //☆☆☆☆☆GJ1.迭代   n   1
        public static ListNode reverseListGJ1(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }

        //GJ2.递归    n   n
        public ListNode reverseListGJ2(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = reverseListGJ2(head.next);
            head.next.next = head;
            //此处循环附空值，多余
            head.next = null;
            return newHead;
        }

        //4.递归(返回值循环传递) n   n
        public ListNode reverseList4(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            return nextToPrev2(null, head);
        }

        private ListNode nextToPrev2(ListNode prev, ListNode next) {
            ListNode nextNext = next.next;
            next.next = prev;
            if (nextNext != null) {
                return nextToPrev2(next, nextNext);
            } else {
                return next;
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}