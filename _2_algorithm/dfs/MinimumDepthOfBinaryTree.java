package _2_algorithm.dfs;

import _3_common.entity.tree.TreeNode;
import _3_common.tool.Tools;

/**
 * 111.二叉树的最小深度 <br>
 * 开题时间：2023-01-31 12:52:34
 */
public class MinimumDepthOfBinaryTree {
  public static void main(String[] args) {
    Solution solution = new MinimumDepthOfBinaryTree().new Solution();
    System.out.println(solution.minDepth(Tools.buildTree("[2,null,3,null,4,null,5,null,6]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ dfs（自底向上）
     * 注意：这题和「104. 二叉树的最大深度」不同之处在于：
     *    当某个节点只有一侧节点（左或右）时，直接取最小值的话、会导致当前节点的最小深度变为 1，而事实上当前节点并非叶子节点
     */
    public int minDepth9(TreeNode root) {
      return root == null
          ? 0 :
          (root.left == null) == (root.right == null)
              ? Math.min(minDepth9(root.left), minDepth9(root.right)) + 1
              : minDepth9(root.right == null ? root.left : root.right) + 1;
    }
    
    /*
     * dfs（自底向上）（当某个节点只有一侧节点（左或右）时，用加法运算（加零等于没加））
     * 注意：这题和「104. 二叉树的最小深度」不同之处在于：
     *    当某个节点只有一侧节点（左或右）时，直接取最小值的话、会导致当前节点的最小深度变为 1，而事实上当前节点并非叶子节点
     */
    public int minDepth(TreeNode root) {
      return root == null
          ? 0 :
          root.left != null && root.right != null
              ? Math.min(minDepth(root.left), minDepth(root.right)) + 1
              : minDepth(root.left) + minDepth(root.right) + 1;
    }
    
    int minDepth = Integer.MAX_VALUE;
    
    // dfs（自顶向下）
    public int minDepth8(TreeNode root) {
      dfs(root, 1);
      return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }
    
    private void dfs(TreeNode root, int depth) {
      if (root == null) {
        return;
      }
      if (root.left == null && root.right == null) {
        minDepth = Math.min(depth, minDepth);
        return;
      }
      dfs(root.left, depth + 1);
      dfs(root.right, depth + 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}