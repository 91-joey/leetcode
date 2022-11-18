//<p>给定一个二叉树，判断它是否是高度平衡的二叉树。</p>
//
//<p>本题中，一棵高度平衡二叉树定义为：</p>
//
//<blockquote> 
// <p>一个二叉树<em>每个节点&nbsp;</em>的左右两个子树的高度差的绝对值不超过 1 。</p> 
//</blockquote>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;" /> 
//<pre>
//<strong>输入：</strong>root = [3,9,20,null,null,15,7]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2,2,3,3,null,null,4,4]
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中的节点数在范围 <code>[0, 5000]</code> 内</li> 
// <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 1184</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tree.TreeNode;

//110.平衡二叉树
//开题时间：2022-11-18 12:25:12
public class BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //递归（自顶向下）
        public boolean isBalanced9(TreeNode root) {
            if (root == null)
                return true;

            return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 &&
                    isBalanced(root.left) &&
                    isBalanced(root.right);
        }

        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }

        //递归（自底向上）
        public boolean isBalance8d(TreeNode root) {
            return maxHeight(root) >= 0;
        }

        private int maxHeight(TreeNode root) {
            if (root == null)
                return 0;

            int heightL = maxHeight(root.left);
            int heightR = maxHeight(root.right);
            if (heightL != -1 && heightR != -1 && Math.abs(heightL - heightR) <= 1)
                return Math.max(heightL, heightR) + 1;
            else
                return -1;
        }

        //☆☆☆☆☆ 递归（自底向上）（优化：左子树不平衡时，不再求解右子树）
        public boolean isBalanced(TreeNode root) {
            return maxHeight2(root) >= 0;
        }

        private int maxHeight2(TreeNode root) {
            if (root == null)
                return 0;

            int heightL;
            int heightR;
            if ((heightL = maxHeight2(root.left)) != -1 &&
                    (heightR = maxHeight2(root.right)) != -1 &&
                    Math.abs(heightL - heightR) <= 1)
                return Math.max(heightL, heightR) + 1;
            else
                return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}