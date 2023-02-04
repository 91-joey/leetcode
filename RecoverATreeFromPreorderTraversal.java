package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

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
      
      int leftEnd = rightBegin - (rightBegin == n ? 0 : depth);
      return new TreeNode(val,
          leftEnd <= n && leftBegin < leftEnd ? recoverFromPreorder(traversal.substring(leftBegin, leftEnd)) : null,
          rightBegin < n ? recoverFromPreorder(traversal.substring(rightBegin, n)) : null
      );
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}