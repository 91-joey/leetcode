//<p>给你一个链表的头节点 <code>head</code> ，旋转链表，将链表每个节点向右移动&nbsp;<code>k</code><em>&nbsp;</em>个位置。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg" style="width: 450px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5], k = 2
//<strong>输出：</strong>[4,5,1,2,3]
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg" style="width: 305px; height: 350px;" /> 
//<pre>
//<strong>输入：</strong>head = [0,1,2], k = 4
//<strong>输出：</strong>[2,0,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点的数目在范围 <code>[0, 500]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
// <li><code>0 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 819</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.LinkedList;

import org.example.leetcode.problems.common.linkedlist.ListNode;

//61.旋转链表
//开题时间：2022-09-02 15:12:14
public class RotateList {
    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
        System.out.println(solution.rotateRight(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
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
        public ListNode rotateRight(ListNode head, int k) {
            int size = 1;
            ListNode tail = head;
            //遍历得到原尾节点tail，同时计算链表长度size
            for (; tail != null && tail.next != null; tail = tail.next) {
                size++;
            }

            int cnt = k % size;
            //k 为 size 的倍数时，或者空单节点时，返回原链表
            if (cnt == 0 || size <= 1) {
                return head;
            }

            ListNode tailNew = head;
            for (int i = 1; i < size - cnt; i++) {
                tailNew = tailNew.next;
            }
            ListNode headNew = tailNew.next;
            //新尾节点 -> null
            tailNew.next = null;
            //原尾节点 -> 原头节点
            tail.next = head;

            return headNew;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}