package _2_algorithm.dfs;

import _3_common.entity.tree.TreeNode;

/**
 * 1028.从先序遍历还原二叉树 <br>
 * 开题时间：2023-02-04 14:25:42
 */
public class RecoverATreeFromPreorderTraversal {
  public static void main(String[] args) {
    Solution solution = new RecoverATreeFromPreorderTraversal().new Solution();
    System.out.println(solution.recoverFromPreorder("1-2--3---4-5--6---7"));
    // System.out.println(solution.recoverFromPreorder("1-2--3--4-5--6--7"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 递归
     * 1. 求根节点值 val 、左子节点的深度 depth、左子节点的开始位置 leftBegin
     * 2. 用左子节点的深度 depth 求出右子节点的开始位置 rightBegin （不存在右子节点时，为 n ）
     * 3. 递归求解左右子树
     */
    public TreeNode recoverFromPreorder(String traversal) {
      int depth = 0;
      int val = 0;
      int leftBegin = 0;
      int n = traversal.length();
      for (; leftBegin < n; leftBegin++) {
        char c = traversal.charAt(leftBegin);
        if (c == '-') {
          depth++;
        } else if (depth == 0) {
          val = val * 10 + (c - '0');
        } else {
          break;
        }
      }
      
      int rightBegin = leftBegin + 1;
      for (int curDepth = 0; rightBegin < n; rightBegin++) {
        char c = traversal.charAt(rightBegin);
        if (c == '-') {
          curDepth++;
        } else if (depth == curDepth) {
          break;
        } else {
          curDepth = 0;
        }
      }
      
      // 右子树不存在时，不需要减去深度
      int leftEnd = rightBegin - (rightBegin == n ? 0 : depth);
      return new TreeNode(val,
          // 边界检查
          leftEnd <= n && leftBegin < leftEnd ? recoverFromPreorder(traversal.substring(leftBegin, leftEnd)) : null,
          rightBegin < n ? recoverFromPreorder(traversal.substring(rightBegin, n)) : null
      );
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}