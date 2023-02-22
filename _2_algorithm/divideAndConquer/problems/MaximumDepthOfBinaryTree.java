//<p>给定一个二叉树，找出其最大深度。</p>
//
//<p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>
//
//<p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>
//
//<p><strong>示例：</strong><br> 给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</br></p>
//
//<pre>    3
//   / \
//  9  20
//    /  \
//   15   7</pre>
//
//<p>返回它的最大深度&nbsp;3 。</p>
//
//<div><li>👍 1428</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import _3_common.entity.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// 104.二叉树的最大深度
// 开题时间：2022-11-18 12:10:17
public class MaximumDepthOfBinaryTree {
  public static void main(String[] args) {
    Solution solution = new MaximumDepthOfBinaryTree().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // DFS + recursion
    public int maxDepth9(TreeNode root) {
      if (root == null)
        return 0;
      return Math.max(maxDepth9(root.left), maxDepth9(root.right)) + 1;
    }
    
    // BFS
    public int maxDepth(TreeNode root) {
      if (root == null)
        return 0;
      
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      
      int depth = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          TreeNode poll = q.poll();
          if (poll.left != null) q.offer(poll.left);
          if (poll.right != null) q.offer(poll.right);
        }
        depth++;
      }
      return depth;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}