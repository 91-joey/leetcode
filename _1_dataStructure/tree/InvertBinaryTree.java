//<p>给你一棵二叉树的根节点 <code>root</code> ，翻转这棵二叉树，并返回其根节点。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert1-tree.jpg" style="height: 165px; width: 500px;" /></p>
//
//<pre>
//<strong>输入：</strong>root = [4,2,7,1,3,6,9]
//<strong>输出：</strong>[4,7,2,9,6,3,1]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/14/invert2-tree.jpg" style="width: 500px; height: 120px;" /></p>
//
//<pre>
//<strong>输入：</strong>root = [2,1,3]
//<strong>输出：</strong>[2,3,1]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目范围在 <code>[0, 100]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 1454</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// 226.翻转二叉树
// 开题时间：2022-12-09 11:44:14
public class InvertBinaryTree {
  public static void main(String[] args) {
    Solution solution = new InvertBinaryTree().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // recursive + DFS
    public TreeNode invertTree(TreeNode root) {
      if (root == null) {
        return null;
      }
      TreeNode tmp = invertTree(root.left);
      root.left = invertTree(root.right);
      root.right = tmp;
      return root;
    }
    
    // recursive + DFS （偏向尾递归）
    public TreeNode invertTree9(TreeNode root) {
      if (root == null)
        return null;
      
      TreeNode left = root.left;
      root.left = root.right;
      root.right = left;
      
      invertTree(root.left);
      invertTree(root.right);
      
      return root;
    }
    
    // BFS + queue
    public TreeNode invertTree8(TreeNode root) {
      if (root == null)
        return null;
      
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      
      while (!q.isEmpty()) {
        TreeNode poll = q.poll();
        
        TreeNode left = poll.left;
        poll.left = poll.right;
        poll.right = left;
        
        if (poll.left != null) q.offer(poll.left);
        if (poll.right != null) q.offer(poll.right);
      }
      
      return root;
    }
    
    // BFS + queue（简版）
    public TreeNode invertTree7(TreeNode root) {
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      
      while (!q.isEmpty()) {
        TreeNode poll = q.poll();
        if (poll == null)
          continue;
        
        TreeNode left = poll.left;
        poll.left = poll.right;
        poll.right = left;
        
        q.offer(poll.left);
        q.offer(poll.right);
      }
      
      return root;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}