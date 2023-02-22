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
package _2_algorithm.divideAndConquer.problems;

import _3_common.entity.tree.TreeNode;

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
    Map<Integer, Integer> val2idxIn = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      int len = inorder.length;
      for (int i = 0; i < len; i++)
        val2idxIn.put(inorder[i], i);
      
      return buildTree(postorder, 0, len - 1, 0);
    }
    
    private TreeNode buildTree(int[] postorder, int lPost, int rPost, int lIn) {
      if (lPost > rPost)
        return null;
      
      int rootIn = val2idxIn.get(postorder[rPost]);
      int leftMostPost = lPost + rootIn - lIn - 1;
      
      return new TreeNode(postorder[rPost],
          buildTree(postorder, lPost, leftMostPost, lIn),
          buildTree(postorder, leftMostPost + 1, rPost - 1, rootIn + 1)
      );
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}