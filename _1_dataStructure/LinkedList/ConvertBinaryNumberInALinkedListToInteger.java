//<p>给你一个单链表的引用结点&nbsp;<code>head</code>。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。</p>
//
//<p>请你返回该链表所表示数字的 <strong>十进制值</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/12/15/graph-1.png" style="height: 108px; width: 426px;" /></p>
//
//<pre><strong>输入：</strong>head = [1,0,1]
//<strong>输出：</strong>5
//<strong>解释：</strong>二进制数 (101) 转化为十进制数 (5)
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>head = [0]
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>head = [1]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
//<strong>输出：</strong>18880
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre><strong>输入：</strong>head = [0,0]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>链表不为空。</li> 
// <li>链表的结点总数不超过&nbsp;<code>30</code>。</li> 
// <li>每个结点的值不是&nbsp;<code>0</code> 就是 <code>1</code>。</li> 
//</ul>
//
//<div><li>👍 135</li><li>👎 0</li></div>
package _1_dataStructure.LinkedList;

import _3_common.entity.linkedlist.ListNode;

// 1290.二进制链表转整数
// 开题时间：2022-12-10 09:33:12
public class ConvertBinaryNumberInALinkedListToInteger {
  public static void main(String[] args) {
    Solution solution = new ConvertBinaryNumberInALinkedListToInteger().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int getDecimalValue9(ListNode head) {
      int n = head.val;
      head = head.next;
      while (head != null) {
        n <<= 1;
        n |= head.val;
        head = head.next;
      }
      return n;
    }
    
    public int getDecimalValue8(ListNode head) {
      String s = "";
      for (ListNode node = head; node != null; node = node.next)
        s += node.val;
      return Integer.parseInt(s, 2);
    }
    
    public int getDecimalValue(ListNode head) {
      int n = 0;
      while (head != null) {
        n <<= 1;
        n |= head.val;
        head = head.next;
      }
      return n;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}