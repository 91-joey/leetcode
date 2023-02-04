//<p>给定两个整数数组 <code>inorder</code> 和 <code>postorder</code> ，其中 <code>inorder</code> 是二叉树的中序遍历， <code>postorder</code> 是同一棵树的后序遍历，请你构造并返回这颗&nbsp;<em>二叉树</em>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" /> 
//<pre>
//<b>输入：</b>inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//<b>输出：</b>[3,9,20,null,null,15,7]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<b>输入：</b>inorder = [-1], postorder = [-1]
//<b>输出：</b>[-1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= inorder.length &lt;= 3000</code></li> 
// <li><code>postorder.length == inorder.length</code></li> 
// <li><code>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</code></li> 
// <li><code>inorder</code>&nbsp;和&nbsp;<code>postorder</code>&nbsp;都由 <strong>不同</strong> 的值组成</li> 
// <li><code>postorder</code>&nbsp;中每一个值都在&nbsp;<code>inorder</code>&nbsp;中</li> 
// <li><code>inorder</code>&nbsp;<strong>保证</strong>是树的中序遍历</li> 
// <li><code>postorder</code>&nbsp;<strong>保证</strong>是树的后序遍历</li> 
//</ul>
//
//<div><li>👍 880</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

// 106.从中序与后序遍历序列构造二叉树
// 开题时间：2022-11-17 17:14:42
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public static void main(String[] args) {
    Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  class Solution {
    Map<Integer, Integer> val2idx = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      int len = inorder.length;
      for (int i = 0; i < len; i++)
        val2idx.put(inorder[i], i);
      
      return buildTree(postorder, 0, len - 1, 0, len - 1);
    }
    
    private TreeNode buildTree(int[] postorder, int l1, int r1, int l2, int r2) {
      if (l1 > r1)
        return null;
      
      TreeNode root = new TreeNode(postorder[r2]);
      int rootIdxIn = val2idx.get(postorder[r2]);
      int rightStart = l2 + rootIdxIn - l1;
      root.left = buildTree(postorder, l1, rootIdxIn - 1, l2, rightStart - 1);
      root.right = buildTree(postorder, rootIdxIn + 1, r1, rightStart, r2 - 1);
      
      return root;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}