package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

/**
 * 1008.前序遍历构造二叉搜索树 <br>
 * 开题时间：2023-02-03 14:38:45
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
  public static void main(String[] args) {
    Solution solution = new ConstructBinarySearchTreeFromPreorderTraversal().new Solution();
    System.out.println(solution.bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 递归 + 二分
    public TreeNode bstFromPreorder9(int[] preorder) {
      return bstFromPreorder(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode bstFromPreorder(int[] preorder, int l, int r) {
      if (l > r) {
        return null;
      }
      
      // int mid = l + 1;
      // while (mid <= r && preorder[l] > preorder[mid]) {
      //   mid++;
      // }
      int mid = firstGreaterThan(preorder, l + 1, r + 1, preorder[l]);
      
      return new TreeNode(preorder[l], bstFromPreorder(preorder, l + 1, mid - 1), bstFromPreorder(preorder, mid, r));
    }
    
    // 二分查找左右子树的分界线（右子树的首索引）
    public static int firstGreaterThan(int[] arr, int l, int r, int target) {
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (target <= arr[mid])
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
    
    int idx = 0;
    
    // ☆☆☆☆☆ 递归（根据数值上下界）
    public TreeNode bstFromPreorder(int[] preorder) {
      return helperByBound(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode helperByBound(int[] preorder, int lowerBound, int upperBound) {
      if (idx == preorder.length || preorder[idx] < lowerBound || preorder[idx] > upperBound) {
        return null;
      }
      int val = preorder[idx++];
      return new TreeNode(val,
          helperByBound(preorder, lowerBound, val),
          helperByBound(preorder, val, upperBound)
      );
    }
    
    // todo 使用先序遍历和中序遍历构造二叉树
  }
  // leetcode submit region end(Prohibit modification and deletion)
}