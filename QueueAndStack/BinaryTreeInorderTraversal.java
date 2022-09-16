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
package org.example.leetcode.problems.QueueAndStack;

import org.example.leetcode.problems.common.tree.TreeNode;

import java.util.*;

//94.二叉树的中序遍历
//开题时间：2022-08-21 08:11:32
//中序遍历: 遍历顺序（左子树 -> 根节点 -> 右子树）
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
        System.out.println(solution.inorderTraversal4(treeNode1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//Definition for a binary tree node.

    class Solution {
        //1.DFS+递归
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            //若root.left为null，返回空集合，空集合再添加到结果集合中
            //这里可以优化：另起一个方法，若节点为空，则返回（参考3.官解一：DFS+递归）
            list.addAll(inorderTraversal(root.left));
            list.add(root.val);
            list.addAll(inorderTraversal(root.right));
            return list;
        }

        //2.DFS+栈   n   n
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


        //3.官解一：DFS+递归
        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            traverse(root,ans);
            return ans;
        }

        private void traverse(TreeNode root, List<Integer> ans) {
            if(root!=null){
                traverse(root.left,ans);
                ans.add(root.val);
                traverse(root.right,ans);
            }
        }

        //4.官解二（DFS+栈）  n   n
        public List<Integer> inorderTraversal4(TreeNode root) {
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

        //5.官解三：Morris中序遍历  n   1
        public List<Integer> inorderTraversal5(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode predecessor;

            while (root != null) {
                if (root.left != null) {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    //predecessor.right != root ->  第二次遍历（左子树已遍历完成），附加条件，防止进入死循环
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }

                    if (predecessor.right == null) {
                        // 让 predecessor 的右指针指向 root
                        predecessor.right = root;
                        //继续遍历左子树
                        root = root.left;
                        // 第二次遍历（左子树已遍历完成）
                    } else {
                        //"遍历"根值
                        res.add(root.val);
                        //断开链接，释放空间
                        predecessor.right = null;
                        //开始遍历右子树
                        root = root.right;
                    }
                    //没有左子树
                } else {
                    //"遍历"根值
                    res.add(root.val);
                    //开始遍历右子树
                    root = root.right;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}