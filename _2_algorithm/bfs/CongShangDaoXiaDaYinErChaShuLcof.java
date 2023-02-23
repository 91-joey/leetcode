package _2_algorithm.bfs;

import _3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 剑指 Offer 32 - I.从上到下打印二叉树 <br>
 * 开题时间：2023-02-21 17:05:39
 */
public class CongShangDaoXiaDaYinErChaShuLcof {
  public static void main(String[] args) {
    Solution solution = new CongShangDaoXiaDaYinErChaShuLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] levelOrder(TreeNode root) {
      if (root == null) {
        return new int[0];
      }
      
      ArrayList<Integer> list = new ArrayList<>();
      LinkedList<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while (!q.isEmpty()) {
        TreeNode node = q.poll();
        list.add(node.val);
        if (node.left != null) {
          q.offer(node.left);
        }
        if (node.right != null) {
          q.offer(node.right);
        }
      }
      
      return list.stream().mapToInt(Integer::intValue).toArray();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}