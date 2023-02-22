package _2_algorithm.dfs;

import _3_common.entity.tree.TreeNode;

/**
 * 100.相同的树 <br>
 * 开题时间：2023-01-31 17:30:31
 */
public class SameTree {
  public static void main(String[] args) {
    Solution solution = new SameTree().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // dfs
    public boolean isSameTree9(TreeNode p, TreeNode q) {
      if (p == null || q == null) {
        return p == null && q == null;
      }
      return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    // dfs（再调优）
    public boolean isSameTree(TreeNode p, TreeNode q) {
      if (p == null) {
        return q == null;
      }
      return q != null && p.val == q.val
          && isSameTree(p.left, q.left)
          && isSameTree(p.right, q.right);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}