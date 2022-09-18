//<p>给你二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>层序遍历</strong> 。 （即逐层地，从左到右访问所有节点）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" /> 
//<pre>
//<strong>输入：</strong>root = [3,9,20,null,null,15,7]
//<strong>输出：</strong>[[3],[9,20],[15,7]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[[1]]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目在范围 <code>[0, 2000]</code> 内</li> 
// <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1454</li><li>👎 0</li></div>
package org.example.leetcode.problems.tree;

import org.example.leetcode.problems.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//102.二叉树的层序遍历
//开题时间：2022-09-13 18:29:51
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //BFS+queue
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> tiers = new ArrayList<>();
            if (root == null)
                return tiers;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> tier = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    tier.add(poll.val);
                    if (poll.left != null)
                        queue.offer(poll.left);
                    if (poll.right != null)
                        queue.offer(poll.right);
                }

                tiers.add(tier);
            }

            return tiers;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}