package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 68 - II.二叉树的最近公共祖先 <br>
 * 开题时间：2023-02-02 13:35:14
 */
public class ErChaShuDeZuiJinGongGongZuXianLcof {
  public static void main(String[] args) {
    Solution solution = new ErChaShuDeZuiJinGongGongZuXianLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    Deque<TreeNode> pathP;
    Deque<TreeNode> pathQ;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      getPaths(root, p, q, new LinkedList<TreeNode>());
      
      TreeNode ans = root;
      while (pathP.peek() == pathQ.peek()) {
        ans = pathP.pop();
        pathQ.pop();
      }
      
      return ans;
    }
    
    private void getPaths(TreeNode root, TreeNode p, TreeNode q, Deque<TreeNode> path) {
      path.addLast(root);
      if (root == p) {
        pathP = new LinkedList<>(path);
      } else if (root == q) {
        pathQ = new LinkedList<>(path);
      }
      
      if (root.left != null) {
        getPaths(root.left, p, q, path);
        path.removeLast();
      }
      if (root.right != null) {
        getPaths(root.right, p, q, path);
        path.removeLast();
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}