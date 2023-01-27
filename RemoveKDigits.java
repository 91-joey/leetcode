package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 402.移掉 K 位数字 <br>
 * 开题时间：2023-01-27 15:36:18
 */
public class RemoveKDigits {
  public static void main(String[] args) {
    Solution solution = new RemoveKDigits().new Solution();
    System.out.println(solution.removeKdigits("10001", 4));
    // System.out.println(solution.removeKdigits("1432219", 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 贪心 + TreeSet （高位优先更小）
    public String removeKdigits9(String num, int k) {
      int n = num.length();
      if (n == k) {
        return "0";
      }
      
      TreeSet<Integer>[] val2mins = new TreeSet[10];
      Arrays.setAll(val2mins, i -> new TreeSet<>());
      
      for (int i = 0; i < n; i++) {
        val2mins[num.charAt(i) - '0'].add(i);
      }
      
      StringBuilder sb = new StringBuilder(n - k);
      for (int i = 0, start = -1, end = k; i < n - k; i++, end++) {
        for (int j = 0; j < 10; j++) {
          Integer higher = val2mins[j].higher(start);
          if (higher != null && higher <= end) {
            if (!(j == 0 && sb.isEmpty())) {
              sb.append(j);
            }
            start = higher;
            break;
          }
        }
      }
      
      return sb.isEmpty() ? "0" : sb.toString();
    }
    
    // ☆☆☆☆☆ 贪心 + 单调递增栈
    public String removeKdigits(String num, int k) {
      int n = num.length();
      if (n == k) {
        return "0";
      }
      
      StringBuilder sb = new StringBuilder(n - k);
      for (int i = 0; i < n; i++) {
        char c = num.charAt(i);
        while (k > 0 && !sb.isEmpty() && sb.charAt(sb.length() - 1) > c) {
          // 移除一个更大的数
          sb.deleteCharAt(sb.length() - 1);
          k--;
        }
        // 前导零不作处理
        if (!(c == '0' && sb.isEmpty())) {
          sb.append(c);
        }
      }
      
      // 有前导零时,sb.length() - k < 0
      sb.delete(Math.max(0, sb.length() - k), sb.length());
      return sb.isEmpty()
          ? "0"
          : sb.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}