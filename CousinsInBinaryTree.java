package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.Pair;
import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.LinkedList;

/**
 * 993.二叉树的堂兄弟节点 <br>
 * 开题时间：2023-02-21 17:51:18
 */
public class CousinsInBinaryTree {
  public static void main(String[] args) {
    Solution solution = new CousinsInBinaryTree().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // 通用思路：两节点为堂兄弟节点的充要条件为：深度相同、父节点不同
  class Solution {
    // bfs
    public boolean isCousins9(TreeNode root, int x, int y) {
      LinkedList<TreeNode> q = new LinkedList<>();
      q.add(root);
      while (!q.isEmpty()) {
        int size = q.size();
        int faX = -1; // x 节点的父节点
        int faY = -2; // y 节点的父节点
        for (int i = 0; i < size; i++) {
          TreeNode node = q.poll();
          if (node.left != null) {
            q.offer(node.left);
            
            if (node.left.val == x) {
              faX = i;
            } else if (node.left.val == y) {
              faY = i;
            }
          }
          if (node.right != null) {
            q.offer(node.right);
            
            if (node.right.val == x) {
              faX = i;
            } else if (node.right.val == y) {
              faY = i;
            }
          }
        }
        
        if (faX >= 0 || faY >= 0) {
          return faX >= 0 && faY >= 0 && faX != faY;
        }
      }
      
      return false;
    }
    
    // bfs（以 Pair 类记录父节点）
    public boolean isCousins8(TreeNode root, int x, int y) {
      LinkedList<Pair<TreeNode, Integer>> q = new LinkedList<>();
      q.add(new Pair<>(root, -1));
      while (!q.isEmpty()) {
        int size = q.size();
        int rootX = -1;
        int rootY = -2;
        for (int i = 0; i < size; i++) {
          Pair<TreeNode, Integer> poll = q.poll();
          TreeNode node = poll.getKey();
          Integer parent = poll.getValue();
          
          if (node.val == x) {
            rootX = parent;
          } else if (node.val == y) {
            rootY = parent;
          }
          
          if (node.left != null) {
            q.offer(new Pair<>(node.left, node.val));
          }
          if (node.right != null) {
            q.offer(new Pair<>(node.right, node.val));
          }
        }
        
        if (rootX >= 0 || rootY >= 0) {
          return rootX >= 0 && rootY >= 0 && rootX != rootY;
        }
      }
      
      return false;
    }
    
    int depthX;
    int depthY;
    int faX;
    int faY;
    boolean foundX;
    boolean foundY;
    
    // ☆☆☆☆☆ dfs
    public boolean isCousins(TreeNode root, int x, int y) {
      dfs(root, -1, 0, x, y);
      
      return depthX == depthY && faX != faY;
    }
    
    private void dfs(TreeNode root, int fa, int depth, int x, int y) {
      if (root == null || (foundX && foundY)) {
        return;
      }
      
      if (root.val == x) {
        depthX = depth;
        faX = fa;
        foundX = true;
      } else if (root.val == y) {
        depthY = depth;
        faY = fa;
        foundY = true;
      }
      
      dfs(root.left, root.val, depth + 1, x, y);
      dfs(root.right, root.val, depth + 1, x, y);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}