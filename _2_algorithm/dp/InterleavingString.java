//<p>给定三个字符串&nbsp;<code>s1</code>、<code>s2</code>、<code>s3</code>，请你帮忙验证&nbsp;<code>s3</code>&nbsp;是否是由&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code><em> </em><strong>交错 </strong>组成的。</p>
//
//<p>两个字符串 <code>s</code> 和 <code>t</code> <strong>交错</strong> 的定义与过程如下，其中每个字符串都会被分割成若干 <strong>非空</strong> 子字符串：</p>
//
//<ul> 
// <li><code>s = s<sub>1</sub> + s<sub>2</sub> + ... + s<sub>n</sub></code></li> 
// <li><code>t = t<sub>1</sub> + t<sub>2</sub> + ... + t<sub>m</sub></code></li> 
// <li><code>|n - m| &lt;= 1</code></li> 
// <li><strong>交错</strong> 是 <code>s<sub>1</sub> + t<sub>1</sub> + s<sub>2</sub> + t<sub>2</sub> + s<sub>3</sub> + t<sub>3</sub> + ...</code> 或者 <code>t<sub>1</sub> + s<sub>1</sub> + t<sub>2</sub> + s<sub>2</sub> + t<sub>3</sub> + s<sub>3</sub> + ...</code></li> 
//</ul>
//
//<p><strong>注意：</strong><code>a + b</code> 意味着字符串 <code>a</code> 和 <code>b</code> 连接。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/02/interleave.jpg" /> 
//<pre>
//<strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s1 = "", s2 = "", s3 = ""
//<strong>输出：</strong>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s1.length, s2.length &lt;= 100</code></li> 
// <li><code>0 &lt;= s3.length &lt;= 200</code></li> 
// <li><code>s1</code>、<code>s2</code>、和 <code>s3</code> 都由小写英文字母组成</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>您能否仅使用 <code>O(s2.length)</code> 额外的内存空间来解决它?</p>
//
//<div><li>👍 802</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

// 97.交错字符串
// 开题时间：2022-12-13 13:31:59
public class InterleavingString {
  public static void main(String[] args) {
    Solution solution = new InterleavingString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * dp[i][j] 表示 s1的前i个字符 和 s2的前j个字符 能否交错组成 s3的前i+j个字符
     * 状态转移：f[i][j] = (f[i - 1][j] && cs1[i - 1] == cs3[i + j - 1]) ||
     *                   (f[i][j - 1] && cs2[j - 1] == cs3[i + j - 1])
     * 初始化：需要初始化 f[i][0] 和 f[0][j]
     * 推导过程一定满足 s1 和 s2 是交错的
     */
    public boolean isInterleave9(String s1, String s2, String s3) {
      if (s1.length() + s2.length() != s3.length())
        return false;
      
      char[] cs1 = s1.toCharArray();
      char[] cs2 = s2.toCharArray();
      char[] cs3 = s3.toCharArray();
      int m = cs1.length + 1;
      int n = cs2.length + 1;
      boolean[][] f = new boolean[m][n];
      
      f[0][0] = true;
      for (int i = 1; i < m && cs1[i - 1] == cs3[i - 1]; i++) f[i][0] = true;
      for (int j = 1; j < n && cs2[j - 1] == cs3[j - 1]; j++) f[0][j] = true;
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          f[i][j] = (f[i - 1][j] && cs1[i - 1] == cs3[i + j - 1]) ||
              (f[i][j - 1] && cs2[j - 1] == cs3[i + j - 1]);
      
      return f[m - 1][n - 1];
    }
    
    // 滚动数组
    public boolean isInterleave(String s1, String s2, String s3) {
      if (s1.length() + s2.length() != s3.length())
        return false;
      
      char[] cs1 = s1.toCharArray();
      char[] cs2 = s2.toCharArray();
      char[] cs3 = s3.toCharArray();
      int m = cs1.length + 1;
      int n = cs2.length + 1;
      boolean[] f = new boolean[n];
      
      f[0] = true;
      
      for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
          if (i > 0) f[j] = f[j] && cs1[i - 1] == cs3[i + j - 1];
          if (j > 0) f[j] = f[j] || (f[j - 1] && cs2[j - 1] == cs3[i + j - 1]);
        }
      
      return f[n - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}