package org.example.leetcode.problems.contest.lccup22.team;

import org.example.leetcode.problems.common.tree.TreeNode;

public class ExpandBinaryTree {
    public static void main(String[] args) {

    }

    public static TreeNode expandBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null)
            return root;
        if (root.left != null)
            root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
        if (root.right != null)
            root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
        return root;
    }
}
