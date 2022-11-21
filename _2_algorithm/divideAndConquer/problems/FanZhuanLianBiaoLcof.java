//<p>定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例:</strong></p>
//
//<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL
//<strong>输出:</strong> 5-&gt;4-&gt;3-&gt;2-&gt;1-&gt;NULL</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<p><code>0 &lt;= 节点个数 &lt;= 5000</code></p>
//
//<p>&nbsp;</p>
//
//<p><strong>注意</strong>：本题与主站 206 题相同：<a href="https://leetcode-cn.com/problems/reverse-linked-list/">https://leetcode-cn.com/problems/reverse-linked-list/</a></p>
//
//<div><li>👍 505</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;

//剑指 Offer 24.反转链表
//开题时间：2022-11-12 16:59:19
public class FanZhuanLianBiaoLcof {
    public static void main(String[] args) {
        Solution solution = new FanZhuanLianBiaoLcof().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        ListNode newHead;

        //递归（单参）
        public ListNode reverseList9(ListNode head) {
            if (head == null)
                return null;
            reverse(head);
            head.next = null;
            return newHead;
        }

        private void reverse(ListNode head) {
            if (head.next == null) {
                newHead = head;
                return;
            }
            reverse(head.next);
            head.next.next = head;
        }

        //迭代（相较于递归更易懂）
        public ListNode reverseList8(ListNode head) {
            ListNode pre = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        //递归（双参）
        public ListNode reverseList(ListNode head) {
            if (head == null)
                return null;
            reverse2(null, head);
            return newHead;
        }

        private void reverse2(ListNode pre, ListNode cur) {
            if (cur == null) {
                newHead = pre;
                return;
            }
            reverse2(cur, cur.next);
            cur.next = pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}