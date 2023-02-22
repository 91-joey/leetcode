//<p>给定二叉搜索树（BST）的根节点
// <meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;和要插入树中的值
// <meta charset="UTF-8" />&nbsp;<code>value</code>&nbsp;，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 <strong>保证</strong> ，新值和原始二叉搜索树中的任意节点值都不同。</p>
//
//<p><strong>注意</strong>，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 <strong>任意有效的结果</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/insertbst.jpg" /> 
//<pre>
//<strong>输入：</strong>root = [4,2,7,1,3], val = 5
//<strong>输出：</strong>[4,2,7,1,3,5]
//<strong>解释：</strong>另一个满足题目要求可以通过的树是：
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/bst.jpg" />
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [40,20,60,10,30,50,70], val = 25
//<strong>输出：</strong>[40,20,60,10,30,50,70,null,null,25]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
//<strong>输出：</strong>[4,2,7,1,3,5]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中的节点数将在
//  <meta charset="UTF-8" />&nbsp;<code>[0,&nbsp;10<sup>4</sup>]</code>的范围内。
//  <meta charset="UTF-8" /></li> 
// <li><code>-10<sup>8</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>8</sup></code></li> 
// <li>所有值&nbsp;
//  <meta charset="UTF-8" /><code>Node.val</code>&nbsp;是&nbsp;<strong>独一无二</strong>&nbsp;的。</li> 
// <li><code>-10<sup>8</sup>&nbsp;&lt;= val &lt;= 10<sup>8</sup></code></li> 
// <li><strong>保证</strong>&nbsp;<code>val</code>&nbsp;在原始BST中不存在。</li> 
//</ul>
//
//<div><li>👍 409</li><li>👎 0</li></div>
package _1_dataStructure.tree.BST;

import _3_common.entity.tree.TreeNode;

// 701.二叉搜索树中的插入操作
// 开题时间：2022-12-10 11:21:18
public class InsertIntoABinarySearchTree {
  public static void main(String[] args) {
    Solution solution = new InsertIntoABinarySearchTree().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // recursive(sophisticated)
    public TreeNode insertIntoBST9(TreeNode root, int val) {
      TreeNode newbie = new TreeNode(val);
      if (root == null)
        return newbie;
      
      TreeNode l = root, r = root;
      
      for (TreeNode node = root; node != null; )
        if (val < node.val) {
          r = node;
          node = node.left;
        } else {
          l = node;
          node = node.right;
        }
      
      if (l.right == null)
        l.right = newbie;
      else
        r.left = newbie;
      
      return root;
    }
    
    /*
     *☆☆☆☆☆ recursive
     *  插入值位于左侧:
     *      左节点为空，赋予左节点
     *      左节点非空，插入左节点中
     *  插入值位于右侧:
     *      右节点为空，赋予右节点
     *      右节点非空，插入右节点中
     */
    public TreeNode insertIntoBST8(TreeNode root, int val) {
      if (root == null)
        return new TreeNode(val);
      
      if (val < root.val)
        root.left = insertIntoBST(root.left, val);
      else
        root.right = insertIntoBST(root.right, val);
      
      return root;
    }
    
    // relative
    public TreeNode insertIntoBST(TreeNode root, int val) {
      TreeNode newbie = new TreeNode(val);
      if (root == null)
        return newbie;
      
      for (TreeNode node = root; node != null; )
        if (val < node.val) {
          if (node.left == null) {
            node.left = newbie;
            break;
          }
          node = node.left;
        } else if (node.right == null) {
          node.right = newbie;
          break;
        } else
          node = node.right;
      
      return root;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}