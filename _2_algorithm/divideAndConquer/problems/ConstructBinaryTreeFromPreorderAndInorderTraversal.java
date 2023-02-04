//<p>给定两个整数数组&nbsp;<code>preorder</code> 和 <code>inorder</code>&nbsp;，其中&nbsp;<code>preorder</code> 是二叉树的<strong>先序遍历</strong>， <code>inorder</code>&nbsp;是同一棵树的<strong>中序遍历</strong>，请构造二叉树并返回其根节点。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" style="height: 302px; width: 277px;" /> 
//<pre>
//<strong>输入</strong><strong>:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//<strong>输出:</strong> [3,9,20,null,null,15,7]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> preorder = [-1], inorder = [-1]
//<strong>输出:</strong> [-1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= preorder.length &lt;= 3000</code></li> 
// <li><code>inorder.length == preorder.length</code></li> 
// <li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li> 
// <li><code>preorder</code>&nbsp;和&nbsp;<code>inorder</code>&nbsp;均 <strong>无重复</strong> 元素</li> 
// <li><code>inorder</code>&nbsp;均出现在&nbsp;<code>preorder</code></li> 
// <li><code>preorder</code>&nbsp;<strong>保证</strong> 为二叉树的前序遍历序列</li> 
// <li><code>inorder</code>&nbsp;<strong>保证</strong> 为二叉树的中序遍历序列</li> 
//</ul>
//
//<div><li>👍 1795</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

// 105.从前序与中序遍历序列构造二叉树
// 开题时间：2022-11-16 14:35:15
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public static void main(String[] args) {
    Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
    //        System.out.println(solution.buildTree(new int[]{1, 2}, new int[]{2, 1}));
    System.out.println(solution.buildTree(new int[]{3, 2, 1, 4}, new int[]{1, 2, 3, 4}));
    //        System.out.println(solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    Map<Integer, Integer> val2idxPre = new HashMap<>();
    Map<Integer, Integer> val2idxIn = new HashMap<>();
    
    public TreeNode buildTreeX(int[] preorder, int[] inorder) {
      for (int i = 0; i < preorder.length; i++)
        val2idxPre.put(preorder[i], i);
      for (int i = 0; i < inorder.length; i++)
        val2idxIn.put(inorder[i], i);
      
      return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
      if (l1 > r1 || l2 > r2)
        return null;
      
      TreeNode head = new TreeNode(preorder[l1]);
      int leftMostIn = val2idxIn.get(preorder[l1]) - 1;
      // 前序、中序遍历的左子节点序列的最后一个值并不相同（考虑左子节点没有右子节点的情况，此时前序遍历最后一个值为左下角的值，中序遍历为根节点的左子节点）
      int leftMostPre = leftMostIn < l2 ? l1 : val2idxPre.get(inorder[leftMostIn]);
      //            if (leftMostIn >= l2 && leftMostIn <= r2)
      head.left = buildTree(preorder, l1 + 1, leftMostPre, inorder, l2, leftMostIn);
      //            if (leftMostPre >= l1 - 1 && leftMostPre < r1)
      head.right = buildTree(preorder, leftMostPre + 1, r1, inorder, leftMostIn + 2, r2);
      
      return head;
    }
    
    // 递归 + 哈希表 + 遍历寻找前序遍历中右子树的起点 n^2 n
    public TreeNode buildTree9(int[] preorder, int[] inorder) {
      HashMap<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < inorder.length; i++) {
        map.put(inorder[i], i);
      }
      return buildTree(preorder, map, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, HashMap<Integer, Integer> map, int lPre, int rPre) {
      if (lPre > rPre) {
        return null;
      }
      int rootIn = map.get(preorder[lPre]);
      // 可优化：前中序遍历的 左右子节点序列 的长度是一样的
      int preRightStart = lPre + 1;
      while (preRightStart <= rPre && map.get(preorder[preRightStart]) < rootIn) {
        preRightStart++;
      }
      return new TreeNode(preorder[lPre],
          buildTree(preorder, map, lPre + 1, preRightStart - 1),
          buildTree(preorder, map, preRightStart, rPre)
      );
    }
    
    /*
     * ☆☆☆☆☆ 递归 + 哈希表 n n
     * 前序遍历：[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]   特性：前序遍历的第一个值为根节点
     * 中序遍历：[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
     *
     * 在中序遍历中定位根节点的索引为 rootIn，我们可以预处理出中序遍历值和索引的映射
     * 由于前序遍历和中序遍历的长度相同，因此根节点的左右子树亦是长度相同。知道中序遍历中根节点的索引 rootIn，即可求出前序遍历中左右子树序列的范围。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      int len = inorder.length;
      for (int i = 0; i < len; i++)
        val2idxIn.put(inorder[i], i);
      
      return buildTree(preorder, 0, len - 1, 0);
    }
    
    private TreeNode buildTree(int[] preorder, int lPre, int rPre, int lIn) {
      if (lPre > rPre)
        return null;
      
      int rootIn = val2idxIn.get(preorder[lPre]);
      int leftMostPre = lPre + rootIn - lIn;
      
      return new TreeNode(preorder[lPre],
          buildTree(preorder, lPre + 1, leftMostPre, lIn),
          buildTree(preorder, leftMostPre + 1, rPre, rootIn + 1)
      );
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}