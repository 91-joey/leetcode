package org.example.leetcode.problems._3_common.tool;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;
import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
  public static final int INF = 0x3f3f3f3f;
  public static final int[] DIRS = {1, 0, -1, 0, 1};
  public static final int[] DIRS8 = {1, 0, -1, 0, 1, 1, -1, -1, 1};
  public static final char EMPTY = '.';
  public static final char WALL = '+';
  
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
  
  public static boolean isVowel(char c) {
    return switch (c) {
      case 'a', 'e', 'i', 'o', 'u',
          'A', 'E', 'I', 'O', 'U' -> true;
      default -> false;
    };
  }
  
  public static boolean isLowerCaseVowel(char c) {
    return switch (c) {
      case 'a', 'e', 'i', 'o', 'u' -> true;
      default -> false;
    };
  }
  
  public static boolean isUpperCaseVowel(char c) {
    return switch (c) {
      case 'A', 'E', 'I', 'O', 'U' -> true;
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
    while (a != 0) {
      int tmp = a;
      a = b % a;
      b = tmp;
    }
    return b;
  }
  
  public static int lcm(int a, int b) {
    return a * b / gcd(a, b);
  }
  
  /**
   * 返回 两数之合 = 目标值 的索引数组
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
  
  // region binary tree traversal
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
  // endregion
  
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
  
  public static int manhattan(int x0, int y0, int x1, int y1) {
    return Math.abs(x0 - x1) + Math.abs(y0 - y1);
  }
  
  public static int manhattan(int[] p0, int[] p1) {
    return manhattan(p0[0], p0[1], p1[0], p1[1]);
  }
  
  public static int manhattan(int[][] points, int i, int j) {
    return manhattan(points[i], points[j]);
  }
  
  public void dfs(char[][] arr, int r, int c) {
    for (int i = 0; i < 4; i++) {
      int rNew = r + DIRS[i];
      int cNew = c + DIRS[i + 1];
      if (0 <= rNew && rNew < arr.length && 0 <= cNew && cNew < arr[0].length &&
          arr[rNew][cNew] == '1')
        dfs(arr, rNew, cNew);
    }
  }
  
  private static boolean isOnBorder(int r, int c, int m, int n) {
    return 0 == r || r == m - 1 || 0 == c || c == n - 1;
  }
  
  
  public static int bfs(int[][] arr, int[] entrance) {
    int m = arr.length;
    int n = arr[0].length;
    Queue<int[]> q = new LinkedList<>();
    q.offer(entrance);
    boolean[][] vis = new boolean[m][n];
    vis[entrance[0]][entrance[1]] = true;
    
    int step = 1;
    while (!q.isEmpty()) {
      for (int i = q.size(); i > 0; i--) {
        int[] poll = q.poll();
        int r = poll[0];
        int c = poll[1];
        
        for (int j = 0; j < 4; j++) {
          int nr = r + DIRS[j];
          int nc = c + DIRS[j + 1];
          if (0 <= nr && nr < m && 0 <= nc && nc < n
              && !vis[nr][nc]) {
            if (arr[nr][nc] == 666) {
              return step;
            }
            vis[nr][nc] = true;
            q.offer(new int[]{nr, nc});
          }
        }
      }
      step++;
    }
    
    return -1;
  }
  
  public <T> int doubleBfs(T source, T target, int notFound) {
    if (target.equals(source))
      return 0;
    
    Queue<T> q1 = new LinkedList<>(), q2 = new LinkedList<>();
    Set<T> vis1 = new HashSet<>(), vis2 = new HashSet<>();
    q1.offer(source);
    vis1.add(source);
    q2.offer(target);
    vis2.add(target);
    int step1 = 0, step2 = 0;
    while (!q1.isEmpty() && !q2.isEmpty()) {
      boolean reachTarget;
      if (q1.size() <= q2.size()) {
        reachTarget = bfs(q1, vis1, vis2);
        step1++;
      } else {
        reachTarget = bfs(q2, vis2, vis1);
        step2++;
      }
      if (reachTarget)
        return step1 + step2;
    }
    
    return notFound;
  }
  
  public <T> boolean bfs(Queue<T> q, Set<T> cur, Set<T> other) {
    for (int i = q.size(); i > 0; i--) {
      T poll = q.poll();
      
      for (T next : getNexts(poll, cur))
        if (other.contains(next))
          return true;
        else {
          q.offer(next);
          cur.add(next);
        }
    }
    return false;
  }
  
  private <T> List<T> getNexts(T poll, Set<T> vis) {
    ArrayList<T> ans = new ArrayList<>();
    
    T next = null;
    if (!vis.contains(next))
      ans.add(next);
    
    return ans;
  }
  
  /**
   * 反转数字（十进制）
   */
  public int reverse(int x) {
    int rev = 0;
    for (int tmp = x; tmp != 0; tmp /= 10)
      rev = rev * 10 + tmp % 10;
    return rev;
  }
  
  /**
   * 求组合数：C_n^m，即 n 个数里取 m 个的组合数
   */
  public static long comb(int n, int m) {
    m = Math.min(m, n - m);
    long comb = 1;
    for (int i = 1, j = n - m + 1; i <= m; i++, j++)
      comb = comb * j / i;
    return comb;
  }
  
  /**
   * 阶乘
   */
  public static long factorial(int x) {
    long ans = 1;
    for (int i = 2; i <= x; i++)
      ans *= i;
    return ans;
  }
  
  public static boolean isPrime(int n) {
    for (int i = 2; i * i <= n; i++)
      if (n % i == 0)
        return false;
    return true;
  }
  
  /**
   * 数组向右轮转 k 个位置
   */
  public static void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n;
    reverse(nums, 0, n - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);
  }
  
  /**
   * 翻转数组
   */
  public static void reverse(int[] nums, int l, int r) {
    while (l < r) {
      int tmp = nums[l];
      nums[l++] = nums[r];
      nums[r--] = tmp;
    }
  }
  
  /**
   * 最大不重叠区间数（端点值相等算重叠）
   */
  public static int maxNonOverlap(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
    int ans = 1;
    for (int i = 1, t = intervals[0][1]; i < intervals.length; i++)
      if (t < intervals[i][0]) {
        ans++;
        t = intervals[i][1];
      }
    return ans;
  }
  
  public static void reverse(char[] cs, int l, int r) {
    while (l < r) {
      char tmp = cs[l];
      cs[l++] = cs[r];
      cs[r--] = tmp;
    }
  }
  
  public static void reverse(char[] cs) {
    reverse(cs, 0, cs.length - 1);
  }
  
  public static <T> void reverse(T[] arr, int l, int r) {
    while (l < r) {
      T tmp = arr[l];
      arr[l++] = arr[r];
      arr[r--] = tmp;
    }
  }
  
  public static <T> void reverse(T[] arr) {
    reverse(arr, 0, arr.length - 1);
  }
  
  public int getSquareDistance(int x1, int y1, int x2, int y2) {
    int disX = x1 - x2;
    int disY = y1 - y2;
    return disX * disX + disY * disY;
  }
  
  public int getSquareDistance(int[] a, int[] b) {
    return getSquareDistance(a[0], a[1], b[0], b[1]);
  }
  
  public double getDistance(int[] a, int[] b) {
    return Math.sqrt(getSquareDistance(a, b));
  }
  
  public static boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }
  
  /**
   * 判断整数是否数位单调递增.
   */
  public static boolean isMonotoneIncreasing(int n) {
    int next = 10;
    for (int i = n; i != 0; i /= 10) {
      int digit = i % 10;
      if (digit > next) {
        return false;
      }
      next = digit;
    }
    return true;
  }
  
  /**
   * 克隆二叉树.
   */
  public static TreeNode cloneTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    return new TreeNode(root.val, cloneTree(root.left), cloneTree(root.right));
  }
}
