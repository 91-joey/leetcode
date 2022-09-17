//<p>给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。</p>
//
//<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。”</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" /> 
//<pre>
//<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//<strong>输出：</strong>3
//<strong>解释：</strong>节点 <span><code>5 </code></span>和节点 <span><code>1 </code></span>的最近公共祖先是节点 <span><code>3 。</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;" /> 
//<pre>
//<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//<strong>输出：</strong>5
//<strong>解释：</strong>节点 <span><code>5 </code></span>和节点 <span><code>4 </code></span>的最近公共祖先是节点 <span><code>5 。</code></span>因为根据定义最近公共祖先节点可以为节点本身。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1,2], p = 1, q = 2
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目在范围 <code>[2, 10<sup>5</sup>]</code> 内。</li> 
// <li><code>-10<sup>9</sup> &lt;= Node.val &lt;= 10<sup>9</sup></code></li> 
// <li>所有 <code>Node.val</code> <code>互不相同</code> 。</li> 
// <li><code>p != q</code></li> 
// <li><code>p</code> 和 <code>q</code> 均存在于给定的二叉树中。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1961</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.common.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

//236.二叉树的最近公共祖先
//开题时间：2022-09-17 15:09:48
public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;
        System.out.println(solution.lowestCommonAncestor(node3, node5, node4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        boolean notFound = true;
        boolean allFound = false;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            helper(root, p, q);
            while (!stack.isEmpty() && !stack2.isEmpty()) {
                TreeNode poll = stack.pollLast();
                if (poll != stack2.pollLast()) {
                    return root;
                }
                root = poll;
            }
            return root;
        }

        private void helper(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || allFound) return;
            stack.push(root);
            if (root.val == p.val || root.val == q.val) {
                if (notFound) {
                    stack2 = new LinkedList<>(stack);
                    notFound = false;
                } else {
                    allFound = true;
                }
            }
            helper(root.left, p, q);
            helper(root.right, p, q);
            if (!allFound)
                stack.pop();
        }

        TreeNode ans;

        public TreeNode lowestCommonAncestorGJ(TreeNode root, TreeNode p, TreeNode q) {
            helperGJ(root, p, q);
            return ans;
        }

        //todo
        private boolean helperGJ(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;
            boolean b = root.val == p.val || root.val == q.val;
            boolean bl = helperGJ(root.left, p, q);
            boolean br = helperGJ(root.right, p, q);
            if (bl && br || (b && bl || br)) {
                ans = root;
            }
            if (b) return true;
            return bl || br;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}