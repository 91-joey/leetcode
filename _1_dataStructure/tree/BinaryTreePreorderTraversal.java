//<p>给你二叉树的根节点 <code>root</code> ，返回它节点值的&nbsp;<strong>前序</strong><em>&nbsp;</em>遍历。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="width: 202px; height: 324px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,null,2,3]
//<strong>输出：</strong>[1,2,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[1]
//</pre>
//
//<p><strong>示例 4：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_5.jpg" style="width: 202px; height: 202px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2]
//<strong>输出：</strong>[1,2]
//</pre>
//
//<p><strong>示例 5：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_4.jpg" style="width: 202px; height: 202px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,null,2]
//<strong>输出：</strong>[1,2]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目在范围 <code>[0, 100]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>
//
//<div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 908</li><li>👎 0</li></div>
package _1_dataStructure.tree;

import _3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 144.二叉树的前序遍历
// 开题时间：2022-09-12 08:27:45
public class BinaryTreePreorderTraversal {
  public static void main(String[] args) {
    Solution solution = new BinaryTreePreorderTraversal().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // DFS+递归    n   n
    List<Integer> list = new ArrayList<>();
    
    public List<Integer> preorderTraversal(TreeNode root) {
      dfs(root);
      return list;
    }
    
    public void dfs(TreeNode root) {
      if (root == null)
        return;
      
      dfs(root.left);
      list.add(root.val);
      dfs(root.right);
    }
    
    // DFS+栈 n   n
    public List<Integer> preorderTraversal_stack(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null)
        return list;
      Deque<TreeNode> stack = new LinkedList<>();
      stack.push(root);
      
      while (!stack.isEmpty()) {
        TreeNode pop = stack.pop();
        list.add(pop.val);
        if (pop.right != null)
          stack.push(pop.right);
        if (pop.left != null)
          stack.push(pop.left);
      }
      
      return list;
    }
    
    // Morris解法  n   1
    public List<Integer> preorderTraversal_morris(TreeNode root) {
      List<Integer> list = new ArrayList<>();
      if (root == null)
        return list;
      
      TreeNode cur = root;
      TreeNode l;
      while (cur != null) {
        l = cur.left;
        if (l != null) {
          while (l.right != null && l.right != cur) {
            l = l.right;
          }
          if (l.right == null) {
            l.right = cur;
            list.add(cur.val);
            cur = cur.left;
          } else {
            l.right = null;
            cur = cur.right;
          }
        } else {
          list.add(cur.val);
          cur = cur.right;
        }
      }
      
      return list;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}