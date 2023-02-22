package _9_contest.history.week332;

// 6357. Subsequence With the Minimum Score
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution.minimumScore("abacaba", "bzaa"));
    System.out.println(solution.minimumScore("dabbbeddeabbaccecaee", "bcbbaabdbebecbebded"));
    System.out.println(solution.minimumScore("cde", "xyz"));
  }
  
  class Solution {
    
    public int minimumScoreX(String s, String t) {
      int lS = 0;
      int lT = 0;
      int last = -1;
      while (lS < s.length() && lT < t.length()) {
        if (s.charAt(lS) == t.charAt(lT)) {
          lT++;
          last = lS;
        }
        lS++;
      }
      
      int rS = s.length();
      int rT = t.length();
      while (rS >= last + 2 && rT >= lT + 1) {
        if (s.charAt(rS - 1) == t.charAt(rT - 1)) {
          rT--;
        }
        rS--;
      }
      
      return rT - lT;
    }
    
    // 前后缀分解 + 双指针
    public int minimumScore9(String S, String T) {
      int n = S.length();
      int m = T.length();
      char[] s = S.toCharArray();
      char[] t = T.toCharArray();
      // 后缀和：suf[i] 表示 s[i:] 能匹配的最大 t 后缀长度
      int[] suf = new int[n + 1];
      for (int i = n - 1, j = m - 1; i >= 0; i--) {
        suf[i] = suf[i + 1];
        if (j >= 0 && s[i] == t[j]) {
          suf[i]++;
          j--;
        }
      }
      
      // 前缀和
      int ans = m - suf[0];
      for (int i = 0, j = 0, pre = 0; i < n; i++) {
        if (j < m && s[i] == t[j]) {
          pre++;
          j++;
        }
        // 注意可能会有负值
        ans = Math.min(ans, Math.max(m - pre - suf[i + 1], 0));
      }
      
      return ans;
    }
    
    
    // ☆☆☆☆☆ 前后缀分解 + 双指针（优化）
    public int minimumScore(String S, String T) {
      int n = S.length();
      int m = T.length();
      char[] s = S.toCharArray();
      char[] t = T.toCharArray();
      // 后缀和：suf[i] 表示 s[i:] 能匹配的最大 t 后缀的开始坐标
      int[] suf = new int[n + 1];
      for (int i = n - 1, j = m - 1; i >= 0; i--) {
        if (j >= 0 && s[i] == t[j]) {
          j--;
        }
        suf[i] = j + 1;
      }
      
      // 前缀和：pre[i] 表示 s[:i] 能匹配的最大 t 前缀的结束坐标，此处因为可以边更新前缀和边计算结果，所以可以省略数组
      int ans = suf[0];
      if (ans == 0) {
        return 0;
      }
      for (int i = 0, j = 0; i < n; i++) {
        if (s[i] == t[j]) { // 注意 j 不会等于 m，因为上面 suf[0]>0 表示 t 不是 s 的子序列
          ans = Math.min(ans, suf[i + 1] - ++j);
        }
      }
      
      return ans;
    }
  }
}
