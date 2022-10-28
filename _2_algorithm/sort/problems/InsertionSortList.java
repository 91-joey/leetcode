//<p>给定单个链表的头
// <meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，使用 <strong>插入排序</strong> 对链表进行排序，并返回&nbsp;<em>排序后链表的头</em>&nbsp;。</p>
//
//<p><strong>插入排序</strong>&nbsp;算法的步骤:</p>
//
//<ol> 
// <li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li> 
// <li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li> 
// <li>重复直到所有输入数据插入完为止。</li> 
//</ol>
//
//<p>下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。</p>
//
//<p>对链表进行插入排序。</p>
//
//<p><img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif" /></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort1linked-list.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> head = [4,2,1,3]
//<strong>输出:</strong> [1,2,3,4]</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/04/sort2linked-list.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> head = [-1,5,3,4,0]
//<strong>输出:</strong> [-1,0,3,4,5]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li>列表中的节点数在&nbsp;<code>[1, 5000]</code>范围内</li> 
// <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>链表</li><li>排序</li></div></div><br><div><li>👍 548</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;

import java.util.HashMap;
import java.util.Map;

//147.对链表进行插入排序
//开题时间：2022-09-20 14:40:31
public class InsertionSortList {
    public static void main(String[] args) {
        Solution solution = new InsertionSortList().new Solution();
        ListNode head = new ListNode(-1,
                new ListNode(5,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(0, null
                                        )))));
        System.out.println(solution.insertionSortList(head));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode insertionSortList(ListNode head) {
            //哈希映射是多余的，可以从头往后遍历，犯不着往前遍历
            Map<ListNode, ListNode> cur2prev = new HashMap<>();
            ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
            ListNode cur = dummy;
            for (; cur.next != null; cur = cur.next) {
                cur2prev.put(cur.next, cur);
            }
            cur = cur2prev.get(cur);
            for (; cur != null; cur = cur2prev.get(cur)) {
                if (cur.val > cur.next.val) {
                    cur2prev.get(cur).next = cur.next;
                    ListNode node = cur.next;
                    while (node.next != null && cur.val > node.next.val) node = node.next;
                    cur.next = node.next;
                    node.next = cur;
                }
            }
            return dummy.next;
        }

        public ListNode insertionSortListGJ(ListNode head) {
            ListNode dummy = new ListNode(Integer.MIN_VALUE, head);
            for (ListNode cur = head.next, lst = head; cur != null; cur = lst.next) {
                if (lst.val > cur.val) {
                    ListNode prev = dummy;
                    while (cur.val > prev.next.val) prev = prev.next;
                    lst.next = cur.next;
                    cur.next = prev.next;
                    prev.next = cur;
                } else {
                    lst = lst.next;
                }
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}