package _2_algorithm.bfs;

import _3_common.entity.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 103.二叉树的锯齿形层序遍历 <br>
 * 开题时间：2023-02-21 17:40:35
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      LinkedList<List<Integer>> ans = new LinkedList<>();
      if (root == null) {
        return ans;
      }
      
      LinkedList<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while (!q.isEmpty()) {
        // region 奇数层
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
        // endregion
        
        if (q.isEmpty()) {
          break;
        }
        
        // region 偶数层
        level = new LinkedList<>();
        for (int i = q.size(); i > 0; i--) {
          TreeNode node = q.poll();
          
          level.push(node.val);
          
          if (node.left != null) {
            q.offer(node.left);
          }
          if (node.right != null) {
            q.offer(node.right);
          }
        }
        ans.offer(level);
        // endregion
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}