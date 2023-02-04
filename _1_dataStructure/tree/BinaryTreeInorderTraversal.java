//<p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>&nbsp;遍历</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="height: 200px; width: 125px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,null,2,3]
//<strong>输出：</strong>[1,3,2]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目在范围 <code>[0, 100]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>
//
//<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1556</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// 94.二叉树的中序遍历
// 开题时间：2022-09-13 13:53:20
public class BinaryTreeInorderTraversal {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeInorderTraversal().new Solution();
    TreeNode treeNode1 = new TreeNode(1);
    TreeNode treeNode2 = new TreeNode(2);
    TreeNode treeNode3 = new TreeNode(3);
    treeNode1.right = treeNode2;
    treeNode2.left = treeNode3;
    System.out.println(solution.inorderTraversal_morris(treeNode1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // DFS+递归
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      
      inorderTraversal(root, list);
      
      return list;
    }
    
    public void inorderTraversal(TreeNode root, Collection<Integer> coll) {
      if (root == null)
        return;
      
      inorderTraversal(root.left, coll);
      coll.add(root.val);
      inorderTraversal(root.right, coll);
    }
    
    // DFS+栈+哈希集和    n   n
    public List<Integer> inorderTraversal_stack_set(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null)
        return list;
      
      Deque<TreeNode> stack = new LinkedList<>();
      Set<TreeNode> set = new HashSet<>();
      stack.push(root);
      while (!stack.isEmpty()) {
        TreeNode peek = stack.peek();
        while (peek.left != null && !set.contains(peek.left)) {
          peek = peek.left;
          stack.push(peek);
        }
        stack.pop();
        list.add(peek.val);
        set.add(peek);
        if (peek.right != null) {
          stack.push(peek.right);
        }
      }
      
      return list;
    }
    
    // DFS+栈    n   n
    public List<Integer> inorderTraversal_stack(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null)
        return list;
      
      Deque<TreeNode> stack = new LinkedList<>();
      while (root != null || !stack.isEmpty()) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        list.add(root.val);
        root = root.right;
      }
      
      return list;
    }
    
    // Morris    n   1
    public List<Integer> inorderTraversal_morris(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null)
        return list;
      
      TreeNode cur = root;
      TreeNode left;
      while (cur != null) {
        left = cur.left;
        if (left != null) {
          while (left.right != null && left.right != cur) {
            left = left.right;
          }
          if (left.right == null) {
            left.right = cur;
            cur = cur.left;
          } else {
            list.add(cur.val);
            left.right = null;
            cur = cur.right;
          }
        } else {
          list.add(cur.val);
          cur = cur.right;
        }
      }
      
      return list;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}