//<p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下映射进行了 <strong>编码</strong> ：</p>
//
//<pre>
//'A' -&gt; "1"
//'B' -&gt; "2"
//...
//'Z' -&gt; "26"</pre>
//
//<p>要 <strong>解码</strong> 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，<code>"11106"</code> 可以映射为：</p>
//
//<ul> 
// <li><code>"AAJF"</code> ，将消息分组为 <code>(1 1 10 6)</code></li> 
// <li><code>"KJF"</code> ，将消息分组为 <code>(11 10 6)</code></li> 
//</ul>
//
//<p>注意，消息不能分组为&nbsp; <code>(1 11 06)</code> ，因为 <code>"06"</code> 不能映射为 <code>"F"</code> ，这是由于 <code>"6"</code> 和 <code>"06"</code> 在映射中并不等价。</p>
//
//<p>给你一个只含数字的 <strong>非空 </strong>字符串 <code>s</code> ，请计算并返回 <strong>解码</strong> 方法的 <strong>总数</strong> 。</p>
//
//<p>题目数据保证答案肯定是一个 <strong>32 位</strong> 的整数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "12"
//<strong>输出：</strong>2
//<strong>解释：</strong>它可以解码为 "AB"（1 2）或者 "L"（12）。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "226"
//<strong>输出：</strong>3
//<strong>解释：</strong>它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "0"
//<strong>输出：</strong>0
//<strong>解释：</strong>没有字符映射到以 0 开头的数字。
//含有 0 的有效映射是 'J' -&gt; "10" 和 'T'-&gt; "20" 。
//由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 100</code></li> 
// <li><code>s</code> 只包含数字，并且可能包含前导零。</li> 
//</ul>
//
//<div><li>👍 1296</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//91.解码方法
//开题时间：2022-12-03 14:17:31
public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
//        System.out.println(solution.numDecodings("06"));
        System.out.println(solution.numDecodings("226"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * f[i] ：以索引 i 结尾，且结尾处解码 1 个数字的方法数
         * g[i] ：以索引 i 结尾，且结尾处解码 2 个数字的方法数
         */
        public int numDecodings9(String s) {
            int n = s.length();
            int[] f = new int[n];
            int[] g = new int[n];
            f[0] = s.charAt(0) == '0' ? 0 : 1;

            for (int i = 1; i < n; i++) {
                int num = s.charAt(i) - '0';

                if (num != 0)
                    f[i] = f[i - 1] + g[i - 1];

                char pre = s.charAt(i - 1);
                if (pre != '0' && (pre - '0') * 10 + num <= 26)
                    g[i] = i >= 2 ? f[i - 2] + g[i - 2] : 1;
            }

            return f[n - 1] + g[n - 1];
        }

        //dp[i] ：以索引 i 结尾的方法数
        public int numDecodings8(String s) {
            int n = s.length();
            int[] dp = new int[n];
            dp[0] = s.charAt(0) == '0' ? 0 : 1;

            for (int i = 1; i < n; i++) {
                int num = s.charAt(i) - '0';

                if (num != 0)
                    dp[i] += dp[i - 1];

                char pre = s.charAt(i - 1);
                if (pre != '0' && (pre - '0') * 10 + num <= 26)
                    dp[i] += i >= 2 ? dp[i - 2] : 1;
            }

            return dp[n - 1];
        }

        //dp[i]空间优化
        public int numDecodings(String s) {
            int n = s.length();
            int a = 0;
            int b = s.charAt(0) == '0' ? 0 : 1;
            int c = 0;

            for (int i = 1; i < n; i++) {
                int num = s.charAt(i) - '0';

                if (num != 0)
                    c += b;

                char pre = s.charAt(i - 1);
                if (pre != '0' && (pre - '0') * 10 + num <= 26)
                    c += i >= 2 ? a : 1;

                a = b;
                b = c;
                c = 0;
            }

            return b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}