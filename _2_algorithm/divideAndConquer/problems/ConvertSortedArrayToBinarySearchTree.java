//<p>给你一个整数数组 <code>nums</code> ，其中元素已经按 <strong>升序</strong> 排列，请你将其转换为一棵 <strong>高度平衡</strong> 二叉搜索树。</p>
//
//<p><strong>高度平衡 </strong>二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg" style="width: 302px; height: 222px;" /> 
//<pre>
//<strong>输入：</strong>nums = [-10,-3,0,5,9]
//<strong>输出：</strong>[0,-3,9,-10,null,5]
//<strong>解释：</strong>[0,-10,5,null,-3,null,9] 也将被视为正确答案：
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg" style="width: 302px; height: 222px;" />
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree.jpg" style="width: 342px; height: 142px;" /> 
//<pre>
//<strong>输入：</strong>nums = [1,3]
//<strong>输出：</strong>[3,1]
//<strong>解释：</strong>[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>nums</code> 按 <strong>严格递增</strong> 顺序排列</li> 
//</ul>
//
//<div><li>👍 1192</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

//108.将有序数组转换为二叉搜索树
//开题时间：2022-11-17 17:43:31
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new ConvertSortedArrayToBinarySearchTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
            if (l > r)
                return null;

            int mid = l + r >> 1;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBST(nums, l, mid - 1);
            root.right = sortedArrayToBST(nums, mid + 1, r);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}