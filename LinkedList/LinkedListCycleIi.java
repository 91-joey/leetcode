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
//<div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1736</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

import java.util.HashSet;
import java.util.Set;

//142.环形链表 II
//开题时间：2022-08-27 09:50:57
public class LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();
        ListNode listNode0 = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(7);
        ListNode listNode7 = new ListNode(8);
        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(10);
        listNode0.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode9;
        System.out.println(solution.detectCycle2(listNode0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        //1.哈希表 n   n
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> visited = new HashSet<>();

            while (head != null) {
                if (visited.contains(head)) {
                    return head;
                } else {
                    visited.add(head);
                    head = head.next;
                }
            }

            return null;
        }

        //2.自解：双指针 n   1
        public ListNode detectCycle2(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            int cnt1 = 0;
            int cnt2 = 0;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                cnt1++;
                if (slow == fast) {
                    do {
                        slow = slow.next;
                        fast = fast.next.next;
                        cnt2++;
                    } while (slow != fast);
                    break;
                }
            }

            if (cnt2 == 0) {
                return null;
            }

            slow = head;
            for (int i = 0; i < cnt1 - cnt2; i++) {
                slow = slow.next;
            }
            for (int i = 0; i <= cnt2; i++) {
                if (slow == fast) {
                    return slow;
                }
                slow = slow.next;
                fast = fast.next;
            }

            return null;
        }
    }

    //3.官解：双指针 n   1
    //a: 起点 -> 入环点，
    //b：入环点 -> 第一次相遇点,
    //c: 第一次相遇点 -> 入环点
    //a=c+(n-1)*(b+c)
    public ListNode detectCycle3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow=head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}