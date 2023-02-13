package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

/**
 * 1234.替换子串得到平衡字符串 <br>
 * 开题时间：2023-02-13 08:47:34
 */
public class ReplaceTheSubstringForBalancedString {
  public static void main(String[] args) {
    Solution solution = new ReplaceTheSubstringForBalancedString().new Solution();
    System.out.println(solution.balancedString("QQQQ"));
    // System.out.println(solution.balancedString("QWER"));
    // System.out.println(solution.balancedString("WQWRQQQW"));
    // System.out.println(solution.balancedString("QQQW"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 滑窗（同向双指针）
    public int balancedString9(String s) {
      int n = s.length();
      int m = 4;
      int t = n / m;
      int[] arr = new int[n];
      
      int[] cnt = new int[m];
      // 预处理字符映射
      for (int i = 0; i < n; i++) {
        int val = switch (s.charAt(i)) {
          case 'Q' -> 0;
          case 'W' -> 1;
          case 'E' -> 2;
          default -> 3;
        };
        arr[i] = val;
        cnt[val]++;
      }
      
      int i = 0;
      while (i < m && cnt[i] == t) {
        i++;
      }
      // 已经符合要求
      if (i == m) {
        return 0;
      }
      
      int ans = n - 1;
      for (int l = 0, r = 0; r < n; ) {
        // 先右指针尽量往右滑动
        while (r < n && !validCnts9(cnt, t)) {
          cnt[arr[r++]]--;
        }
        // 再左指针尽量往右滑动
        while (l < r && validCnts9(cnt, t)) {
          cnt[arr[l++]]++;
        }
        // 更新答案
        ans = Math.min(ans, r - l + 1);
      }
      
      return ans;
    }
    
    private boolean validCnts9(int[] cnt, int t) {
      for (int i = 0; i < cnt.length; i++) {
        if (cnt[i] > t) {
          return false;
        }
      }
      return true;
    }
    
    // ☆☆☆☆☆ 滑窗（枚举右端点）
    public int balancedString(String S) {
      int n = S.length();
      int m = 4;
      int t = n / m;
      int[] cnt = new int['X'];
      char[] s = S.toCharArray();
      // 计数
      for (char c : s) {
        cnt[c]++;
      }
  
      // 已经符合要求
      if (validCnts(cnt, t)) {
        return 0;
      }
      
      int ans = n - 1;
      for (int l = 0, r = 0; r < n; ) {
        cnt[s[r++]]--;
        while (validCnts(cnt, t)) {
          cnt[s[l++]]++;
          ans = Math.min(ans, r - l + 1);
        }
      }
      
      return ans;
    }
  
    /**
     * 判断待替换子串之外的任意字符的出现次数是否都不超过 t
     * @param t n / 4
     */
    private boolean validCnts(int[] cnt, int t) {
      return cnt['Q'] <= t &&
          cnt['W'] <= t &&
          cnt['E'] <= t &&
          cnt['R'] <= t;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}