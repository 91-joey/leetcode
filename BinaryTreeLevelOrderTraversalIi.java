import _3_common.entity.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 107.二叉树的层序遍历 II <br>
 * 开题时间：2023-02-21 15:40:34
 */
public class BinaryTreeLevelOrderTraversalIi {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
          level.add(node.val);
          if (node.left != null) {
            q.offer(node.left);
          }
          if (node.right != null) {
            q.offer(node.right);
          }
        }
        ans.push(level);
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}