//<p>给定一个二叉树，找出其最大深度。</p>
//
//<p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>
//
//<p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>
//
//<p><strong>示例：</strong><br> 给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</br></p>
//
//<pre>    3
//   / \
//  9  20
//    /  \
//   15   7</pre>
//
//<p>返回它的最大深度&nbsp;3 。</p>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1371</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//104.二叉树的最大深度
//开题时间：2022-09-14 08:59:25
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int max = 0;

        //DFS+自顶向下
        public int maxDepth(TreeNode root) {
            DFS(root, 1);
            return max;
        }

        private void DFS(TreeNode node, int depth) {
            if (node == null)
                return;
            //叶节点时，更新最大深度
            if (node.left == null && node.right == null) {
                max = Math.max(max, depth);
                return;
            }
            DFS(node.left, depth + 1);
            DFS(node.right, depth + 1);
        }

        //DFS+自底向上
        public int maxDepth2(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
        }

        //BFS
        public int maxDepth3(TreeNode root) {
            if (root == null)
                return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int max = 0;

            while (!queue.isEmpty()) {
                max++;
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null)
                        queue.offer(poll.left);
                    if (poll.right != null)
                        queue.offer(poll.right);
                }

            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}