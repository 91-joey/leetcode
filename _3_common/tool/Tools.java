package org.example.leetcode.problems._3_common.tool;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;
import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Tools {
    public static final char OPEN_BRACE = '{';
    public static final char CLOSE_BRACE = '}';
    public static final char OPEN_PAREN = '(';
    public static final char CLOSE_PAREN = ')';
    public static final char OPEN_BRACKET = '[';
    public static final char CLOSE_BRACKET = ']';
    public static final int[] SHUFFLED_ARR_EASY = {6, 2, 1, 3, 5, 4};
    public static final int[] SHUFFLED_ARR_HARD = {69, 54, 87, 27, 37, 56, 31, 76, 23, 47, 38, 44, 60, 86, 84, 96, 10, 73, 25, 19, 40, 30, 66, 65, 67, 4, 35, 12, 55, 0, 32, 8, 59, 33, 46, 97, 14, 91, 24, 71, 75, 89, 50, 92, 2, 90, 63, 64, 45, 39, 51, 52, 18, 94, 21, 5, 57, 3, 72, 61, 77, 53, 83, 13, 48, 9, 74, 16, 99, 6, 62, 93, 26, 58, 95, 98, 41, 15, 22, 49, 88, 81, 29, 42, 20, 70, 43, 79, 82, 85, 1, 11, 28, 7, 68, 80, 17, 34, 36, 78};
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final int MOD = 10_0000_0007;

    public static void main(String[] args) {
    }

    public static List<Integer> toList(int[] ints) {
        return Arrays.stream(ints).boxed().toList();
    }

    public static int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isVowelLowerAndUpper(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u',
                    'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }

    public static boolean isVowelLowerOnly(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
    }

    public static int maxWindow(int[] arr, int k) {
        int sumMax = 0;
        for (int i = 0; i < k; i++)
            sumMax += arr[i];

        for (int i = k, sumCur = sumMax; i < arr.length; i++) {
            sumCur += arr[i] - arr[i - k];
            sumMax = Math.max(sumMax, sumCur);
        }

        return sumMax;
    }

    public static void sort(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(SHUFFLED_ARR_EASY));
        consumer.accept(SHUFFLED_ARR_EASY);
        System.out.println(Arrays.toString(SHUFFLED_ARR_EASY));
        System.out.println("isSortedNaturally = " + isSortedNaturally(SHUFFLED_ARR_EASY));
    }

    public static void sortHard(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(SHUFFLED_ARR_HARD));
        consumer.accept(SHUFFLED_ARR_HARD);
        System.out.println(Arrays.toString(SHUFFLED_ARR_HARD));
        System.out.println("isSortedNaturally = " + isSortedNaturally(SHUFFLED_ARR_HARD));
    }

    public static boolean isSortedNaturally(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }

    public static boolean isSortedNaturally(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++)
            if (list.get(i) > list.get(i + 1))
                return false;
        return true;
    }

    public static int gcd(int a, int b) {
        return b != 0 ?
                gcd(b, a % b) :
                a;
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 返回 两数之合 = 目标值 的索引数组
     *
     * @param arr
     * @param target
     * @return
     */

    public static int[] searchSumOfTwo(int[] arr, int start, int end, int target) {
        end--;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == target) {
                return new int[]{start, end};
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{};
    }

    public static int[] searchSumOfTwo(int[] arr, int target) {
        return searchSumOfTwo(arr, 0, arr.length, target);
    }

    public static TreeNode buildTree(String s) {
        String[] split = s.substring(1, s.length() - 1).split(",");
        if (split[0].equals("null"))
            return null;

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        q.offer(root);
        for (int i = 1; i < split.length; ) {
            for (int j = q.size(); j > 0 && i < split.length; j--) {
                TreeNode poll = q.poll();
                if (poll != null) {
                    q.offer(poll.left = buildTreeWithSingleWord(split[i++]));
                    q.offer(poll.right = buildTreeWithSingleWord(split[i++]));
                }
            }
        }
        return root;
    }

    public static TreeNode buildTreeWithSingleWord(String s) {
        if (s.equals("null"))
            return null;
        else
            return new TreeNode(Integer.parseInt(s));
    }

    public static boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }

    //region binary tree traversal
    public static Collection<Integer> preorderTraversal(TreeNode root, Supplier<Collection<Integer>> collectionSupplier) {
        Collection<Integer> coll = collectionSupplier.get();
        if (root == null)
            return coll;

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
                    coll.add(cur.val);
                    cur = cur.left;
                } else {
                    l.right = null;
                    cur = cur.right;
                }
            } else {
                coll.add(cur.val);
                cur = cur.right;
            }
        }

        return coll;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        return (List<Integer>) preorderTraversal(root, ArrayList::new);
    }

    public static Collection<Integer> postorderTraversal(TreeNode root, Supplier<Collection<Integer>> collectionSupplier) {
        Collection<Integer> coll = collectionSupplier.get();
        if (root == null)
            return coll;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            coll.add(pop.val);
            if (pop.left != null)
                stack.push(pop.left);
            if (pop.right != null)
                stack.push(pop.right);
        }

        return coll;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        return (List<Integer>) postorderTraversal(root, ArrayList::new);
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

    public static List<Integer> inorderTraversal(TreeNode root) {
        return (List<Integer>) inorderTraversal(root, ArrayList::new);
    }

    public static Collection<Integer> levelOrderTraversal(TreeNode root, Supplier<Collection<Integer>> collectionSupplier) {
        Collection<Integer> coll = collectionSupplier.get();
        if (root == null)
            return coll;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                TreeNode poll = q.poll();
                coll.add(poll.val);
                if (poll.left != null) q.offer(poll.left);
                if (poll.right != null) q.offer(poll.right);
            }
        }

        return coll;
    }

    public static List<Integer> levelOrderTraversal(TreeNode root) {
        return (List<Integer>) levelOrderTraversal(root, ArrayList::new);
    }
    //endregion

    public static int[] toIntArray(String s) {
        String strip = s.strip();
        String substring = strip.substring(1, strip.length() - 1);
        if (substring.isEmpty())
            return new int[]{};
        return Arrays.stream(substring.split(",")).map(String::strip).mapToInt(Integer::parseInt).toArray();
    }

    public static int[][] to2DIntArray(String s) {
        return Arrays.stream(s.substring(1, s.length() - 1).split("(?<=]),")).map(Tools::toIntArray).toArray(int[][]::new);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static int max(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int x : arr) if (max < x) max = x;
        return max;
    }

    public static int min(int... arr) {
        int min = Integer.MAX_VALUE;
        for (int x : arr) if (min > x) min = x;
        return min;
    }

    public static long max(long... arr) {
        long max = Long.MIN_VALUE;
        for (long x : arr) if (max < x) max = x;
        return max;
    }

    public static long min(long... arr) {
        long min = Long.MAX_VALUE;
        for (long x : arr) if (min > x) min = x;
        return min;
    }

    public static double max(double... arr) {
        double max = -Double.MAX_VALUE;
        for (double x : arr) if (max < x) max = x;
        return max;
    }

    public static double min(double... arr) {
        double min = Double.MAX_VALUE;
        for (double x : arr) if (min > x) min = x;
        return min;
    }

    public static void runDesign(String methods, String parameters) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        methods = methods.replace("\"", "");
        String[] m = methods.substring(1, methods.length() - 1).split(",");
        String[] p = parameters.substring(1, methods.length() - 1).split(",");

        Class<?> clazz = Class.forName(m[0]);
        Object o = clazz.getDeclaredConstructors()[0].newInstance();
    }
}
