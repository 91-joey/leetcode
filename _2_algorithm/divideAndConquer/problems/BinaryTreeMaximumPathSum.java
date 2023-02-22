//<p><strong>路径</strong> 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 <strong>至多出现一次</strong> 。该路径<strong> 至少包含一个 </strong>节点，且不一定经过根节点。</p>
//
//<p><strong>路径和</strong> 是路径中各节点值的总和。</p>
//
//<p>给你一个二叉树的根节点 <code>root</code> ，返回其 <strong>最大路径和</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg" style="width: 322px; height: 182px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2,3]
//<strong>输出：</strong>6
//<strong>解释：</strong>最优路径是 2 -&gt; 1 -&gt; 3 ，路径和为 2 + 1 + 3 = 6</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg" /> 
//<pre>
//<strong>输入：</strong>root = [-10,9,20,null,null,15,7]
//<strong>输出：</strong>42
//<strong>解释：</strong>最优路径是 15 -&gt; 20 -&gt; 7 ，路径和为 15 + 20 + 7 = 42
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目范围是 <code>[1, 3 * 10<sup>4</sup>]</code></li> 
// <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 1785</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import _3_common.entity.tree.TreeNode;

// 124.二叉树中的最大路径和
// 开题时间：2022-11-18 15:10:32
public class BinaryTreeMaximumPathSum {
  public static void main(String[] args) {
    Solution solution = new BinaryTreeMaximumPathSum().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int max = Integer.MIN_VALUE;
    
    // 三个关键点：某子树内部最大值，某子树向外部提供的最大值、全局最大值。
    public int maxPathSum(TreeNode root) {
      maxGain(root);
      return max;
    }
    
    public int maxGain(TreeNode root) {
      if (root == null)
        return 0;
      
      int maxL = Math.max(maxGain(root.left), 0);
      int maxR = Math.max(maxGain(root.right), 0);
      max = Math.max(max, root.val + maxL + maxR);
      
      return root.val + Math.max(maxL, maxR);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}