//<p>给你两个单链表的头节点&nbsp;<code>headA</code> 和 <code>headB</code> ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 <code>null</code> 。</p>
//
//<p>图示两个链表在节点 <code>c1</code> 开始相交<strong>：</strong></p>
//
//<p><a href="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" target="_blank"><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" style="height: 130px; width: 400px;" /></a></p>
//
//<p>题目数据 <strong>保证</strong> 整个链式结构中不存在环。</p>
//
//<p><strong>注意</strong>，函数返回结果后，链表必须 <strong>保持其原始结构</strong> 。</p>
//
//<p><strong>自定义评测：</strong></p>
//
//<p><strong>评测系统</strong> 的输入如下（你设计的程序 <strong>不适用</strong> 此输入）：</p>
//
//<ul> 
// <li><code>intersectVal</code> - 相交的起始节点的值。如果不存在相交节点，这一值为 <code>0</code></li> 
// <li><code>listA</code> - 第一个链表</li> 
// <li><code>listB</code> - 第二个链表</li> 
// <li><code>skipA</code> - 在 <code>listA</code> 中（从头节点开始）跳到交叉节点的节点数</li> 
// <li><code>skipB</code> - 在 <code>listB</code> 中（从头节点开始）跳到交叉节点的节点数</li> 
//</ul>
//
//<p>评测系统将根据这些输入创建链式数据结构，并将两个头节点 <code>headA</code> 和 <code>headB</code> 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 <strong>视作正确答案</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png" target="_blank"><img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png" style="height: 130px; width: 400px;" /></a></p>
//
//<pre>
//<strong>输入：</strong>intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
//<strong>输出：</strong>Intersected at '8'
//<strong>解释：</strong>相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
// 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
// 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png" target="_blank"><img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png" style="height: 136px; width: 350px;" /></a></p>
//
//<pre>
//<strong>输入：</strong>intersectVal&nbsp;= 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
//<strong>输出：</strong>Intersected at '2'
//<strong>解释：</strong>相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
// 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
// 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
//</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png" target="_blank"><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png" style="height: 126px; width: 200px;" /></a></p>
//
//<pre>
//<strong>输入：</strong>intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//<strong>输出：</strong>null
//<strong>解释：</strong>从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
// 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
// 这两个链表不相交，因此返回 null 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>listA</code> 中节点数目为 <code>m</code></li> 
// <li><code>listB</code> 中节点数目为 <code>n</code></li> 
// <li><code>1 &lt;= m, n &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= skipA &lt;= m</code></li> 
// <li><code>0 &lt;= skipB &lt;= n</code></li> 
// <li>如果 <code>listA</code> 和 <code>listB</code> 没有交点，<code>intersectVal</code> 为 <code>0</code></li> 
// <li>如果 <code>listA</code> 和 <code>listB</code> 有交点，<code>intersectVal == listA[skipA] == listB[skipB]</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你能否设计一个时间复杂度 <code>O(m + n)</code> 、仅用 <code>O(1)</code> 内存的解决方案？</p>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1827</li><li>👎 0</li></div>
package _1_dataStructure.LinkedList;

import java.util.HashSet;
import java.util.Set;

// 160.相交链表
// 开题时间：2022-08-28 08:35:03
public class IntersectionOfTwoLinkedLists {
  public static void main(String[] args) {
    Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   * int val;
   * ListNode next;
   * ListNode(int x) {
   * val = x;
   * next = null;
   * }
   * }
   */
  public class Solution {
    // 1.hashmap m+n m
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
      Set<ListNode> visited = new HashSet<>();
      
      ListNode pointer = headA;
      while (pointer != null) {
        visited.add(pointer);
        pointer = pointer.next;
      }
      
      pointer = headB;
      while (pointer != null) {
        if (visited.contains(pointer)) {
          return pointer;
        }
        pointer = pointer.next;
      }
      
      return null;
    }
    
    // 2.双指针 m*n 1
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
      ListNode pointerA = headA;
      ListNode pointerB = headB;
      while (pointerA != null) {
        while (pointerB != null) {
          if (pointerA == pointerB) {
            return pointerA;
          }
          pointerB = pointerB.next;
        }
        pointerB = headB;
        pointerA = pointerA.next;
      }
      
      return null;
    }
    
    // 3.双指针 m+n 1
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
      ListNode pointerA = headA;
      ListNode pointerB = headB;
      int sizeA = 1;
      int sizeB = 1;
      while (pointerA.next != null) {
        sizeA++;
        pointerA = pointerA.next;
      }
      while (pointerB.next != null) {
        sizeB++;
        pointerB = pointerB.next;
      }
      
      if (pointerA != pointerB) {
        return null;
      } else {
        pointerA = sizeA < sizeB ? headB : headA;
        pointerB = pointerA == headA ? headB : headA;
        for (int i = 0; i < Math.abs(sizeA - sizeB); i++) {
          pointerA = pointerA.next;
        }
        while (true) {
          if (pointerA == pointerB) {
            return pointerA;
          }
          pointerA = pointerA.next;
          pointerB = pointerB.next;
        }
      }
    }
    
    // 4.高分解：双指针 m+n 1
    // 原理同3. 代码更优雅
    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
      ListNode pointerA = headA;
      ListNode pointerB = headB;
      while (pointerA != pointerB) {
        pointerA = pointerA == null ? headB : pointerA.next;
        pointerB = pointerB == null ? headA : pointerB.next;
      }
      return pointerA;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
  
  class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
      val = x;
      next = null;
    }
  }
}