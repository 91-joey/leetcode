//<p>给定二叉搜索树（BST）的根节点
// <meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;和一个整数值
// <meta charset="UTF-8" />&nbsp;<code>val</code>。</p>
//
//<p>你需要在 BST 中找到节点值等于&nbsp;<code>val</code>&nbsp;的节点。 返回以该节点为根的子树。 如果节点不存在，则返回
// <meta charset="UTF-8" />&nbsp;<code>null</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2021/01/12/tree1.jpg" style="height: 179px; width: 250px;" />
// <meta charset="UTF-8" /></p>
//
//<pre>
//<b>输入：</b>root = [4,2,7,1,3], val = 2
//<b>输出：</b>[2,1,3]
//</pre>
//
//<p><strong>示例 2:</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/01/12/tree2.jpg" style="height: 179px; width: 250px;" /> 
//<pre>
//<b>输入：</b>root = [4,2,7,1,3], val = 5
//<b>输出：</b>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>数中节点数在&nbsp;<code>[1, 5000]</code>&nbsp;范围内</li> 
// <li><code>1 &lt;= Node.val &lt;= 10<sup>7</sup></code></li> 
// <li><code>root</code>&nbsp;是二叉搜索树</li> 
// <li><code>1 &lt;= val &lt;= 10<sup>7</sup></code></li> 
//</ul>
//
//<div><li>👍 346</li><li>👎 0</li></div>
package _1_dataStructure.tree.BST;

import _3_common.entity.tree.TreeNode;

// 700.二叉搜索树中的搜索
// 开题时间：2022-12-10 11:11:53
public class SearchInABinarySearchTree {
  public static void main(String[] args) {
    Solution solution = new SearchInABinarySearchTree().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // recursive
    public TreeNode searchBST9(TreeNode root, int val) {
      if (root == null)
        return null;
      
      if (root.val == val)
        return root;
      else if (root.val < val)
        return searchBST(root.right, val);
      else
        return searchBST(root.left, val);
    }
    
    //☆☆☆☆☆ relative
    public TreeNode searchBST(TreeNode root, int val) {
      while (root != null)
        if (root.val == val)
          return root;
        else if (root.val < val)
          root = root.right;
        else
          root = root.left;
      return null;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}