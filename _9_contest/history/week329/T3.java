package org.example.leetcode.problems._9_contest.history.week329;

// 2546. Apply Bitwise Operations to Make Strings Equal
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution.makeStringsEqual("00100"
        , "00000"));
    System.out.println(solution.makeStringsEqual("0100"
        , "1100"));
  }
  
  class Solution {
    public boolean makeStringsEqualX(String s, String target) {
      if (s.equals(target))
        return true;
      
      if (s.equals("00") && target.equals("11"))
        return false;
      if (target.equals("00") && s.equals("11"))
        return false;
      
      int n = s.length();
      int cnt0 = 0, diff = 0, cnt02 = 0;
      boolean one = false;
      for (int i = 0; i < n; i++) {
        char c = s.charAt(i);
        if (c == '0')
          cnt0++;
        if (target.charAt(i) == '0')
          cnt02++;
        if (c != target.charAt(i)) {
          diff++;
          if (c == '1')
            one = true;
        }
      }
      if (cnt0 == n)
        return false;
      
      if (cnt0 == n - 1 && diff == 1 && one)
        return false;
      return cnt0 + 1 < cnt02;
    }
    
    // 脑筋急转弯：使 `s` 等于 `target` 的充要条件：两个字符串中都有 1 或者都没有 1
    public boolean makeStringsEqual9(String s, String target) {
      return hasOne(s) == hasOne(target);
    }
    
    /*
     * 分类讨论
     * ##### 我的理解是：`1` 其实是一个很好的辅助，她可以辅助别人进行位取反操作、而自身保持不变。
     * - 如果有「场外辅助」（`s[i] = target[i] = 1`），那么我们直接全用她来将 `s` 中与 `target` 不同的位取反，就能使 `s` 等于 `target`
     * - 如果没有「场外辅助」，那么我们只好使用（`s[i] = 1` `target[i] = 0` 这种情况）或者创造（`s[i] = 0` `target[i] = 1` 这种情况）「场内辅助」，不管是使用和创造都是一样的。区别在于：
     *   - 使用的话，先辅助别人，最后辅助也需要取反，这时候就需要借助那些「在她辅助下，变为 `1` 的字符，也就是原来是 `s[j] = 0` `target[j] = 1` 的情况」
     *   - 创造的话，需要借助那些 `s[j] = 1` `target[j] = 0` 的字符，自己先取反，再辅助别人。
     * ##### 总结：可以使 `s` 等于 `target` 的情况一共有如下 3 种
     * - 两字符串相等
     * - 两字符串不等：
     *   - 有「场外辅助」（`s[i] = target[i] = 1`）
     *   - 无「场外辅助」，同时存在以下两种情况：
     *     - `s[i] = 1` `target[i] = 0`
     *     - `s[j] = 0` `target[j] = 1`
     * ##### 具体实现上，我在楼主的基础上作了如下优化：
     * - 没有必要计数，只需要知道每种变化是否存在
     * - 字符串相等的情况，可以在统计变换后得出
     * - 可以使 `s` 等于 `target` 的情况一共有如下 3 种，可以合并在一起输出
     *   - 两字符串相等，即 `!(exists[0][1] || exists[1][0])`
     *   - 有「场外辅助」，即 `exists[1][1]`
     *   - 无「场外辅助」，即 `exists[0][1] && exists[1][0]`
     */
    public boolean makeStringsEqual(String s, String target) {
      boolean[][] exists = new boolean[2][2];
      for (int i = 0; i < s.length(); i++)
        exists[s.charAt(i) - '0'][target.charAt(i) - '0'] = true;
      
      return !(exists[0][1] || exists[1][0])
          || exists[1][1]
          || (exists[0][1] && exists[1][0]);
    }
    
    private boolean hasOne(String s) {
      //            for (int i = 0; i < s.length(); i++)
      //                if (s.charAt(i) == '1')
      //                    return true;
      //            return false;
      return s.chars().anyMatch(c -> c == '1');
    }
  }
}
