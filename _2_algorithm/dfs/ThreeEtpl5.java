package _2_algorithm.dfs;

import _3_common.entity.tree.TreeNode;

/**
 * 剑指 Offer II 049.从根节点到叶节点的路径数字之和 <br>
 * 开题时间：2023-02-02 11:42:19
 */
public class ThreeEtpl5 {
  public static void main(String[] args) {
    Solution solution = new ThreeEtpl5().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int sum = 0;
    
    // 无返回值 dfs
    public int sumNumbers9(TreeNode root) {
      dfs9(root, root.val);
      
      return sum;
    }
    
    private void dfs9(TreeNode root, int path) {
      if (root.left == null && root.right == null) {
        sum += path;
        return;
      }
      
      for (TreeNode node : new TreeNode[]{root.left, root.right}) {
        if (node != null) {
          dfs9(node, path * 10 + node.val);
        }
      }
    }
    
    
    // 有返回值 dfs
    public int sumNumbers(TreeNode root) {
      return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int prevSum) {
      if (root == null) {
        return 0;
      }
      
      prevSum = prevSum * 10 + root.val;
      if (root.left == null && root.right == null) {
        return prevSum;
      }
      
      return dfs(root.left, prevSum)
          + dfs(root.right, prevSum);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}