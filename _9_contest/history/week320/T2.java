package org.example.leetcode.problems._9_contest.history.week320;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;

//6242. Closest Nodes Queries in a Binary Search Tree
public class T2 {
    public static void main(String[] args) {

    }

    //树遍历
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        ArrayList<List<Integer>> ans = new ArrayList<>(queries.size());

        for (int target : queries) {
            TreeNode p = root;
            int l = p.val;
            int r = p.val;
            while (p != null) {
                if (target <= p.val) {
                    r = p.val;
                    p = p.left;
                } else {
                    l = p.val;
                    p = p.right;
                }
            }

            if (target == r)
                ans.add(List.of(target, target));
            else
                ans.add(List.of(
                        l <= target ? l : -1,
                        r >= target ? r : -1
                ));
        }

        return ans;
    }

    //☆☆☆☆☆ 中序遍历 + 二分查找
    public List<List<Integer>> closestNodes9(TreeNode root, List<Integer> queries) {
        int[] arr = inorderTraversal(root, ArrayList::new).stream().mapToInt(Integer::intValue).toArray();

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int query : queries) {
            int search = Arrays.binarySearch(arr, query);
            int i = -1, j = -1;
            if (search >= 0) {
                i = j = arr[search];
            } else {
                search = -search - 1;
                if (search < arr.length)
                    j = arr[search];
                if (search > 0)
                    i = arr[search - 1];
            }
            ans.add(List.of(i, j));
        }
        return ans;
    }

    //中序遍历 + TreeSet
    public List<List<Integer>> closestNodes8(TreeNode root, List<Integer> queries) {
        TreeSet<Integer> set = (TreeSet<Integer>) inorderTraversal(root, TreeSet::new);

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int query : queries) {
            Integer min = set.floor(query);
            Integer max = set.ceiling(query);
            ans.add(List.of(
                    min == null ? -1 : min,
                    max == null ? -1 : max
            ));
        }
        return ans;
    }

    //错误中序遍历法 时间复杂度为 n^2
    public List<Integer> inorderTraversalX(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null)
            return list;
        list.addAll(inorderTraversalX(root.left));
        list.add(root.val);
        list.addAll(inorderTraversalX(root.right));

        return list;
    }


    public static List<Integer> inorderTraversal(TreeNode root) {
        return (List<Integer>) inorderTraversal(root, ArrayList::new);
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
}
