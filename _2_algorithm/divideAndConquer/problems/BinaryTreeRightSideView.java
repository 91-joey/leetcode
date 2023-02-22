//<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/02/14/tree.jpg" style="width: 270px; " /></p>
//
//<pre>
//<strong>输入:</strong>&nbsp;[1,2,3,null,5,null,4]
//<strong>输出:</strong>&nbsp;[1,3,4]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong>&nbsp;[1,null,3]
//<strong>输出:</strong>&nbsp;[1,3]
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong>&nbsp;[]
//<strong>输出:</strong>&nbsp;[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li>二叉树的节点个数的范围是 <code>[0,100]</code></li> 
// <li>
//  <meta charset="UTF-8" /><code>-100&nbsp;&lt;= Node.val &lt;= 100</code>&nbsp;</li> 
//</ul>
//
//<div><li>👍 783</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import _3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 199.二叉树的右视图
// 开题时间：2022-11-18 17:24:06
public class BinaryTreeRightSideView {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeRightSideView().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // BFS
    public List<Integer> rightSideView9(TreeNode root) {
      List<Integer> ans = new ArrayList<>();
      if (root == null)
        return ans;
      
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      
      TreeNode poll = null;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          poll = q.poll();
          if (poll.left != null) q.offer(poll.left);
          if (poll.right != null) q.offer(poll.right);
        }
        ans.add(poll.val);
      }
      
      return ans;
    }
    
    List<Integer> ans = new ArrayList<>();
    
    public List<Integer> rightSideView(TreeNode root) {
      dfs(root, 1);
      return ans;
    }
    
    private void dfs(TreeNode root, int depth) {
      if (root == null)
        return;
      
      if (ans.size() < depth)
        ans.add(root.val);
      
      dfs(root.right, depth + 1);
      dfs(root.left, depth + 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}