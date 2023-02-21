package org.example.leetcode.problems._2_algorithm.dfs;

import org.example.leetcode.problems._3_common.entity.tree.nAryTree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 590.N 叉树的后序遍历 <br>
 * 开题时间：2023-02-04 17:43:49
 */
public class NAryTreePostorderTraversal {
  public static void main(String[] args) {
    Solution solution = new NAryTreePostorderTraversal().new Solution();
    System.out.println(solution);
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  class Solution {
    List<Integer> list = new ArrayList<Integer>();
    
    // Recursive
    public List<Integer> postorder(Node root) {
      dfs(root);
      return list;
    }
    
    private void dfs(Node root) {
      if (root == null)
        return;
      
      if (root.children != null)
        root.children.forEach(this::dfs);
      list.add(root.val);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}