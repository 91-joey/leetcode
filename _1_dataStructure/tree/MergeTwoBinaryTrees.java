//<p>给你两棵二叉树： <code>root1</code> 和 <code>root2</code> 。</p>
//
//<p>想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，<strong>不为</strong> null 的节点将直接作为新二叉树的节点。</p>
//
//<p>返回合并后的二叉树。</p>
//
//<p><strong>注意:</strong> 合并过程必须从两个树的根节点开始。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/05/merge.jpg" style="height: 163px; width: 600px;" /> 
//<pre>
//<strong>输入：</strong>root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//<strong>输出：</strong>[3,4,5,5,4,null,7]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root1 = [1], root2 = [1,2]
//<strong>输出：</strong>[2,2]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>两棵树中的节点数目在范围 <code>[0, 2000]</code> 内</li> 
// <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 1142</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

// 617.合并二叉树
// 开题时间：2023-01-26 08:44:45
public class MergeTwoBinaryTrees {
  public static void main(String[] args) {
    Solution solution = new MergeTwoBinaryTrees().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
      if (root1 == null && root2 == null)
        return null;
      
      if (root1 == null)
        return root2;
      else if (root2 == null)
        return root1;
      else {
        return new TreeNode(root1.val + root2.val,
            mergeTrees(root1.left, root2.left),
            mergeTrees(root1.right, root2.right));
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}