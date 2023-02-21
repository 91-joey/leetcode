package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 32 - III.从上到下打印二叉树 III <br>
 * 开题时间：2023-02-21 17:13:18
 */
public class CongShangDaoXiaDaYinErChaShuIiiLcof {
  public static void main(String[] args) {
    Solution solution = new CongShangDaoXiaDaYinErChaShuIiiLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 层序遍历 + 双端队列
    public List<List<Integer>> levelOrder9(TreeNode root) {
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
          
          if (ans.size() % 2 == 0) {
            level.offer(node.val);
          } else {
            level.push(node.val);
          }
          
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
    
    // ☆☆☆☆☆ 层序遍历 + 双端队列（优化：奇偶分列）
    public List<List<Integer>> levelOrder8(TreeNode root) {
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
    
    // 层序遍历 + 队列 + 偶数层反转
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
        
        if (ans.size() % 2 == 1) {
          Collections.reverse(level);
        }
        
        ans.offer(level);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}