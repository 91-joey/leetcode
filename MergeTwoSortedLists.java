//<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;" /> 
//<pre>
//<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
//<strong>输出：</strong>[1,1,2,3,4,4]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [], l2 = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [], l2 = [0]
//<strong>输出：</strong>[0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>两个链表的节点数目范围是 <code>[0, 50]</code></li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
// <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2648</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.LinkedList.common.ListNode;

//21.合并两个有序链表
//开题时间：2022-08-31 14:40:02
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
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
        //1.迭代  n+m 1
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode pointer = dummy;

            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    pointer.next = list1;
                    list1 = list1.next;
                } else {
                    pointer.next = list2;
                    list2 = list2.next;
                }
                pointer = pointer.next;
            }
            pointer.next = list1 == null ? list2 : list1;

            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}