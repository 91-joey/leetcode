//<p>有台奇怪的打印机有以下两个特殊要求：</p>
//
//<ul> 
// <li>打印机每次只能打印由 <strong>同一个字符</strong> 组成的序列。</li> 
// <li>每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。</li> 
//</ul>
//
//<p>给你一个字符串 <code>s</code> ，你的任务是计算这个打印机打印它需要的最少打印次数。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aaabbb"
//<strong>输出：</strong>2
//<strong>解释：</strong>首先打印 "aaa" 然后打印 "bbb"。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aba"
//<strong>输出：</strong>2
//<strong>解释：</strong>首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 100</code></li> 
// <li><code>s</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 297</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//664.奇怪的打印机
//开题时间：2022-12-21 16:29:42
public class StrangePrinter {
    public static void main(String[] args) {
        Solution solution = new StrangePrinter().new Solution();
        System.out.println(solution.strangePrinter("aba"));
        System.out.println(solution.strangePrinter("abab"));
//        System.out.println(solution.strangePrinter("aaabbb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strangePrinterX(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            int[][] f = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    f[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] - (cs[k] == cs[k + 1] ? 1 : 0));
                    }
                }
            }

            return f[0][n - 1];
        }

        /*
         * 区间DP
         * 定义：f[i][j]表示 s 的子字符串 [i,j] 的最少打印次数
         * 转移：
         *      单字符：1
         *      两字符及以上：
         *          首尾字符相同，则与去首或去尾后的子问题相同：
         *              f[i][j - 1] 或 f[i + 1][j]
         *          首尾字符不同，则枚举i、j中间的分割点k(i<=k<j)，为2个子问题和的最小值：
         *              min(f[i][k] + f[k + 1][j])
         */
        public int strangePrinter(String s) {
            char[] cs = s.toCharArray();
            int n = 1;
            //连续相同的字符，只保留一个字符
            for (int i = 1; i < s.length(); i++)
                if (cs[i - 1] != cs[i])
                    cs[n++] = cs[i];
            int[][] f = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++)
                    if (cs[i] == cs[j])
                        f[i][j] = f[i][j - 1];
//                        f[i][j] = f[i + 1][j];
                    else {
                        f[i][j] = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++)
                            f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                    }
            }

            return f[0][n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}