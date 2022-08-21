//<p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>&nbsp;遍历</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="height: 200px; width: 125px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,null,2,3]
//<strong>输出：</strong>[1,3,2]
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
// <li>树中节点数目在范围 <code>[0, 100]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>
//
//<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1534</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.*;

//94.二叉树的中序遍历
//开题时间：2022-08-21 08:11:32
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
        System.out.println(solution.inorderTraversal3(treeNode1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        //1.DFS+递归
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            list.addAll(inorderTraversal(root.left));
            list.add(root.val);
            list.addAll(inorderTraversal(root.right));
            return list;
        }

        //2.DFS+栈
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Deque<TreeNode> stack = new ArrayDeque<>();
            //此处visited是多余的，因为二叉树只有左右两个分支，可优化为3.官解二（DFS+栈）
            Set<TreeNode> visited = new HashSet<>();
            stack.push(root);
            visited.add(root);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.peekLast();
                if (pop.left != null && !visited.contains(pop.left)) {
                    stack.push(pop.left);
                    continue;
                }
                list.add(pop.val);
                if (pop.right != null && !visited.contains(pop.right)) {
                    stack.push(pop.right);
                    continue;
                }
                stack.pop();
            }
            return list;
        }

        //3.官解二（DFS+栈）
        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }

            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}