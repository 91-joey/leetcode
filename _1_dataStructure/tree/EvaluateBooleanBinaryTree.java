package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

/**
 * 2331. Evaluate Boolean Binary Tree
 * 开题时间：2023-02-06 08:40:31
 */
public class EvaluateBooleanBinaryTree {
  public static void main(String[] args) {
    Solution solution = new EvaluateBooleanBinaryTree().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean evaluateTree(TreeNode root) {
      if (root.left == null) {
        return root.val == 1;
      }
      return root.val == 2 ?
          evaluateTree(root.left) || evaluateTree(root.right)
          : evaluateTree(root.left) && evaluateTree(root.right);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}