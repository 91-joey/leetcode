//<p>给你一个字符串 <code>s</code>，找到 <code>s</code> 中最长的回文子串。</p>
//
//<p>如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "babad"
//<strong>输出：</strong>"bab"
//<strong>解释：</strong>"aba" 同样是符合题意的答案。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "cbbd"
//<strong>输出：</strong>"bb"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s</code> 仅由数字和英文字母组成</li> 
//</ul>
//
//<div><li>👍 5984</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 5.最长回文子串
// 开题时间：2022-12-20 12:18:01
public class LongestPalindromicSubstring {
  public static void main(String[] args) {
    Solution solution = new LongestPalindromicSubstring().new Solution();
    System.out.println(solution.longestPalindrome("babad"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力 n^3    1
    public String longestPalindrome1(String s) {
      // 1.确定子串长度
      for (int length = s.length(); length >= 1; length--)
        // 2.确定子串起点
        for (int i = 0; i <= s.length() - length; i++) {
          String str = s.substring(i, i + length);
          // 3.判断是否回文
          for (int j = 0; j < length / 2; j++) {
            if (str.charAt(j) != str.charAt(length - 1 - j))
              break;
            if (j == length / 2 - 1)
              return str;
          }
        }
      return s.substring(0, 1);
    }
    
    // DP
    public String longestPalindrome9(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      boolean[][] f = new boolean[n][n];
      
      int max = 1;
      int start = 0;
      for (int i = n - 2; i >= 0; i--) {
        f[i][i] = true;
        for (int j = i + 1; j < n; j++) {
          f[i][j] = (i + 1 > j - 1 || f[i + 1][j - 1]) && cs[i] == cs[j];
          if (f[i][j] && max < j - i + 1) {
            max = j - i + 1;
            start = i;
          }
        }
      }
      
      return s.substring(start, start + max);
    }
    
    // DP（滚动数组）
    public String longestPalindrome8(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      boolean[] f = new boolean[n];
      
      int max = 1;
      int start = 0;
      for (int i = n - 2; i >= 0; i--)
        for (int j = n - 1; j >= i + 1; j--) {
          f[j] = (j - i <= 2 || f[j - 1]) && cs[i] == cs[j];
          if (f[j] && max < j - i + 1) {
            max = j - i + 1;
            start = i;
          }
        }
      
      return s.substring(start, start + max);
    }
    
    //☆☆☆☆ 中心扩散法
    public String longestPalindrome(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      
      int max = 1;
      int start = 0;
      for (int i = 0; i < n - 1; i++) {
        int lenOdd = centerSpread(cs, i, i);
        int lenEven = centerSpread(cs, i, i + 1);
        lenOdd = Math.max(lenOdd, lenEven);
        if (max < lenOdd) {
          max = lenOdd;
          start = i - (lenOdd - 1) / 2;
        }
      }
      
      return s.substring(start, start + max);
    }
    
    private int centerSpread(char[] cs, int l, int r) {
      while (0 <= l && r < cs.length && cs[l] == cs[r]) {
        l--;
        r++;
      }
      return r - l - 1;
    }
    
    // todo 高阶内容：Manacher算法，面试不需要掌握
  }
  // leetcode submit region end(Prohibit modification and deletion)
}