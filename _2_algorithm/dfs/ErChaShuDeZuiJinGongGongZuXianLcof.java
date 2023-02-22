package _2_algorithm.dfs;

import _3_common.entity.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 68 - II.二叉树的最近公共祖先 <br>
 * 开题时间：2023-02-02 13:35:14
 */
public class ErChaShuDeZuiJinGongGongZuXianLcof {
  public static void main(String[] args) {
    Solution solution = new ErChaShuDeZuiJinGongGongZuXianLcof().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    Deque<TreeNode> pathP;
    Deque<TreeNode> pathQ;
    
    public TreeNode lowestCommonAncestor9(TreeNode root, TreeNode p, TreeNode q) {
      getPaths(root, p, q, new LinkedList<TreeNode>());
      
      TreeNode ans = root;
      while (pathP.peek() == pathQ.peek()) {
        ans = pathP.pop();
        pathQ.pop();
      }
      
      return ans;
    }
    
    // ☆☆☆☆☆ 「后序遍历」的思想
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      // 只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
      if (root == null || root == p || root == q) {
        return root;
      }
      
      // 根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
      TreeNode l = lowestCommonAncestor(root.left, p, q);
      TreeNode r = lowestCommonAncestor(root.right, p, q);
      // 左子树没有p也没有q，就返回右子树的结果
      // p和q都没找到，那就没有
      if (l == null) {
        return r;
      }
      // 右子树没有p也没有q就返回左子树的结果
      if (r == null) {
        return l;
      }
      // 左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
      return root;
    }
    
    private void getPaths(TreeNode root, TreeNode p, TreeNode q, Deque<TreeNode> path) {
      path.addLast(root);
      if (root == p) {
        pathP = new LinkedList<>(path);
      } else if (root == q) {
        pathQ = new LinkedList<>(path);
      }
      
      if (root.left != null) {
        getPaths(root.left, p, q, path);
        path.removeLast();
      }
      if (root.right != null) {
        getPaths(root.right, p, q, path);
        path.removeLast();
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}