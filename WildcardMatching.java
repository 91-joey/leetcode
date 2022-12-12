//<p>给定一个字符串&nbsp;(<code>s</code>) 和一个字符模式&nbsp;(<code>p</code>) ，实现一个支持&nbsp;<code>'?'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的通配符匹配。</p>
//
//<pre>'?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
//</pre>
//
//<p>两个字符串<strong>完全匹配</strong>才算匹配成功。</p>
//
//<p><strong>说明:</strong></p>
//
//<ul> 
// <li><code>s</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li> 
// <li><code>p</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>?</code>&nbsp;和&nbsp;<code>*</code>。</li> 
//</ul>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre><strong>输入:</strong>
//s = "aa"
//p = "a"
//<strong>输出:</strong> false
//<strong>解释:</strong> "a" 无法匹配 "aa" 整个字符串。</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre><strong>输入:</strong>
//s = "aa"
//p = "*"
//<strong>输出:</strong> true
//<strong>解释:</strong>&nbsp;'*' 可以匹配任意字符串。
//</pre>
//
//<p><strong>示例&nbsp;3:</strong></p>
//
//<pre><strong>输入:</strong>
//s = "cb"
//p = "?a"
//<strong>输出:</strong> false
//<strong>解释:</strong>&nbsp;'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
//</pre>
//
//<p><strong>示例&nbsp;4:</strong></p>
//
//<pre><strong>输入:</strong>
//s = "adceb"
//p = "*a*b"
//<strong>输出:</strong> true
//<strong>解释:</strong>&nbsp;第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
//</pre>
//
//<p><strong>示例&nbsp;5:</strong></p>
//
//<pre><strong>输入:</strong>
//s = "acdcb"
//p = "a*c?b"
//<strong>输出:</strong> false</pre>
//
//<div><li>👍 973</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//44.通配符匹配
//开题时间：2022-12-12 17:52:52
public class WildcardMatching {
    public static void main(String[] args) {
        Solution solution = new WildcardMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * dp
         * 关键点：2个字符串要从右往左匹配
         * 状态转移：
         *      s[i]=p[j]||p[j]='?':
         *          true:f[i][j]=f[i-1][j-1]
         *          false:
         *              p[j]!='*':false
         *              p[j]=='*':
         *                      '*'消了0次：f[i][j]=f[i][j-1]
         *                      '*'消了1次：f[i][j]=f[i-1][j-1]
         *                      '*'消了2次以上：f[i][j]=f[i-1][j]
         *  初始化：
         *      p只包含'*',  f[0][j]=true   ,f[0][0]=true
         *            否则,  f[0][j]=false
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
            for (int j = 1; j < n && cP[j - 1] == '*'; j++)
                f[0][j] = true;

            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++) {
                    char a = cS[i - 1];
                    char b = cP[j - 1];
                    if (a == b || b == '?')
                        f[i][j] = f[i - 1][j - 1];
                    else if (b == '*')
//                        f[i][j] = f[i][j - 1] || f[i - 1][j - 1] || f[i - 1][j];
                        f[i][j] = f[i][j - 1] || f[i - 1][j];
                }

            return f[m - 1][n - 1];
        }

        //TODO 贪心算法
    }
//leetcode submit region end(Prohibit modification and deletion)
}