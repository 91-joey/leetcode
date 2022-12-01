//<p>给你一个只包含 <code>'('</code>&nbsp;和 <code>')'</code>&nbsp;的字符串，找出最长有效（格式正确且连续）括号子串的长度。</p>
//
//<p>&nbsp;</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <p><strong>示例 1：</strong></p> 
// </div>
//</div>
//
//<pre>
//<strong>输入：</strong>s = "(()"
//<strong>输出：</strong>2
//<strong>解释：</strong>最长有效括号子串是 "()"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ")()())"
//<strong>输出：</strong>4
//<strong>解释：</strong>最长有效括号子串是 "()()"
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ""
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li> 
//</ul>
//
//<div><li>👍 2094</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//32.最长有效括号
//开题时间：2022-12-01 17:53:44
public class LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses("()(())"));
//        System.out.println(solution.longestValidParentheses("())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final char L = '(';
        public static final char R = ')';

        public int longestValidParentheses(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            if (n <= 1)
                return 0;

            int[] f = new int[n];
            int[] g = new int[n];
            g[0] = -1;
            if (cs[0] == L && cs[1] == R)
                f[1] = 2;
            g[1] = -1;
            int max = f[1];

            for (int i = 2; i < n; i++) {
                if (cs[i] == L)
                    g[i] = -1;
                else {
                    if (cs[i - 1] == L) {
                        f[i] = f[i - 2] + 2;
                        //todo 加上前面
                        if (f[i - 2] == 0)
                            g[i] = i - 2;
                        else
                            g[i] = g[i - 2];
                    } else {
                        if (f[i - 1] != 0 && g[i - 1] != -1 && cs[g[i - 1]] == L) {
                            f[i] = f[i - 1] + 2;
                            g[i] = g[i - 1] - 1;
                        } else {
                            g[i] = -1;
                        }
                    }
                }
                max = Math.max(max, f[i]);
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}