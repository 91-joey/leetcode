//<p>给定一个二叉搜索树 <code>root</code> 和一个目标结果 <code>k</code>，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 <code>true</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_1.jpg" style="height: 229px; width: 400px;" /> 
//<pre>
//<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 9
//<strong>输出:</strong> true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_2.jpg" style="height: 229px; width: 400px;" /> 
//<pre>
//<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 28
//<strong>输出:</strong> false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
// <li>题目数据保证，输入的 <code>root</code> 是一棵 <strong>有效</strong> 的二叉搜索树</li> 
// <li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 443</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree.BST;

import org.example.leetcode.problems._3_common.tool.Tools;
import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.util.*;
import java.util.function.Supplier;

//653.两数之和 IV - 输入二叉搜索树
//开题时间：2022-12-11 10:17:21
public class TwoSumIvInputIsABst {
    public static void main(String[] args) {
        Solution solution = new TwoSumIvInputIsABst().new Solution();
        System.out.println(solution.findTarget(Tools.buildTree("[1,0,4,-2,null,3,null]"), 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    import java.util.Collection;
    class Solution {
        //中序遍历结果 + 双指针
        public boolean findTarget9(TreeNode root, int k) {
            int[] arr = inorderTraversal(root).stream().mapToInt(Integer::intValue).toArray();
            for (int l = 0, r = arr.length - 1; l < r; )
                if (arr[l] + arr[r] == k)
                    return true;
                else if (arr[l] + arr[r] < k)
                    l++;
                else
                    r--;
            return false;
        }

        //☆☆☆☆☆ 双指针 + 中序遍历
        public boolean findTarget8(TreeNode root, int k) {
            TreeNode left = root, right = root;
            Deque<TreeNode> stackL = new LinkedList<>(), stackR = new LinkedList<>();
            stackL.push(left);
            stackR.push(right);
            while (left.left != null) {
                left = left.left;
                stackL.push(left);
            }
            while (right.right != null) {
                right = right.right;
                stackR.push(right);
            }

            while (left != right) {
                if (k == left.val + right.val)
                    return true;
                else if (k < left.val + right.val)
                    right = getRight(stackR);
                else
                    left = getLeft(stackL);
            }

            return false;
        }


        HashSet<Integer> set = new HashSet<>();

        //哈希表
        public boolean findTarget(TreeNode root, int k) {
            if (root == null)
                return false;

            if (set.contains(k - root.val))
                return true;
            set.add(root.val);
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        private TreeNode getRight(Deque<TreeNode> stack) {
            TreeNode pop = stack.pop();
            TreeNode node = pop.left;
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            return pop;
        }

        private TreeNode getLeft(Deque<TreeNode> stack) {
            TreeNode pop = stack.pop();
            TreeNode node = pop.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            return pop;
        }

        public static Collection<Integer> inorderTraversal(TreeNode root, Supplier<Collection<Integer>> collectionSupplier) {
            Collection<Integer> coll = collectionSupplier.get();
            if (root == null)
                return coll;

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
                        coll.add(cur.val);
                        left.right = null;
                        cur = cur.right;
                    }
                } else {
                    coll.add(cur.val);
                    cur = cur.right;
                }
            }

            return coll;
        }

        public static List<Integer> inorderTraversal(TreeNode root) {
            return (List<Integer>) inorderTraversal(root, ArrayList::new);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}