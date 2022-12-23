//<p>给你一个字符串 <code>s</code> ，找出其中最长的回文子序列，并返回该序列的长度。</p>
//
//<p>子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "bbbab"
//<strong>输出：</strong>4
//<strong>解释：</strong>一个可能的最长回文子序列为 "bbbb" 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "cbbd"
//<strong>输出：</strong>2
//<strong>解释：</strong>一个可能的最长回文子序列为 "bb" 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s</code> 仅由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 917</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//516.最长回文子序列
//开题时间：2022-12-20 15:55:23
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DP
        public int longestPalindromeSubseq9(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            int[][] f = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
//                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
//                    if (cs[i] == cs[j])
//                        f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
                    if (cs[i] == cs[j])
                        f[i][j] = f[i + 1][j - 1] + 2;
                    else
                        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }

            return f[0][n - 1];
        }

        //☆☆☆☆☆ DP（滚动数组）
        public int longestPalindromeSubseq(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            int[] f = new int[n];

            for (int i = n - 1; i >= 0; i--) {
                f[i] = 1;
                for (int j = i + 1, leftDown = 0; j < n; j++) {
                    int tmp = f[j];
                    if (cs[i] == cs[j])
                        f[j] = leftDown + 2;
                    else
                        f[j] = Math.max(f[j], f[j - 1]);
                    leftDown = tmp;
                }
            }

            return f[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}