//<p>给你两个&nbsp;<strong>非空</strong> 的链表，表示两个非负的整数。它们每位数字都是按照&nbsp;<strong>逆序</strong>&nbsp;的方式存储的，并且每个节点只能存储&nbsp;<strong>一位</strong>&nbsp;数字。</p>
//
//<p>请你将两个数相加，并以相同形式返回一个表示和的链表。</p>
//
//<p>你可以假设除了数字 0 之外，这两个数都不会以 0&nbsp;开头。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg" style="width: 483px; height: 342px;" /> 
//<pre>
//<strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
//<strong>输出：</strong>[7,0,8]
//<strong>解释：</strong>342 + 465 = 807.
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [0], l2 = [0]
//<strong>输出：</strong>[0]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//<strong>输出：</strong>[8,9,9,9,0,0,0,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>每个链表中的节点数在范围 <code>[1, 100]</code> 内</li> 
// <li><code>0 &lt;= Node.val &lt;= 9</code></li> 
// <li>题目数据保证列表表示的数字不含前导零</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>递归</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 8571</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

import org.example.leetcode.problems.common.ListNode;

//2.两数相加
//开题时间：2022-09-01 08:04:19
public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(9)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        System.out.println(solution.addTwoNumbers(l1, l2));
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
        //1.自解  max(m,n)    1
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode ans = l1;
            ListNode p = ans;
            int incre = 0;
            while (l1 != null || l2 != null) {
                int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + incre;
                p.val = sum % 10;
                incre = sum < 10 ? 0 : 1;
                if (incre == 1 && (l1 == null || l1.next == null) && (l2 == null || l2.next == null)) {
                    p.next = new ListNode(incre);
                    break;
                }
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
                if (p.next == null) {
                    p.next = l2;
                }
                p = p.next;
            }

            return ans;
        }

        //2.高分解 max(m,n)    1
        public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            //进位值
            int carry = 0;
            while (l1 != null || l2 != null) {
                int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
                p.next = new ListNode(sum % 10);
                carry = sum / 10;
                l1 = l1 == null ? null : l1.next;
                l2 = l2 == null ? null : l2.next;
                p = p.next;
            }
            if (carry > 0) {
                p.next = new ListNode(carry);
            }

            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}