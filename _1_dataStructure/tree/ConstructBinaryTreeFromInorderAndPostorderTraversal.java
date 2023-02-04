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
//<div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 837</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 106.从中序与后序遍历序列构造二叉树
// 开题时间：2022-09-15 10:44:06
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public static void main(String[] args) {
    Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
    //        System.out.println(solution.buildTree2(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
    System.out.println(solution.buildTree2(new int[]{15, 9, 10, 3, 20, 5, 7, 8, 4}, new int[]{15, 10, 9, 5, 4, 8, 7, 20, 3}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 递归
    Map<Integer, Integer> val2idx = new HashMap<>();
    int[] postorder;
    int postIdx;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      this.postorder = postorder;
      postIdx = inorder.length - 1;
      
      int idx = 0;
      for (int val : inorder)
        val2idx.put(val, idx++);
      
      return helper(0, postIdx);
    }
    
    // 递归
    private TreeNode helper(int l, int r) {
      if (l > r) return null;
      
      int rootVal = postorder[postIdx--];
      Integer rootIdx = val2idx.get(rootVal);
      TreeNode root = new TreeNode(rootVal);
      root.right = helper(rootIdx + 1, r);
      root.left = helper(l, rootIdx - 1);
      
      return root;
    }
    
    /*
                 3
                / \
               9  20
              / \   \
             15 10   7
                    / \
                   5   8
                        \
                         4
            inorder   = [15, 9, 10, 3, 20, 5, 7, 8, 4]
            postorder = [15, 10, 9, 5, 4, 8, 7, 20, 3]
    */
    // 迭代
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
      int length = postorder.length;
      int inIdx = length - 1;
      TreeNode root = new TreeNode(postorder[length - 1]);
      Deque<TreeNode> stack = new LinkedList<>();
      stack.push(root);
      
      for (int i = length - 2; i >= 0; i--) {
        TreeNode peek = stack.peek();
        TreeNode node = new TreeNode(postorder[i]);
        if (peek.val != inorder[inIdx]) {
          peek.right = node;
        } else {
          while (!stack.isEmpty() && stack.peek().val == inorder[inIdx]) {
            peek = stack.pop();
            inIdx--;
          }
          peek.left = node;
        }
        stack.push(node);
      }
      
      return root;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}