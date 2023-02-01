//<p>给定一个已排序的链表的头
// <meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，&nbsp;<em>删除所有重复的元素，使每个元素只出现一次</em>&nbsp;。返回 <em>已排序的链表</em>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" style="height: 160px; width: 200px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,1,2]
//<strong>输出：</strong>[1,2]
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" style="height: 123px; width: 300px;" /> 
//<pre>
//<strong>输入：</strong>head = [1,1,2,3,3]
//<strong>输出：</strong>[1,2,3]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表中节点数目在范围 <code>[0, 300]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
// <li>题目数据保证链表已经按升序 <strong>排列</strong></li> 
//</ul>
//
//<div><li>👍 901</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

//83.删除排序链表中的重复元素
//开题时间：2022-12-05 10:24:13
public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //找到第一个与当前节点值不同的节点
        public ListNode deleteDuplicates9(ListNode head) {
            if (head == null)
                return null;

            ListNode pre = head;
            for (ListNode cur = head.next; cur != null; cur = cur.next) {
                if (pre.val != cur.val) {
                    pre.next = cur;
                    pre = cur;
                }
            }
            pre.next = null;

            return head;
        }

        //递归
        public ListNode deleteDuplicates8(ListNode head) {
            if (head == null)
                return null;

            ListNode next = deleteDuplicates(head.next);
            if (next != null && head.val == next.val)
                head.next = next.next;

            return head;
        }

        //下一个节点与当前节点值相同，就更新指针
        public ListNode deleteDuplicates(ListNode head) {
            for (ListNode cur = head; cur != null && cur.next != null; ) {
                if (cur.val == cur.next.val)
                    cur.next = cur.next.next;
                else
                    cur = cur.next;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}