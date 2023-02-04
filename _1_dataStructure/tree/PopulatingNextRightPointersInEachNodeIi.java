//<p>给定一个二叉树</p>
//
//<pre>
// struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}</pre>
//
//<p>填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 <code>NULL</code>。</p>
//
//<p>初始状态下，所有&nbsp;next 指针都被设置为 <code>NULL</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>你只能使用常量级额外空间。</li> 
// <li>使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/15/117_sample.png" style="height: 218px; width: 640px;" /></p>
//
//<pre>
//<strong>输入</strong>：root = [1,2,3,4,5,null,7]
//<strong>输出：</strong>[1,#,2,3,#,4,5,7,#]
//<strong>解释：</strong>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中的节点数小于 <code>6000</code></li> 
// <li><code>-100&nbsp;&lt;= node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<ul> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>链表</li><li>二叉树</li></div></div><br><div><li>👍 631</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

// 117.填充每个节点的下一个右侧节点指针 II
// 开题时间：2022-09-16 17:39:20
public class PopulatingNextRightPointersInEachNodeIi {
  public static void main(String[] args) {
    Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.right = node7;
    System.out.println(solution.connect2(node1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // BFS+queue n   breadth(n)
    public Node connect(Node root) {
      if (root == null) return null;
      Queue<Node> queue = new LinkedList<>();
      queue.offer(root);
      
      while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
          Node poll = queue.poll();
          if (i < size - 1)
            poll.next = queue.peek();
          if (poll.left != null)
            queue.offer(poll.left);
          if (poll.right != null)
            queue.offer(poll.right);
        }
      }
      
      return root;
    }
    
    // BFS（递归）
    public Node connect2(Node root) {
      if (root == null) return null;
      
      Node start = null;
      for (Node tierFirst = root; tierFirst != null; ) {
        if (tierFirst.left != null) {
          helper(tierFirst, true);
          tierFirst = tierFirst.left;
        } else if (tierFirst.right != null) {
          helper(tierFirst, false);
          tierFirst = tierFirst.right;
        } else {
          tierFirst = tierFirst.next;
        }
      }
      
      return root;
    }
    
    private void helper(Node node, boolean leftOrRight) {
      Node start = leftOrRight ? node.left : node.right;
      Node end = null;
      if (leftOrRight) {
        if (node.right != null) {
          start.next = node.right;
        }
      }
      if (start.next == null) {
        for (Node tmp = node.next; tmp != null; tmp = tmp.next) {
          if (tmp.left != null) {
            start.next = tmp.left;
            helper(tmp, true);
            break;
          } else if (tmp.right != null) {
            start.next = tmp.right;
            helper(tmp, false);
            break;
          }
        }
      } else {
        helper(node, false);
      }
    }
    
    
    //☆☆☆☆☆链表法（哑结点！！）
    public Node connect3(Node root) {
      if (root == null) return null;
      // 引入哑结点，不用算计每一层的头结点
      Node dummy = new Node();
      Node cur = root;
      while (cur != null) {
        Node pre = dummy;
        while (cur != null) {
          if (cur.left != null) {
            pre.next = cur.left;
            pre = pre.next;
          }
          if (cur.right != null) {
            pre.next = cur.right;
            pre = pre.next;
          }
          cur = cur.next;
        }
        cur = dummy.next;
        dummy.next = null;
      }
      
      return root;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}
