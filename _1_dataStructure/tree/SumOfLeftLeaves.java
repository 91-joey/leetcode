//<p>给定二叉树的根节点&nbsp;<code>root</code>&nbsp;，返回所有左叶子之和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/04/08/leftsum-tree.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> root = [3,9,20,null,null,15,7] 
//<strong>输出:</strong> 24 
//<strong>解释:</strong> 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> root = [1]
//<strong>输出:</strong> 0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li>节点数在&nbsp;<code>[1, 1000]</code>&nbsp;范围内</li> 
// <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><li>👍 527</li><li>👎 0</li></div>
package _1_dataStructure.tree;

import _3_common.entity.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// 404.左叶子之和
// 开题时间：2022-12-10 09:53:22
public class SumOfLeftLeaves {
  public static void main(String[] args) {
    Solution solution = new SumOfLeftLeaves().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int sum = 0;
    
    // 递归（复杂版）
    public int sumOfLeftLeaves9(TreeNode root) {
      helper(null, root, false);
      return sum;
    }
    
    private void helper(TreeNode root, TreeNode cur, boolean left) {
      if (cur == null)
        return;
      if (root != null && left && isLeafNode(cur))
        sum += cur.val;
      
      helper(cur, cur.left, true);
      helper(cur, cur.right, false);
    }
    
    // BFS（复杂版）
    public int sumOfLeftLeaves8(TreeNode root) {
      int sum = 0;
      if (root == null)
        return sum;
      
      Queue<TreeNode> qL = new LinkedList<>();
      Queue<TreeNode> qR = new LinkedList<>();
      qL.offer(root.left);
      qR.offer(root.right);
      
      while (!qL.isEmpty() || !qR.isEmpty()) {
        while (!qL.isEmpty()) {
          TreeNode poll = qL.poll();
          if (poll != null) {
            if (isLeafNode(poll))
              sum += poll.val;
            qL.offer(poll.left);
            qR.offer(poll.right);
          }
        }
        while (!qR.isEmpty()) {
          TreeNode poll = qR.poll();
          if (poll != null) {
            qL.offer(poll.left);
            qR.offer(poll.right);
          }
        }
      }
      
      return sum;
    }
    
    public static boolean isLeafNode(TreeNode root) {
      return root.left == null && root.right == null;
    }
    
    //☆☆☆☆☆ 递归（精简版）
    public int sumOfLeftLeaves7(TreeNode root) {
      if (root == null)
        return 0;
      
      return sumOfLeftLeaves(root.left) +
          sumOfLeftLeaves(root.right) +
          (root.left != null && isLeafNode(root.left) ? root.left.val : 0);
    }
    
    
    // BFS（精简版）
    public int sumOfLeftLeaves(TreeNode root) {
      int sum = 0;
      
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      
      while (!q.isEmpty()) {
        TreeNode poll = q.poll();
        if (poll == null)
          continue;
        
        if (poll.left != null) {
          if (isLeafNode(poll.left))
            sum += poll.left.val;
          q.offer(poll.left);
        }
        q.offer(poll.right);
      }
      
      return sum;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}