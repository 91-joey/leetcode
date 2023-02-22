//<p>给定一个&nbsp;<strong>完美二叉树&nbsp;</strong>，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：</p>
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
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/14/116_sample.png" style="height: 171px; width: 500px;" /></p>
//
//<pre>
//<b>输入：</b>root = [1,2,3,4,5,6,7]
//<b>输出：</b>[1,#,2,3,#,4,5,6,7,#]
//<b>解释：</b>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
//</pre>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<b>输入：</b>root = []
//<b>输出：</b>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点的数量在
//  <meta charset="UTF-8" />&nbsp;<code>[0, 2<sup>12</sup>&nbsp;- 1]</code>&nbsp;范围内</li> 
// <li><code>-1000 &lt;= node.val &lt;= 1000</code></li> 
//</ul>
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
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>链表</li><li>二叉树</li></div></div><br><div><li>👍 869</li><li>👎 0</li></div>
package _1_dataStructure.tree;

import _3_common.entity.tree.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 116.填充每个节点的下一个右侧节点指针
// 开题时间：2022-09-16 10:50:23
public class PopulatingNextRightPointersInEachNode {
  public static void main(String[] args) {
    Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();
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
    node3.left = node6;
    node3.right = node7;
    System.out.println(solution.connect2(node1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ BFS（层序遍历） + 迭代    n   1
     * 按父节点是否相同，分为 2 类：
     *  父节点相同，直接借助父节点即可，    node.left.next = node.right
     *  父节点不同，需要借助父节点的后节点，node.right.next = node.next == null ? null : node.next.left
     *
     * 具体的：
     *  1.枚举二叉树的最左侧节点
     *  2.从每个当前的最左侧节点开始层序遍历（通过 next 指针，类似链表）每个节点
     *  3.对于每个节点：
     *      - 串联左子树和右子树
     *      - 串联右子树和后节点的左子树
     *
     * 由于是层序遍历，可以保证遍历到当前层级时，上层均已串联
     */
    public Node connectGJ2(Node root) {
      if (root == null) return null;
      Node leftMost = root;
      
      while (leftMost.left != null) {
        for (Node node = leftMost; node != null; node = node.next) {
          node.left.next = node.right;
          node.right.next = node.next == null ? null : node.next.left;
        }
        leftMost = leftMost.left;
      }
      
      return root;
    }
    
    // 自解: BFS层序遍历+queue n   n
    public Node connect(Node root) {
      if (root == null) return null;
      Queue<Node> queue = new LinkedList<>();
      queue.offer(root);
      
      for (int size = 1; !queue.isEmpty(); size *= 2) {
        getLeftNode(queue, size);
      }
      
      return root;
    }
    
    private Node getLeftNode(Queue<Node> queue, int idx) {
      if (idx > 0) {
        Node poll = queue.poll();
        Node l = poll.left;
        Node r = poll.right;
        if (l != null) {
          queue.offer(l);
          queue.offer(r);
          l.next = r;
          r.next = getLeftNode(queue, --idx);
          return l;
        }
      }
      return null;
    }
    
    // DFS+递归    n   logn
    public Node connect2(Node root) {
      if (root == null) return null;
      
      if (root.right != null) {
        root.left.next = root.right;
        root.right.next = root.next == null ? null : root.next.left;
        connect2(root.left);
        connect2(root.right);
      }
      
      return root;
    }
    
    /*
     * 递归2
     * 我们以当前节 root 点为起始，左右节点不断的深入下面：
     *  left 节点不断往右走
     *  right 节点不断往左走
     * 当这两个节点走到底后，整个纵深这段就完成了串联。
     */
    public Node connect7(Node root) {
      if (root == null)
        return null;
      
      Node l = root.left;
      Node r = root.right;
      while (l != null && r != null) {
        l.next = r;
        l = l.right;
        r = r.left;
      }
      
      connect(root.left);
      connect(root.right);
      
      return root;
    }
    
    // 栈 n   logn
    public Node connect3(Node root) {
      if (root == null) return null;
      Deque<Node> stack = new LinkedList<>();
      stack.push(root);
      while (!stack.isEmpty()) {
        Node pop = stack.pop();
        if (pop.right != null) {
          pop.left.next = pop.right;
          pop.right.next = pop.next == null ? null : pop.next.left;
          stack.push(pop.right);
          stack.push(pop.left);
        }
      }
      return root;
    }
    
    //（官解优化）BFS层序遍历+queue   n   n
    public Node connectGJ(Node root) {
      if (root == null) return null;
      Queue<Node> queue = new LinkedList<>();
      queue.offer(root);
      
      for (int size = 1; !queue.isEmpty(); size *= 2) {
        for (int i = 0; i < size; i++) {
          Node poll = queue.poll();
          if (i < size - 1)
            poll.next = queue.peek();
          Node l = poll.left;
          if (l != null) {
            queue.offer(l);
            queue.offer(poll.right);
          }
        }
      }
      
      return root;
    }
    
    //（自解）BFS层序遍历 + queue   n   n
    public Node connect9(Node root) {
      if (root == null)
        return null;
      
      Queue<Node> q = new LinkedList<>();
      q.offer(root);
      while (!q.isEmpty()) {
        Node pre = new Node();
        for (int i = q.size(); i > 0; i--) {
          pre.next = q.poll();
          pre = pre.next;
          if (pre.left != null) {
            q.offer(pre.left);
            q.offer(pre.right);
          }
        }
      }
      return root;
    }
    
    // 未知代码
    public Node connect4(Node root) {
      if (root == null) return null;
      Deque<Node> stack = new LinkedList<>();
      for (Node node = root; node.right != null; node = node.right) {
        stack.push(node);
      }
      while (!stack.isEmpty()) {
        Node pop = stack.pop();
        if (pop.left != null) {
          pop.left.next = pop.right;
          pop.right.next = pop.next == null ? null : pop.next.left;
          leftTree(pop.left);
        }
      }
      return root;
    }
    
    private void leftTree(Node root) {
      if (root.left != null) {
        root.left.next = root.right;
        root.right.next = root.next == null ? null : root.next.left;
        leftTree(root.left);
        leftTree(root.right);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}