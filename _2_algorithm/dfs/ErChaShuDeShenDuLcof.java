package org.example.leetcode.problems._2_algorithm.dfs;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

/**
 * 剑指 Offer 55 - I.二叉树的深度 <br>
 * 开题时间：2023-01-31 12:29:05
 */
public class ErChaShuDeShenDuLcof {
  public static void main(String[] args) {
    Solution solution = new ErChaShuDeShenDuLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // ☆☆☆☆☆ dfs（自底向上）
    public int maxDepth9(TreeNode root) {
      return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    int maxDepth = 0;
    
    // dfs（自顶向下）
    public int maxDepth(TreeNode root) {
      dfs(root, 0);
      return maxDepth;
    }
    
    private void dfs(TreeNode root, int depth) {
      if (root == null) {
        maxDepth = Math.max(maxDepth, depth);
        return;
      }
      dfs(root.left, depth + 1);
      dfs(root.right, depth + 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}