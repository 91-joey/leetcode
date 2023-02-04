//<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个字符规律&nbsp;<code>p</code>，请你来实现一个支持 <code>'.'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的正则表达式匹配。</p>
//
//<ul> 
// <li><code>'.'</code> 匹配任意单个字符</li> 
// <li><code>'*'</code> 匹配零个或多个前面的那一个元素</li> 
//</ul>
//
//<p>所谓匹配，是要涵盖&nbsp;<strong>整个&nbsp;</strong>字符串&nbsp;<code>s</code>的，而不是部分字符串。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aa", p = "a"
//<strong>输出：</strong>false
//<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aa", p = "a*"
//<strong>输出：</strong>true
//<strong>解释：</strong>因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ab", p = ".*"
//<strong>输出：</strong>true
//<strong>解释：</strong>".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li> 
// <li><code>1 &lt;= p.length&nbsp;&lt;= 30</code></li> 
// <li><code>s</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li> 
// <li><code>p</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>.</code>&nbsp;和&nbsp;<code>*</code>。</li> 
// <li>保证每次出现字符&nbsp;<code>*</code> 时，前面都匹配到有效的字符</li> 
//</ul>
//
//<div><li>👍 3325</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

// 10.正则表达式匹配
// 开题时间：2022-11-19 10:49:27
public class RegularExpressionMatching {
  public static void main(String[] args) {
    Solution solution = new RegularExpressionMatching().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isMatch9(String s, String p) {
      return s.matches(p);
    }
    
    /*
     * dp
     * 关键点：2个字符串要从右往左匹配
     * 状态转移：
     *      s[i]=p[j]||p[j]='.':
     *          true:f[i][j]=f[i-1][j-1]
     *          false:
     *              p[j]!='*':false
     *              p[j]=='*':
     *                  s[i]=p[j-1]||p[j-1]='.':
     *                      '*'消了0次：f[i][j]=f[i][j-2]
     *                      '*'消了1次：f[i][j]=f[i-1][j-2]
     *                      '*'消了2次以上：f[i][j]=f[i-1][j]
     *                  s[i]!=p[j-1]:f[i][j]=f[i][j-2]
     *  初始化：
     *      p[j]=='*',  f[0][j]=f[0][j-2]   ,f[0][0]=true
     *      p[j]!='*',  f[0][j]=false
     *      f[i][0]=false
     *  结果：
     *      f[m-1][n-1]
     */
    public boolean isMatch(String s, String p) {
      char[] cS = s.toCharArray();
      char[] cP = p.toCharArray();
      int m = cS.length + 1;
      int n = cP.length + 1;
      boolean[][] f = new boolean[m][n];
      
      f[0][0] = true;
      for (int j = 2; j < n && cP[j - 1] == '*'; j += 2)
        f[0][j] = true;
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++) {
          char a = cS[i - 1];
          char b = cP[j - 1];
          if (a == b || b == '.')
            f[i][j] = f[i - 1][j - 1];
          else if (b == '*')
            f[i][j] = a == cP[j - 2] || cP[j - 2] == '.' ?
                //                                f[i][j - 2] || f[i - 1][j - 2] || f[i - 1][j] :
                f[i][j - 2] || f[i - 1][j] :
                f[i][j - 2];
        }
      
      return f[m - 1][n - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}