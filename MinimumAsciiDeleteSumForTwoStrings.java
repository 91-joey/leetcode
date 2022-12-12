//<p>给定两个字符串<code>s1</code>&nbsp;和&nbsp;<code>s2</code>，返回 <em>使两个字符串相等所需删除字符的&nbsp;<strong>ASCII&nbsp;</strong>值的最小和&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> s1 = "sea", s2 = "eat"
//<strong>输出:</strong> 231
//<strong>解释:</strong> 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
//在 "eat" 中删除 "t" 并将 116 加入总和。
//结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> s1 = "delete", s2 = "leet"
//<strong>输出:</strong> 403
//<strong>解释:</strong> 在 "delete" 中删除 "dee" 字符串变成 "let"，
//将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
//结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
//如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s1.length, s2.length &lt;= 1000</code></li> 
// <li><code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 304</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//712.两个字符串的最小ASCII删除和
//开题时间：2022-12-12 08:36:19
public class MinimumAsciiDeleteSumForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new MinimumAsciiDeleteSumForTwoStrings().new Solution();
        System.out.println(solution.minimumDeleteSum("sea", "eat"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //☆☆☆☆☆ 直接DP
        public int minimumDeleteSum9(String s1, String s2) {
            char[] cs1 = s1.toCharArray();
            char[] cs2 = s2.toCharArray();
            int m = cs1.length + 1;
            int n = cs2.length + 1;
            //状态定义：f[i][j]表示 s1的前i个字符 和 s2的前j个字符 的「使两个子字符串相等所需删除字符的 ASCII 值的最小和」
            int[][] f = new int[m][n];

            //初始化：一个字符串为空，则需要删除另一个字符串的所有字符
            for (int i = 1; i < m; i++)
                f[i][0] = f[i - 1][0] + cs1[i - 1];
            for (int i = 1; i < n; i++)
                f[0][i] = f[0][i - 1] + cs2[i - 1];

            //状态转移：
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (cs1[i - 1] == cs2[j - 1])
                        f[i][j] = f[i - 1][j - 1];
                    else
                        f[i][j] = Math.min(f[i][j - 1] + cs2[j - 1], f[i - 1][j] + cs1[i - 1]);

            return f[m - 1][n - 1];
        }

        //LCS 曲线救国
        public int minimumDeleteSum(String s1, String s2) {
            char[] cs1 = s1.toCharArray();
            char[] cs2 = s2.toCharArray();
            int m = cs1.length + 1;
            int n = cs2.length + 1;
            int[][] f = new int[m][n];

            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (cs1[i - 1] == cs2[j - 1])
                        f[i][j] = f[i - 1][j - 1] + cs1[i - 1];
                    else
                        f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);


            return s1.chars().sum()
                    + s2.chars().sum()
                    - 2 * f[m - 1][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}