//<p>给你一个单链表的头节点 <code>head</code> ，请你判断该链表是否为回文链表。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg" style="width: 422px; height: 62px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,2,1]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/03/pal2linked-list.jpg" style="width: 182px; height: 62px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点数目在范围<code>[1, 10<sup>5</sup>]</code> 内</li> 
// <li><code>0 &lt;= Node.val &lt;= 9</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你能否用&nbsp;<code>O(n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度解决此题？</p>
//
//<div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1489</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.LinkedList;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;

import java.util.Deque;
import java.util.LinkedList;

//234.回文链表
//开题时间：2022-08-30 11:06:13
public class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
        ListNode listNode4 = new ListNode(1);
        ListNode listNode3 = new ListNode(2, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.isPalindrome(listNode1));
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
//    1234567
    class Solution {
        //1.双指针+双端队列 n   n
        public boolean isPalindrome(ListNode head) {
            Deque<Integer> deque = new LinkedList<>();
            while (head != null) {
                deque.push(head.val);
                head = head.next;
            }

            while (deque.size() > 1) {
                if (deque.removeFirst() != deque.removeLast()) {
                    return false;
                }
            }

            return true;
        }

        //GJ3.快慢指针   n   1
        public boolean isPalindromeGJ1(ListNode head) {
            //找到中间节点
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            //反转右半部分
            ListNode headOfReversedRightHalf = ReverseLinkedList.Solution.reverseListGJ1(slow);
            //比较左半部分和反转后的右半部分
            while (headOfReversedRightHalf != null) {
                if (head.val != headOfReversedRightHalf.val) {
                    return false;
                }
                head = head.next;
                headOfReversedRightHalf = headOfReversedRightHalf.next;
            }
            return true;
        }

        //2.高分解
        public boolean isPalindrome2(ListNode head) {
            //找到中间节点，同时反转左半部分
            ListNode prev = null;
            ListNode curr = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;

                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            //奇数节点 -> 忽略中间节点
            if (fast != null) {
                curr = curr.next;
            }
            //比较反转后的左半部分和右半部分
            while (prev != null) {
                if (prev.val != curr.val) {
                    return false;
                }
                prev = prev.next;
                curr = curr.next;
            }
            return true;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
}