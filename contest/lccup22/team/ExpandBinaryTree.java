package org.example.leetcode.problems.contest.lccup22.team;

import org.example.leetcode.problems.common.tree.TreeNode;

//LCP 67. 装饰树
public class ExpandBinaryTree {
    //递归
    public static TreeNode expandBinaryTree(TreeNode root) {
        if (root.left != null)
            root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
        else if (root.right == null)
            return root;
        if (root.right != null)
            root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
        return root;
    }
}
