package org.example.leetcode.problems._3_common.tool;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;
import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 用来反序列化常见的Leetcode输入用例.
 * 便于本地化测试
 *
 * @author Sennri
 */
public class DeserializeUtil {
  /**
   * 从String中反序列化建立int[][]的数组.
   */
  public static int[][] deserialize2dArray(String s) {
    s = s.trim();
    // 去除前后中括号, 当然，效率起见也能在下面从 1 遍历到 len - 2
    s = s.substring(1, s.length() - 1);
    char[] chars = s.toCharArray();
    // avoid Integer.MIN_VALUE;
    long temp = 0;
    List<List<Integer>> rows = new ArrayList<>();
    List<Integer> row = new ArrayList<>();
    boolean preIsNum = false;
    boolean minus = false;
    for (char c : chars) {
      if (c == ']') {
        if (minus) {
          row.add((int) -temp);
        } else {
          row.add((int) temp);
        }
        temp = 0;
        preIsNum = false;
        minus = false;
        rows.add(row);
        row = new ArrayList<>();
      } else if (c == '[') {
        preIsNum = false;
      } else {
        if (c != ' ') {
          if (c == ',') {
            if (preIsNum) {
              if (minus) {
                row.add((int) -temp);
              } else {
                row.add((int) temp);
              }
              temp = 0;
              minus = false;
            }
            preIsNum = false;
          } else if (c == '-') {
            minus = true;
          } else {
            temp = temp * 10 + (c - '0');
            preIsNum = true;
          }
        }
      }
    }
    int size = rows.size();
    int[][] res = new int[size][];
    for (int i = 0; i < size; i++) {
      res[i] = rows.get(i).stream().mapToInt(Integer::intValue).toArray();
    }
    return res;
  }
  
  /**
   * 从String中反序列化建立int[]数组.
   */
  public static int[] deserializeArray(String s) {
    s = s.trim();
    // 去除前后括号
    s = s.substring(1, s.length() - 1);
    String[] ss = COMMA_PATTERN.split(s);
    int len = ss.length;
    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      res[i] = Integer.parseInt(ss[i].trim());
    }
    return res;
  }
  
  /**
   * 建立链表.
   */
  public static ListNode deserializeListNode(int[] nums) {
    int len = nums.length;
    ListNode res = null;
    for (int index = len - 1; index >= 0; index--) {
      res = new ListNode(nums[index], res);
    }
    return res;
  }
  
  /**
   * 从String中反序列化链表.
   */
  public static ListNode deserializeListNode(String s) {
    int[] array = deserializeArray(s);
    return deserializeListNode(array);
  }
  
  
  /**
   * 反序列化树.
   */
  public static TreeNode deserializeTree(String data) {
    String[] strings = data.substring(1, data.length() - 1).split(",");
    // do sth here
    int len = strings.length;
    String first = strings[0].trim();
    if ("".equals(first) || "null".equals(first)) {
      return null;
    }
    Deque<TreeNode> deque = new ArrayDeque<>();
    TreeNode head = new TreeNode(Integer.parseInt(strings[0].trim()));
    deque.offer(head);
    boolean isLeft = true;
    for (int i = 1; i < len; i++) {
      String s = strings[i].trim();
      if ("null".equals(s)) {
        if (!isLeft) {
          deque.poll();
        }
      } else {
        TreeNode node = new TreeNode(Integer.parseInt(s));
        if (isLeft) {
          deque.peek().left = node;
        } else {
          deque.poll().right = node;
        }
        deque.offer(node);
      }
      isLeft = !isLeft;
    }
    return head;
  }
  
  final static Pattern COMMA_PATTERN = Pattern.compile(",");
  
  /**
   * 序列化树.
   */
  public static String serializeTree(TreeNode root) {
    if (root == null) {
      return "[]";
    }
    // ArrayDeque不支持null值
    Deque<TreeNode> layer = new LinkedList<>();
    StringBuilder sb = new StringBuilder("[");
    layer.offer(root);
    int size = layer.size();
    while (size > 0) {
      for (int i = 0; i < size; i++) {
        TreeNode node = layer.poll();
        if (node == null) {
          sb.append("null").append(",");
        } else {
          sb.append(node.val).append(",");
          layer.offer(node.left);
          layer.offer(node.right);
        }
      }
      size = layer.size();
    }
    int sbLen = sb.length() - 1;
    // remove ','
    sb.deleteCharAt(sbLen);
    sbLen--;
    while (sb.charAt(sbLen) == 'l') {
      sb.delete(sbLen - 4, sbLen + 1);
      sbLen -= 5;
    }
    return sb.append(']').toString();
  }
}
