package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.nAryTree.Node;

/**
 * 559.N 叉树的最大深度 <br>
 * 开题时间：2023-02-17 12:21:54
 */
public class MaximumDepthOfNAryTree {
  public static void main(String[] args) {
    Solution solution = new MaximumDepthOfNAryTree().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // ☆☆☆☆☆ dfs(后序遍历（自底向上）)
    public int maxDepth9(Node root) {
      if (root == null) {
        return 0;
      }
      
      int depth = 0;
      for (Node child : root.children) {
        depth = Math.max(depth, maxDepth(child));
      }
      return depth + 1;
    }
    
    int depth = 0;
  
    // dfs(自顶向下)
    public int maxDepth(Node root) {
      if (root == null) {
        return 0;
      }
      
      dfs(root, 1);
      return depth;
    }
    
    private void dfs(Node root, int depth) {
      if (root.children == null || root.children.isEmpty()) {
        this.depth = Math.max(this.depth, depth);
        return;
      }
      
      for (Node child : root.children) {
        dfs(child, depth + 1);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}