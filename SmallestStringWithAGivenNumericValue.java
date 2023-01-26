//<p><strong>小写字符 </strong>的 <strong>数值</strong> 是它在字母表中的位置（从 <code>1</code> 开始），因此 <code>a</code> 的数值为 <code>1</code> ，<code>b</code> 的数值为 <code>2</code> ，<code>c</code> 的数值为 <code>3</code> ，以此类推。</p>
//
//<p>字符串由若干小写字符组成，<strong>字符串的数值</strong> 为各字符的数值之和。例如，字符串 <code>"abe"</code> 的数值等于 <code>1 + 2 + 5 = 8</code> 。</p>
//
//<p>给你两个整数 <code>n</code> 和 <code>k</code> 。返回 <strong>长度</strong> 等于 <code>n</code> 且 <strong>数值</strong> 等于 <code>k</code> 的 <strong>字典序最小</strong> 的字符串。</p>
//
//<p>注意，如果字符串 <code>x</code> 在字典排序中位于 <code>y</code> 之前，就认为 <code>x</code> 字典序比 <code>y</code> 小，有以下两种情况：</p>
//
//<ul> 
// <li><code>x</code> 是 <code>y</code> 的一个前缀；</li> 
// <li>如果 <code>i</code> 是&nbsp;<code>x[i] != y[i]</code> 的第一个位置，且 <code>x[i]</code>&nbsp;在字母表中的位置比&nbsp;<code>y[i]</code>&nbsp;靠前。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, k = 27
//<strong>输出：</strong>"aay"
//<strong>解释：</strong>字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5, k = 73
//<strong>输出：</strong>"aaszz"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li> 
// <li><code>n &lt;= k &lt;= 26 * n</code></li> 
//</ul>
//
//<div><li>👍 71</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//1663.具有给定数值的最小字符串
//开题时间：2023-01-26 17:39:29
public class SmallestStringWithAGivenNumericValue {
    public static void main(String[] args) {
        Solution solution = new SmallestStringWithAGivenNumericValue().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 贪心
         * 左边尽可能填充 'a' ，右边尽可能填充 'z'，类似 “aaa?zzzzzz” 的形式
         */
        public String getSmallestString9(int n, int k) {
            char[] ans = new char[n];
            Arrays.fill(ans, 'a');
            k -= n;
            for (int i = n - 1; k > 0; i--) {
                int incr = Math.min(k, 25);
                ans[i] += incr;
                k -= incr;
            }
            return new String(ans);
        }

        //优化之直接计算 'z' 的个数
        public String getSmallestString(int n, int k) {
            char[] ans = new char[n];
            k -= n;
            int fromIndex = n - (k / 25);
            Arrays.fill(ans, fromIndex, n, 'z');
            Arrays.fill(ans, 0, fromIndex, 'a');
            if (fromIndex > 0)
                ans[fromIndex - 1] += k % 25;
            return new String(ans);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}