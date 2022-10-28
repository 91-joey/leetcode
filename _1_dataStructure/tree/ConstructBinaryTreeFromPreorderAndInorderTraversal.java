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
//<div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div><li>👍 1727</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//105.从前序与中序遍历序列构造二叉树
//开题时间：2022-09-16 08:32:45
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
//        System.out.println(solution.buildTree3(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        System.out.println(solution.buildTree3(new int[]{3, 9, 15, 10, 20, 7, 5, 8, 4}, new int[]{15, 9, 10, 3, 20, 5, 7, 8, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //递归
        Map<Integer, Integer> val2idx = new HashMap<>();
        int[] preorder;
        int preIdx;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            preIdx = 0;

            int idx = 0;
            for (int val : inorder)
                val2idx.put(val, idx++);

            return helper(0, inorder.length - 1);
        }

        //迭代
        private TreeNode helper(int l, int r) {
            if (l > r) return null;

            int rootVal = preorder[preIdx++];
            Integer rootIdx = val2idx.get(rootVal);
            TreeNode root = new TreeNode(rootVal);
            root.left = helper(l, rootIdx - 1);
            root.right = helper(rootIdx + 1, r);

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
                preorder =  [3, 9, 15, 10, 20, 7, 5, 8, 4]
                inorder   = [15, 9, 10, 3, 20, 5, 7, 8, 4]
        */
        //迭代
        public TreeNode buildTree2(int[] preorder, int[] inorder) {
            int length = preorder.length;
            int inIdx = 0;
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);

            for (int i = 1; i < length; i++) {
                TreeNode peek = stack.peek();
                TreeNode node = new TreeNode(preorder[i]);
                if (peek.val != inorder[inIdx]) {
                    peek.left = node;
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inIdx]) {
                        peek = stack.pop();
                        inIdx++;
                    }
                    peek.right = node;
                }
                stack.push(node);
            }

            return root;
        }

        //高分解（递归）
        //todo
        private int in;
        private int pre;
        public TreeNode buildTree3(int[] preorder, int[] inorder) {
            return build(preorder, inorder, (long) Integer.MAX_VALUE + 1);
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
                preorder =  [3, 9, 15, 10, 20, 7, 5, 8, 4]
                inorder   = [15, 9, 10, 3, 20, 5, 7, 8, 4]
        */
        private TreeNode build(int[] preorder, int[] inorder, long stop) {
            if (in < inorder.length && inorder[in] != stop) {
                TreeNode root = new TreeNode(preorder[pre++]);
                root.left = build(preorder, inorder, root.val);
                in++;
                root.right = build(preorder, inorder, stop);
                return root;
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}