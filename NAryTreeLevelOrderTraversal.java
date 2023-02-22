import _3_common.entity.tree.nAryTree.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * 429.N 叉树的层序遍历 <br>
 * 开题时间：2023-02-21 17:45:46
 */
public class NAryTreeLevelOrderTraversal {
  public static void main(String[] args) {
    Solution solution = new NAryTreeLevelOrderTraversal().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> levelOrder(Node root) {
      LinkedList<List<Integer>> ans = new LinkedList<>();
      if (root == null) {
        return ans;
      }
      
      LinkedList<Node> q = new LinkedList<>();
      q.offer(root);
      while (!q.isEmpty()) {
        LinkedList<Integer> level = new LinkedList<>();
        
        for (int i = q.size(); i > 0; i--) {
          Node node = q.poll();
          
          level.offer(node.val);
          
          for (Node child : node.children) {
            q.offer(child);
          }
        }
        
        ans.offer(level);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}