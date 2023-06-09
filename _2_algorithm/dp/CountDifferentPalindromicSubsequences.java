//<p>给定一个字符串 s，返回 <em><code>s</code>&nbsp;中不同的非空「回文子序列」个数 。</em></p>
//
//<p>通过从 s&nbsp;中删除 0 个或多个字符来获得子序列。</p>
//
//<p>如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。</p>
//
//<p>如果有某个 <code>i</code> , 满足&nbsp;<code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code><sub>&nbsp;</sub>，则两个序列&nbsp;<code>a<sub>1</sub>, a<sub>2</sub>, ...</code>&nbsp;和&nbsp;<code>b<sub>1</sub>, b<sub>2</sub>, ...</code>&nbsp;不同。</p>
//
//<p><strong>注意：</strong></p>
//
//<ul> 
// <li>结果可能很大，你需要对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;取模 。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = 'bccb'
//<strong>输出：</strong>6
//<strong>解释：</strong>6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
// 注意：'bcb' 虽然出现两次但仅计数一次。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
//<strong>输出：</strong>104860361
//<strong>解释：</strong>共有 3104860382 个不同的非空回文子序列，104860361 对 10<sup>9</sup> + 7 取模后的值。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s[i]</code>&nbsp;仅包含&nbsp;<code>'a'</code>,&nbsp;<code>'b'</code>,&nbsp;<code>'c'</code>&nbsp;或&nbsp;<code>'d'</code>&nbsp;</li> 
//</ul>
//
//<div><li>👍 309</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 730.统计不同回文子序列
// 开题时间：2022-12-21 13:54:45
public class CountDifferentPalindromicSubsequences {
  public static void main(String[] args) {
    Solution solution = new CountDifferentPalindromicSubsequences().new Solution();
    System.out.println(solution.countPalindromicSubsequences("bcbacbabdcbcbdcbddcaaccdcbbcdbcabbcdddadaadddbdbbbdacbabaabdddcaccccdccdbabcddbdcccabccbbcdbcdbdaada"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int MOD = 10_0000_0007;
    
    public int countPalindromicSubsequences(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      int[][] f = new int[n][n];
      
      for (int i = n - 1; i >= 0; i--) {
        f[i][i] = 1;
        
        int[] cnt = new int[4];
        int[] first = new int[4];
        int[] last = new int[4];
        
        for (int j = i + 1; j < n; j++) {
          if (j > i + 1) {
            int idx = cs[j - 1] - 'a';
            cnt[idx]++;
            if (first[idx] == 0)
              first[idx] = j - 1;
            else
              last[idx] = j - 1;
          }
          
          f[i][j] = cs[i] == cs[j] ?
              2 * f[i + 1][j - 1] + switch (cnt[cs[i] - 'a']) {
                case 0 -> 2;
                case 1 -> 1;
                default -> -f[first[cs[i] - 'a'] + 1][last[cs[i] - 'a'] - 1];
              } :
              f[i + 1][j] + f[i][j - 1] - f[i + 1][j - 1];
          
          f[i][j] = Math.floorMod(f[i][j], MOD);
        }
      }
      
      return f[0][n - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}