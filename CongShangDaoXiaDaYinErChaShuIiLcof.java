import _3_common.entity.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 32 - II.从上到下打印二叉树 II <br>
 * 开题时间：2023-02-21 17:36:33
 */
public class CongShangDaoXiaDaYinErChaShuIiLcof {
  public static void main(String[] args) {
    Solution solution = new CongShangDaoXiaDaYinErChaShuIiLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
      LinkedList<List<Integer>> ans = new LinkedList<>();
      if (root == null) {
        return ans;
      }
      
      LinkedList<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while (!q.isEmpty()) {
        LinkedList<Integer> level = new LinkedList<>();
        for (int i = q.size(); i > 0; i--) {
          TreeNode node = q.poll();
          
          level.offer(node.val);
          
          if (node.left != null) {
            q.offer(node.left);
          }
          if (node.right != null) {
            q.offer(node.right);
          }
        }
        ans.offer(level);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}