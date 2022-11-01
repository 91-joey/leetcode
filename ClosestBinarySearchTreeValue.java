//<p>给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。</p>
//
//<p><strong>注意：</strong></p>
//
//<ul> 
// <li>给定的目标值 target 是一个浮点数</li> 
// <li>题目保证在该二叉搜索树中只会存在一个最接近目标值的数</li> 
//</ul>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入:</strong> root = [4,2,5,1,3]，目标值 target = 3.714286
//
//    4
//   / \
//  2   5
// / \
//1   3
//
//<strong>输出:</strong> 4
//</pre>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>二分查找</li><li>二叉树</li></div></div><br><div><li>👍 128</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.util.ArrayList;

//270.最接近的二叉搜索树值
//开题时间：2022-11-01 16:59:50
public class ClosestBinarySearchTreeValue {
    public static void main(String[] args) {
        Solution solution = new ClosestBinarySearchTreeValue().new Solution();
        System.out.println(solution.closestValue(new TreeNode(5,
                        new TreeNode(3,
                                new TreeNode(2,
                                        new TreeNode(1),
                                        null),
                                new TreeNode(4)),
                        new TreeNode(6)),
                2.8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int closestValue(TreeNode root, double target) {
            int ans = root.val;
            TreeNode p = root;
            for (; p.left != null || p.right != null; ) {
                if (p.val == target) {
                    return p.val;
                } else if (p.val < target) {
                    if (p.right == null)
                        return ans;
                    else {
                        p = p.right;
                        if (Math.abs(target - p.val) < target - ans)
                            ans = p.val;
                    }
                } else {
                    if (p.left == null)
                        return ans;
                    else {
                        p = p.left;
                        if (Math.abs(target - p.val) < ans - target)
                            ans = p.val;
                    }
                }
            }
            if (Math.abs(target - p.val) < Math.abs(ans - target))
                ans = p.val;
            return ans;
        }

        //中序遍历+二分
        public int closestValue2(TreeNode root, double target) {
            ArrayList<Integer> list = new ArrayList<>();
            buildList(list, root);
            if (list.size() == 1)
                return root.val;

            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                Integer e = list.get(mid);
                if (target == e)
                    return e;
                else if (target < e)
                    r = mid;
                else
                    l = mid + 1;
            }

            int cur = list.get(r);
            if (r == 0)
                return cur;
            int pre = list.get(r - 1);
            return target - pre < cur - target ? pre : cur;
        }

        private void buildList(ArrayList<Integer> list, TreeNode root) {
            if (root == null)
                return;
            if (root.left != null) buildList(list, root.left);
            list.add(root.val);
            if (root.right != null) buildList(list, root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}