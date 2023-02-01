//<p>给你一棵二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>后序遍历 </strong>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg" style="width: 127px; height: 200px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,null,2,3]
//<strong>输出：</strong>[3,2,1]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点的数目在范围 <code>[0, 100]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>
//
//<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 921</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//145.二叉树的后序遍历
//开题时间：2022-09-13 15:46:08
public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        //DFS+递归
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();

            if (root == null)
                return list;
            list.addAll(postorderTraversal(root.left));
            list.addAll(postorderTraversal(root.right));
            list.add(root.val);

            return list;
        }

        //DFS+栈    n   n
        public List<Integer> postorderTraversal_stack(TreeNode root) {
            LinkedList<Integer> list = new LinkedList<>();
            if (root == null)
                return list;
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                list.addFirst(pop.val);
                if (pop.left != null)
                    stack.push(pop.left);
                if (pop.right != null)
                    stack.push(pop.right);
            }

            return list;
        }

        //Morris解法  n   1
        public List<Integer> postorderTraversal_morris(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null)
                return list;

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
                        left.right = null;
                        addDiagonal(list, cur.left);
                        cur = cur.right;
                    }
                } else {
                    cur = cur.right;
                }
            }
            addDiagonal(list, root);

            return list;
        }

        //对角线反序 添加元素
        private void addDiagonal(List<Integer> list, TreeNode root) {
            int cnt = 0;
            while (root != null) {
                list.add(root.val);
                root = root.right;
                cnt++;
            }
            //reverse
            int size = list.size();
            int l = size - cnt;
            int r = size - 1;
            while (l < r) {
                Integer tmp = list.get(l);
                list.set(l++, list.get(r));
                list.set(r--, tmp);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}