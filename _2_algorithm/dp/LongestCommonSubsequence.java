//<p>给定两个字符串&nbsp;<code>text1</code> 和&nbsp;<code>text2</code>，返回这两个字符串的最长 <strong>公共子序列</strong> 的长度。如果不存在 <strong>公共子序列</strong> ，返回 <code>0</code> 。</p>
//
//<p>一个字符串的&nbsp;<strong>子序列</strong><em>&nbsp;</em>是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。</p>
//
//<ul> 
// <li>例如，<code>"ace"</code> 是 <code>"abcde"</code> 的子序列，但 <code>"aec"</code> 不是 <code>"abcde"</code> 的子序列。</li> 
//</ul>
//
//<p>两个字符串的 <strong>公共子序列</strong> 是这两个字符串所共同拥有的子序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>text1 = "abcde", text2 = "ace" 
//<strong>输出：</strong>3  
//<strong>解释：</strong>最长公共子序列是 "ace" ，它的长度为 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>text1 = "abc", text2 = "abc"
//<strong>输出：</strong>3
//<strong>解释：</strong>最长公共子序列是 "abc" ，它的长度为 3 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>text1 = "abc", text2 = "def"
//<strong>输出：</strong>0
//<strong>解释：</strong>两个字符串没有公共子序列，返回 0 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= text1.length, text2.length &lt;= 1000</code></li> 
// <li><code>text1</code> 和&nbsp;<code>text2</code> 仅由小写英文字符组成。</li> 
//</ul>
//
//<div><li>👍 1177</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//1143.最长公共子序列
//开题时间：2022-12-06 16:23:26
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] cs1 = text1.toCharArray();
            char[] cs2 = text2.toCharArray();
            int m = cs1.length + 1;
            int n = cs2.length + 1;
            int[][] f = new int[m][n];

            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (cs1[i - 1] == cs2[j - 1])
                        f[i][j] = f[i - 1][j - 1] + 1;
                    else
                        f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);

            return f[m - 1][n - 1];
        }

        /*
         * f[i][j]=max(f[i][j-1],f[i-1][j],f[i-1][j-1]+s[i]==s[j]?1:0)
         */
        public int longestCommonSubsequence9(String text1, String text2) {
            char[] cs1 = text1.toCharArray();
            char[] cs2 = text2.toCharArray();
            int m = cs1.length + 1;
            int n = cs2.length + 1;
            int[][] f = new int[m][n];

            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    f[i][j] = Math.max(Math.max(f[i][j - 1], f[i - 1][j]), f[i - 1][j - 1] + (cs1[i - 1] == cs2[j - 1] ? 1 : 0));

            return f[m - 1][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}