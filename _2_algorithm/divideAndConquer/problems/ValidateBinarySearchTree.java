//<p>给你一个二叉树的根节点 <code>root</code> ，判断其是否是一个有效的二叉搜索树。</p>
//
//<p><strong>有效</strong> 二叉搜索树定义如下：</p>
//
//<ul> 
// <li>节点的左子树只包含<strong> 小于 </strong>当前节点的数。</li> 
// <li>节点的右子树只包含 <strong>大于</strong> 当前节点的数。</li> 
// <li>所有左子树和右子树自身必须也是二叉搜索树。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" /> 
//<pre>
//<strong>输入：</strong>root = [2,1,3]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" /> 
//<pre>
//<strong>输入：</strong>root = [5,1,4,null,null,3,6]
//<strong>输出：</strong>false
//<strong>解释：</strong>根节点的值是 5 ，但是右子节点的值是 4 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目范围在<code>[1, 10<sup>4</sup>]</code> 内</li> 
// <li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 1816</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;
import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 98.验证二叉搜索树
// 开题时间：2022-11-17 17:59:16
public class ValidateBinarySearchTree {
  public static void main(String[] args) {
    Solution solution = new ValidateBinarySearchTree().new Solution();
    //        System.out.println(solution.isValidBST(Tools.buildTree("[2,1,3]")));
    //        System.out.println(solution.isValidBST(Tools.buildTree("[5,4,6,null,null,3,7]")));
    System.out.println(solution.isValidBST(Tools.buildTree("[120,70,140,50,100,130,160,20,55,75,110,119,135,150,200]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     *       5
     *      / \
     *     4   6
     *        / \
     *       3   7
     */
    public boolean isValidBSTX(TreeNode root) {
      TreeNode dummy = new TreeNode(root.right == null ? root.val + 1 : root.right.val + 1, root, null);
      return isValidBST(dummy, root);
    }
    
    private boolean isValidBST(TreeNode root, TreeNode cur) {
      if (cur == null)
        return true;
      
      return (cur.left == null || (cur.val > cur.left.val && cur.left.val > root.val))
          &&
          (cur.right == null || (cur.val < cur.right.val && cur.right.val < root.val))
          &&
          isValidBST(cur, cur.left)
          &&
          isValidBST(cur, cur.right);
    }
    
    public boolean isValidBSTXX(TreeNode root) {
      if (helper(root)) {
        TreeNode node = root.left;
        while (node != null && node.right != null)
          node = node.right;
        if (node == null || node.val < root.val) {
          node = root.right;
          while (node != null && node.left != null)
            node = node.left;
          return node == null || node.val > root.val;
        }
      }
      return false;
    }
    
    private boolean helper(TreeNode root) {
      if (root == null)
        return true;
      
      return (root.left == null || (root.val > root.left.val && (root.left.right == null || root.left.right.val < root.val)))
          &&
          (root.right == null || (root.val < root.right.val && (root.right.left == null || root.right.left.val > root.val)))
          &&
          helper(root.left)
          &&
          helper(root.right);
    }
    
    // 中序遍历 + 判断升序
    public boolean isValidBST7(TreeNode root) {
      return isSortedNaturally(inorderTraversal(root));
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      
      if (root == null)
        return list;
      list.addAll(inorderTraversal(root.left));
      list.add(root.val);
      list.addAll(inorderTraversal(root.right));
      
      return list;
    }
    
    public static boolean isSortedNaturally(List<Integer> list) {
      for (int i = 0; i < list.size() - 1; i++)
        if (list.get(i) >= list.get(i + 1))
          return false;
      return true;
    }
    
    //☆☆☆☆☆ 递归
    public boolean isValidBST6(TreeNode root) {
      return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode root, long min, long max) {
      if (root == null)
        return true;
      
      return min < root.val &&
          root.val < max &&
          helper(root.left, min, root.val) &&
          helper(root.right, root.val, max);
    }
    
    // 中序遍历（Morris）
    public boolean isValidBST5(TreeNode root) {
      long curVal = Long.MIN_VALUE;
      TreeNode cur = root;
      TreeNode left;
      while (cur != null) {
        left = cur.left;
        if (left != null) {
          while (left.right != null && left.right != cur) {
            left = left.right;
          }
          if (left.right == null) {
            left.right = cur;
            cur = cur.left;
          } else {
            if (curVal >= cur.val)
              return false;
            curVal = cur.val;
            left.right = null;
            cur = cur.right;
          }
        } else {
          if (curVal >= cur.val)
            return false;
          curVal = cur.val;
          cur = cur.right;
        }
      }
      return true;
    }
    
    // 中序遍历（栈）
    public boolean isValidBST(TreeNode root) {
      long curVal = Long.MIN_VALUE;
      Deque<TreeNode> stack = new LinkedList<>();
      while (root != null || !stack.isEmpty()) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        root = stack.pop();
        if (curVal >= root.val)
          return false;
        curVal = root.val;
        root = root.right;
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}