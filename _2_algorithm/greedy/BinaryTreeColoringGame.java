package org.example.leetcode.problems._2_algorithm.greedy;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

/**
 * 1145.二叉树着色游戏 <br>
 * 开题时间：2023-02-03 10:03:44
 */
public class BinaryTreeColoringGame {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeColoringGame().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
      TreeNode X = findX(root, x);
      
      int cntLeft = cntNodes(X.left);
      int cntRight = cntNodes(X.right);
      int maxCnt = Math.max(n - cntLeft - cntRight - 1, Math.max(cntLeft, cntRight));
      
      return maxCnt > n - maxCnt;
    }
    
    private int cntNodes(TreeNode root) {
      if (root == null)
        return 0;
      return 1 + cntNodes(root.left) + cntNodes(root.right);
    }
    
    private TreeNode findX(TreeNode root, int x) {
      if (root == null) {
        return null;
      }
      if (root.val == x) {
        return root;
      }
      
      TreeNode l = findX(root.left, x);
      if (l != null) {
        return l;
      }
      TreeNode r = findX(root.right, x);
      if (r != null) {
        return r;
      }
      
      return null;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}