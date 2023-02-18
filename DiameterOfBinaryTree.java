package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 543.二叉树的直径 <br>
 * 开题时间：2023-02-18 12:23:21
 */
public class DiameterOfBinaryTree {
  public static void main(String[] args) {
    Solution solution = new DiameterOfBinaryTree().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    List<List<Integer>> graph = new ArrayList<List<Integer>>();
    int ans = 0;
    
    /*
     * ☆☆☆☆☆ 树形dp
     * 任意一条路径都可以看成是某一个节点的「左子树的路径」「右子树的路径」拼接在一起，某一子树的路径可能为空
     * 不用考虑其余部分的路径（上溯父节点），因为这种情况也可以作为「某个节点的左右子树路径和」考虑
     */
    public int diameterOfBinaryTree9(TreeNode root) {
      buildGraph(root, 0, -1);
      
      dfs(0, -1);
      
      return ans;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
      dfs(root);
      return ans;
    }
    
    private int dfs(TreeNode root) {
      if (root == null) {
        return 0;
      }
      int l = dfs(root.left);
      int r = dfs(root.right);
      ans = Math.max(ans, l + r);
      return Math.max(l, r) + 1;
    }
    
    // 建图 + 树形dp
    private void buildGraph(TreeNode root, int v, int u) {
      graph.add(new ArrayList<Integer>());
      if (u != -1) {
        graph.get(u).add(v);
        graph.get(v).add(u);
      }
      if (root.left != null) {
        buildGraph(root.left, graph.size(), v);
      }
      if (root.right != null) {
        buildGraph(root.right, graph.size(), v);
      }
    }
    
    /**
     * 求以 u 为根节点、以 fa 为父节点（不能返回父节点）的最大路径长度
     *
     * @param u  根节点
     * @param fa 父节点
     */
    private int dfs(int u, int fa) {
      int max1 = 0; // 最大值
      int max2 = 0; // 次大值
      
      for (Integer v : graph.get(u)) {
        if (v == fa) {
          continue;
        }
        int depth = dfs(v, u);
        if (depth > max1) {
          max2 = max1;
          max1 = depth;
        } else if (depth > max2) {
          max2 = depth;
        }
      }
      // 经过 u 的最长链的长度 = 以 u 为根节点的最大路径长度 +　以 u 为根节点的次大路径长度
      ans = Math.max(ans, max1 + max2);
      
      return max1 + 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}